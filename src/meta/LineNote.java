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
public class LineNote {
    private final String error;
    private final String line;
    private final String note;
    private final String page;
        
    public LineNote(String line, String error, String pageNum, String note) {
        this.line = line;
        this.error = error;
        this.page = pageNum;
        this.note = note;
    }
    
    public String getLine() {
        return line;
    }
    
    public String getPageNum() {
        return page;
    }
    
    public String getNote() {
        return note;
    }
    
    public String getError() {
        return error;
    }
    
    public String toString() {
        return "Page " + page + ": " + line + " - " + note + " - " + error;
        //return "(" + page + "):\n" + line + "\n" + note;
    }
}
