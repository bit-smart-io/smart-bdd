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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  <div class="mermaid">
 *  sequenceDiagram
 *     participant Alice
 *     participant Bob
 *     Alice->>John: Hello John, how are you?
 *     loop Healthcheck
 *         John->>John: Fight against hypochondria
 *     end
 *     Note right of John: Rational thoughts <br/>prevail!
 *     John-->>Alice: Great!
 *     John->>Bob: How about you?
 *     Bob-->>John: Jolly good!
 *   </div>
 */
public class SequenceDiagram implements Expression {
    private List<Participant> participants = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    @Override
    public String generate() {
        return "sequenceDiagram\n"
            + participants.stream().map(Expression::generate).collect(Collectors.joining("\n\t", "\t", "\n"))
            + messages.stream().map(Expression::generate).collect(Collectors.joining("\n\t", "\t", ""));
    }

    public void addActor(String name) {
        //TODO if "Actor" throw illegal state exception
        participants.add(new Participant(name, ParticipantType.ACTOR));
    }

    public void addParticipant(String name) {
        participants.add(new Participant(name));
    }

    public void addMessage(Message message) {
        messages.add(message);
    }
}
