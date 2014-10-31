/*
 * Copyright (C) 2014 Nathan
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package meta;

import java.util.ArrayList;

/**
 *
 * @author Nathan
 */
public class Role {
    private final String name;
    private final Actor actor;
    private final ArrayList<LineNote> notes;
    
    /**
     * Create a new role
     * @param actor the actor
     * @param role the role
     */
    public Role(Actor actor, String role) {
        this.name = role;
        this.actor = actor;
        notes = new ArrayList<>();
    }
    
    /**
     * @return the name of the role
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the name of the actor
     */
    public Actor getActor() {
        return actor;
    }
    
    /**
     * @return the notes for the actor
     */
    public ArrayList<LineNote> getNotes() {
        return notes;
    }
    
    /**
     * Add a new note to the role
     * @param note the note to add
     */
    public void addNote(LineNote note) {
        notes.add(note);
    }
}
