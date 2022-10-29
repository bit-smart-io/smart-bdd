/*
 * Smart BDD - The smart way to do behavior-driven development.
 * Copyright (C)  2021  James Bayliss
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.bookstore.bdd;

import com.example.bookstore.model.IsbnBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import io.bitsmart.bdd.report.junit5.results.extension.SmartReport;
import io.bitsmart.bdd.report.junit5.test.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getAllServeEvents;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.bitsmart.bdd.report.mermaid.MessageBuilder.aMessage;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

/**
 * To get UML and docs extend BaseTest
 */
@ExtendWith(SmartReport.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class GetBookByIsbnTest extends BaseTest {

    @Override
    public void doc() {
        featureNotes("Working progress for example of usage Smart BDD");
    }

    @Autowired
    private TestRestTemplate template;
    private static final int PORT = 8080;

    @RegisterExtension
    static WireMockExtension wireMockExtension = WireMockExtension.newInstance()
        .options(wireMockConfig().port(PORT))
        .build();

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String isbn10 = "1234567890";
    private static final String isbn13 = "1234567890123";
    private static final String INVALID_ISBN_LENGTH_1 = "1";
    private static final String INVALID_ISBN_LENGTH_9 = "123456789";
    private static final String INVALID_ISBN_LENGTH_11 = "12345678901";
    private static final String INVALID_ISBN_LENGTH_12 = "123456789012";
    private static final String INVALID_ISBN_LENGTH_14 = "12345678901234";
    private final IsbnBook bookIsbn10 = new IsbnBook(isbn10, "title1", singletonList("author1"));
    private final IsbnBook bookIsbn13 = new IsbnBook(isbn13, "title2", singletonList("author2"));

    private ResponseEntity<String> response = null;

    @BeforeEach
    public void setupMocksAndResponse() {
        stubGetBookByIsbn(isbn10, bookIsbn10);
        stubGetBookByIsbn(isbn13, bookIsbn13);
        response = null;
    }

    @BeforeEach
    void setupUml() {
        sequenceDiagram()
            .addActor("User")
            .addParticipant("BookStore")
            .addParticipant("ISBNdb");
    }

    private void stubGetBookByIsbn(String isbn, IsbnBook book) {
        stubFor(get(urlEqualTo("/isbn-db/" + isbn))
            .withPort(PORT)
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody(bookAsString(book))));
    }

    @Test
    public void getBookByAValidIsbn10() {
        whenGetBookByIsbnIsCalledWith(isbn10);
        thenTheResponseBodyContains(bookIsbn10);
    }

    @Test
    public void getBookByAValidIsbn13() {
        whenGetBookByIsbnIsCalledWith(isbn13);
        thenTheResponseBodyContains(bookIsbn13);
    }

    @ParameterizedTest(name = "#{index} - ISBN {0} not valid - returns warning")
    @ValueSource(strings = {
        INVALID_ISBN_LENGTH_1, INVALID_ISBN_LENGTH_9, INVALID_ISBN_LENGTH_11, INVALID_ISBN_LENGTH_12,  INVALID_ISBN_LENGTH_14})
    public void getBookByIsbn_whenIsbnNumberIsNot10or13_return417StatusCode(String isbn) {
        whenGetBookByIsbnIsCalledWith(isbn);
        thenTheResponseStatusIs(EXPECTATION_FAILED);
        thenTheResponseErrorMessageIs("Expectation Failed - ISBN should be 10 or 13 characters");
    }

    private String bookAsString(IsbnBook book) {
        try {
            return MAPPER.writeValueAsString(book);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private IsbnBook bookFromJson(String json) {
        try {
            return MAPPER.readValue(json, IsbnBook.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void thenTheResponseBodyContains(IsbnBook book) {
        sequenceDiagram().add(aMessage().from("BookStore").to("User").text(response.getBody()));
        assertThat(bookFromJson(response.getBody())).isEqualTo(book);
    }

    private void thenTheResponseStatusIs(HttpStatus statusCode) {
        assertThat(response.getStatusCode()).isEqualTo(statusCode);
    }

    private void thenTheResponseErrorMessageIs(String errorMessage) {
        assertThat(response.getBody()).isEqualTo(errorMessage);
    }

    public void whenGetBookByIsbnIsCalledWith(String isbn) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(MediaType.APPLICATION_JSON));

        sequenceDiagram().add(aMessage().from("User").to("BookStore").text("/book/" + isbn));
        response = template.getForEntity("/book/" + isbn, String.class, headers);

        List<ServeEvent> allServeEvents = getAllServeEvents();
        allServeEvents.forEach(event -> {
            sequenceDiagram().add(aMessage().from("BookStore").to("ISBNdb").text(event.getRequest().getUrl()));
            sequenceDiagram().add(aMessage().from("ISBNdb").to("BookStore").text(event.getResponse().getBodyAsString()));
        });

        sequenceDiagram().add(aMessage().from("BookStore").to("User").text(response.getBody()));
    }
}
