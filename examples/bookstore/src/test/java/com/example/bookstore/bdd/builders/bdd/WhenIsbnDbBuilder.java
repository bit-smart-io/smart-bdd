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

package com.example.bookstore.bdd.builders.bdd;

import com.example.bookstore.bdd.model.bdd.WhenGetBookByIsbn;
import io.bitsmart.bdd.report.utils.Builder;

public final class WhenIsbnDbBuilder implements Builder<WhenGetBookByIsbn> {
    private String isbn;

    private WhenIsbnDbBuilder() {
    }

    public static WhenIsbnDbBuilder aUserRequestsABook() {
        return new WhenIsbnDbBuilder();
    }

    public WhenIsbnDbBuilder withIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public WhenGetBookByIsbn build() {
        return new WhenGetBookByIsbn(isbn);
    }
}
