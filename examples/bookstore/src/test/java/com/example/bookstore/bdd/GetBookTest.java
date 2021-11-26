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

import org.junit.jupiter.api.Test;

import static com.example.bookstore.bdd.builders.IsbnBookBuilder.anIsbnBook;
import static com.example.bookstore.bdd.builders.bdd.GivenIsbnDbBuilder.IsbnDbContains;
import static com.example.bookstore.bdd.builders.bdd.GivenIsbnDbEntryBuilder.forAnIsbn;
import static com.example.bookstore.bdd.builders.bdd.WhenIsbnDbBuilder.aUserRequestsABook;
import static com.example.bookstore.bdd.factories.IsbnBookFactory.aDefaultIsbnBook;
import static com.example.bookstore.bdd.model.bdd.ThenGetBookByIsbnBuilder.theResponseContains;
import static java.util.Collections.singletonList;

public class GetBookTest extends BaseBookStoreTest {

    @Test
    public void getBookByIsbnUsingDefaults() {
        when(aUserRequestsABook());
        then(theResponseContains(aDefaultIsbnBook()));
    }

    @Test
    public void getBookByIsbnShowingAllDefaultValues() {
        given(IsbnDbContains().anEntry(
            forAnIsbn("default-isbn")
                .thatWillReturn(anIsbnBook()
                    .withIsbn("default-isbn")
                    .withTitle("default-title")
                    .withAuthor("default-author"))));
        when(aUserRequestsABook().withIsbn("default-isbn"));
        then(theResponseContains(anIsbnBook()
            .withIsbn("default-isbn")
            .withTitle("default-title")
            .withAuthors(singletonList("default-author"))));
    }
}
