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
import java.io.FileNotFoundException;
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
    private final String directory = "C:/Users/Nathan/Documents/Programming/Projects/2014/lineNotes/src/data/notes/saved/";
    private final BufferedReader reader;
    
    /**
     * Create a new XMLReader
     * @param reader
     */
    public XMLReader(BufferedReader reader) {
        this.reader = reader;
    }
    
    /**
     * Read the data saved in an XML file
     * @param role the role to read data for
     * @return the arrayList of notes found for the given role
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     */
    public ArrayList<LineNote> readData(Role role) throws IOException, ClassNotFoundException, FileNotFoundException {
        ArrayList<LineNote> notes = new ArrayList<>();
        XStream xstream = new XStream();
        xstream.alias("LineNote", LineNote.class);
        ObjectInputStream in = xstream.createObjectInputStream(reader);
        
        LineNote noteToAdd = (LineNote)in.readObject();
        notes.add(noteToAdd);
        return notes;
    } 
}
