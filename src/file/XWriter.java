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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import meta.LineNote;

/**
 *
 * @author Nathan
 */
public class XWriter {
    private final String directory;
    private final ArrayList<LineNote> notes;

    /**
     * Create a new XMLWriter to write the notes to the given directory
     * @param notes the notes to write
     * @param directory the given directory to write to
     * @param title
     */
    public XWriter(ArrayList<LineNote> notes, String directory, String title) {
        this.directory = directory + "/" + title + "/";
        this.notes = notes;
    }
    
    /**
     * Store the data in an XML file
     * @param characterName the name of the role to be saved
     * @throws IOException
     */
    public void storeData(String characterName) throws IOException {
        String newFileLocation = directory + characterName.replaceAll(" ", "") + ".xml";
        System.out.println("new file location: " + newFileLocation);
        
        XStream xstream = new XStream();
        xstream.alias("LineNote", LineNote.class);
        //FileWriter f = new FileWriter(new File(newFileLocation), true); // only for append mode
        FileWriter f = new FileWriter(new File(newFileLocation), false);
        try (ObjectOutputStream out = xstream.createObjectOutputStream(f)) {
            for (LineNote note : notes) {
                out.writeObject(note);
            }
        }
    }
}
