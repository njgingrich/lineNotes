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
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import meta.LineNote;

/**
 *
 * @author Nathan
 */
public class XMLWriter {
    private final String directory;
    private final ArrayList<LineNote> notes;
    
    /**
     * Create a new XMLWriter to write the notes
     * @param notes the notes to write
     */
    public XMLWriter(ArrayList<LineNote> notes) {
        this.directory = "C:/Users/Nathan/Documents/Programming/Projects/2014/lineNotes/src/data/notes/saved/";
        this.notes = notes;
    }
    
    /**
     * Create a new XMLWriter to write the notes to the given directory
     * @param notes the notes to write
     * @param directory the given directory to write to
     */
    public XMLWriter(ArrayList<LineNote> notes, String directory) {
        this.directory = directory;
        this.notes = notes;
    }
    
    /**
     * Store the data in an XML file
     * @param characterName the name of the role to be saved
     * @throws IOException
     */
    public void storeData(String characterName) throws IOException {
        String newDirectory = directory + characterName.replaceAll(" ", "") + ".xml";
        
        XStream xstream = new XStream();
        xstream.alias("LineNote", LineNote.class);
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(newDirectory));
        for (LineNote note : notes) {
            out.writeObject(note);
        }
        out.close();
    }
}
