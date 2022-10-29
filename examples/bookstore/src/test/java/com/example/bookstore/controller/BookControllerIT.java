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

package com.example.bookstore.controller;

import com.example.bookstore.model.IsbnBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class BookControllerIT {

    @Autowired
    private TestRestTemplate template;

    private static final int PORT = 8080;

    @RegisterExtension
    static WireMockExtension wireMockExtension = WireMockExtension.newInstance()
        .options(wireMockConfig().port(PORT))
        .build();

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String ISBN10 = "1234567890";
    private static final String ISBN13 = "1234567890123";
    private static final String INVALID_ISBN_LENGTH_1 = "1";
    private static final String INVALID_ISBN_LENGTH_9 = "123456789";
    private static final String INVALID_ISBN_LENGTH_11 = "12345678901";
    private static final String INVALID_ISBN_LENGTH_12 = "123456789012";
    private static final String INVALID_ISBN_LENGTH_14 = "12345678901234";
    private final IsbnBook bookIsbn10 = new IsbnBook(ISBN10, "title1", singletonList("author1"));
    private final IsbnBook bookIsbn13 = new IsbnBook(ISBN13, "title2", singletonList("author2"));

    @BeforeEach
    public void setupMocks() {
        stubGetBookByIsbn(ISBN10, bookIsbn10);
        stubGetBookByIsbn(ISBN13, bookIsbn13);
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
        ResponseEntity<IsbnBook> response = template.getForEntity("/book/" + ISBN10, IsbnBook.class);
        assertThat(response.getBody()).isEqualTo(bookIsbn10);
    }

    @Test
    public void getBookByAValidIsbn13() {
        ResponseEntity<IsbnBook> response = template.getForEntity("/book/" + ISBN13, IsbnBook.class);
        assertThat(response.getBody()).isEqualTo(bookIsbn13);
    }

    @ParameterizedTest(name = "#{index} - ISBN {0} not valid - returns warning")
    @ValueSource(strings = {
        INVALID_ISBN_LENGTH_1, INVALID_ISBN_LENGTH_9, INVALID_ISBN_LENGTH_11, INVALID_ISBN_LENGTH_12,  INVALID_ISBN_LENGTH_14})
    public void getBookByIsbn_whenIsbnNumberIsNot10or13_return417StatusCode(String isbn) {
        ResponseEntity<String> response = template.getForEntity("/book/" + isbn, String.class);
        assertThat(response.getStatusCode()).isEqualTo(EXPECTATION_FAILED);
        assertThat(response.getBody()).isEqualTo("Expectation Failed - ISBN should be 10 or 13 characters");
    }

    private String bookAsString(IsbnBook book) {
        try {
            return MAPPER.writeValueAsString(book);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
