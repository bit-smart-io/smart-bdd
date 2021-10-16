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

package com.example.cucumbers.model;

import java.util.Objects;

public class Cucumber {
    private final int size;
    private final String colour;

    public Cucumber(int size, String colour) {
        this.size = size;
        this.colour = colour;
    }

    public int getSize() {
        return size;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cucumber)) return false;
        Cucumber cucumber = (Cucumber) o;
        return size == cucumber.size && Objects.equals(colour, cucumber.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, colour);
    }

    @Override
    public String toString() {
        return "Cucumber{" +
            "size=" + size +
            ", colour='" + colour + '\'' +
            '}';
    }
}
