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

import com.example.bookstore.bdd.builders.bdd.GivenIsbnDbBuilder;
import com.example.bookstore.bdd.builders.bdd.WhenIsbnDbBuilder;
import com.example.bookstore.bdd.model.bdd.ThenGetBookByIsbnBuilder;
import com.example.bookstore.model.IsbnBook;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.bitsmart.bdd.report.junit5.test.BaseTest;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseBookStoreTest extends BaseTest {

	@Autowired
	private TestRestTemplate template;

    private ResponseEntity<String> response;

    // how to configure this as WireMockExtension is static. Static life cycle means reset before and after class.
    //@Value("${downstream.port}")
    private static final int PORT = 8080;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @RegisterExtension
    static WireMockExtension wm1 = WireMockExtension.newInstance()
        .options(wireMockConfig().port(PORT))
        //.configureStaticDsl(true) //what does this do?
        .build();

    @Override
    public void doc() {
    }

    public void given(GivenIsbnDbBuilder givenBuilder) {
        givenBuilder.build().getBooks().forEach((isbn, book) -> {
            stubFor(get(urlEqualTo("/isbn-db/" + isbn))
                .withPort(PORT)
                .willReturn(aResponse()
                    .withHeader("Content-Type", "application/json")
                    .withBody(isbnBookAsString(book))));
        });
    }

    public void when(WhenIsbnDbBuilder whenBuilder) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(singletonList(MediaType.APPLICATION_JSON));

        String isbn = whenBuilder.build().getIsbn();
        response = template.getForEntity("/book/" + isbn, String.class, headers);
    }

    public void then(ThenGetBookByIsbnBuilder thenBuilder) {
        assertThat(isbnBook(response.getBody())).isEqualTo(thenBuilder.build().getBook());
    }

    private IsbnBook isbnBook(String json) {
        try {
            return MAPPER.readValue(json, IsbnBook.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String isbnBookAsString(IsbnBook book) {
        try {
            return MAPPER.writeValueAsString(book);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void checkWiremockIsRunning() {
        URI uri = null;
        try {
            uri = new URI("http://localhost:"+ PORT + "/__admin/mappings");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        final ResponseEntity<String> forEntity = template.getForEntity(uri, String.class);
        final String body = forEntity.getBody();
        assertThat(body).isNotNull();
    }
}
