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

package com.example.bookstore.bdd.builder_example.defaults;

/** would this helpful for should we have the defaults in the builders? */
public class DefaultIsbnBook {
    public static final String isbn = "default-isbn_";
    public static final String title = "default-title";
    public static final String author = "default-author";

    public static final String isbn10 = "1234567890";
    public static final String isbn13 = "1234567890123";

    public static final String INVALID_ISBN_LENGTH_1 = "1";
    public static final String INVALID_ISBN_LENGTH_9 = "123456789";
    public static final String INVALID_ISBN_LENGTH_11 = "12345678901";
    public static final String INVALID_ISBN_LENGTH_12 = "123456789012";
    public static final String INVALID_ISBN_LENGTH_14 = "12345678901234";
}
