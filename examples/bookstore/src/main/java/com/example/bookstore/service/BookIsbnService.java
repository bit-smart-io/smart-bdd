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

package com.example.bookstore.service;

import com.example.bookstore.model.IsbnBook;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.fluent.Content;
import org.apache.hc.client5.http.fluent.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BookIsbnService {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value("${downstream.port}")
    private int port;

    public IsbnBook get(String isbn) throws IOException {
        final Content content = Request.get(url() + isbn)
            .execute()
            .returnContent();
        return MAPPER.readValue(content.asString(), IsbnBook.class);
    }

    private String url() {
        return "http://localhost:" + port + "/isbn-db/";
    }
}
