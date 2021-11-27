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

import com.example.bookstore.bdd.model.bdd.GivenIsbnDb;
import com.example.bookstore.bdd.model.bdd.GivenIsbnDbEntry;
import io.bitsmart.bdd.report.utils.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class GivenIsbnDbBuilder implements Builder<GivenIsbnDb> {
    private List<GivenIsbnDbEntryBuilder> isbnDbEntries = new ArrayList<>();

    private GivenIsbnDbBuilder() {
    }

    public static GivenIsbnDbBuilder theIsbnDbContains() {
        return new GivenIsbnDbBuilder();
    }

    public GivenIsbnDbBuilder anEntry(GivenIsbnDbEntryBuilder isbnDbEntry) {
        isbnDbEntries.add(isbnDbEntry);
        return this;
    }

    public GivenIsbnDb build() {
        return new GivenIsbnDb(isbnDbEntries.stream().map(GivenIsbnDbEntryBuilder::build)
            .collect(Collectors.toMap(GivenIsbnDbEntry::getIsbn, GivenIsbnDbEntry::getBook))
        );
    }
}
