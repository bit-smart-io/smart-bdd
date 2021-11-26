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

package com.example.bookstore.bdd.mappings;

import com.example.bookstore.bdd.BaseBookStoreTest;
import com.example.bookstore.bdd.defaults.DefaultIsbnBook;

import static com.example.bookstore.bdd.builders.IsbnBookBuilder.anIsbnBook;
import static com.example.bookstore.bdd.builders.bdd.GivenIsbnDbBuilder.IsbnDbContains;
import static com.example.bookstore.bdd.builders.bdd.GivenIsbnDbEntryBuilder.forAnIsbn;

public class DefaultMappings {

    private final BaseBookStoreTest baseBookStoreTest;

    public DefaultMappings(BaseBookStoreTest baseBookStoreTest) {
        this.baseBookStoreTest = baseBookStoreTest;
    }

    public void initDefaultMappings() {
        baseBookStoreTest.given(IsbnDbContains().anEntry(
            forAnIsbn(DefaultIsbnBook.ISBN)
                .thatWillReturn(anIsbnBook()
                    .withIsbn(DefaultIsbnBook.ISBN)
                    .withTitle(DefaultIsbnBook.TITLE)
                    .withAuthor(DefaultIsbnBook.AUTHOR))));
    }
}
