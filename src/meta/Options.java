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

/**
 *
 * @author Nathan
 */
public enum Options {
    AUTOSAVE("AUTOSAVE"),
    NEW_DIALOGS("NEW_DIALOG"),
    EMAIL_STRING("EMAIL_STRING");
    
    private Options(String val) {
        this.val = val;
    }
    
    public final String getValue() {
        return val;
    }
    
    public void setValue(String val) {
        this.val = val;
    }
    
    private String val;
}
