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
public class Actor {
    private final String email;
    private final String firstName;
    private final String lastName;
    
    /**
     * Create a new Actor
     * @param firstName the first name
     * @param lastName the last name
     */
    public Actor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        email = firstName + "." + lastName + "@hope.edu";
    }
    
    /**
     * Create a new actor
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email address
     */
    public Actor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @return the first name of the actor
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @return the last name of the actor
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @return the full name of the actor
     */
    public String getName() {
        return firstName + " " + lastName;
    }
}
