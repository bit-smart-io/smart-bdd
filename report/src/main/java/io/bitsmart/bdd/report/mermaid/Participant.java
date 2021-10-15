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

package io.bitsmart.bdd.report.mermaid;

public class Participant implements Expression {

    private final String name;
    private final ParticipantType type;

    public Participant(String name) {
        this(name, ParticipantType.PARTICIPANT);
    }

    public Participant(String name, ParticipantType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String generate() {
        return type() + " "+ name;
    }

    private String type() {
        return type == ParticipantType.PARTICIPANT ? "participant" : "actor";
    }
}
