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
package file;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import meta.LineNote;
import meta.Role;

/**
 *
 * @author Nathan
 */
public class XMLReader {
    private String directory = "C:/Users/Nathan/Documents/Programming/Projects/2014/lineNotes/src/data/notes/saved/";
    private BufferedReader reader;
    
    public XMLReader(BufferedReader reader) {
        this.reader = reader;
    }
    
    public ArrayList<LineNote> readData(Role role) throws IOException, ClassNotFoundException {
       ArrayList<LineNote> notes = new ArrayList<>();
        XStream xstream = new XStream();
        ObjectInputStream in = xstream.createObjectInputStream(reader);
        File f = new File(directory + role.getName().replaceAll(" ", "") + ".xml");
        
        try {
            notes.add((LineNote)in.readObject());
        } catch (EOFException ex) {
            return notes;
        }
        return notes;
    } 
}
