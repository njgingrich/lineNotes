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
package file.json;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import meta.LineNote;

/**
 *
 * @author Nathan
 */
public class JSONWriter {
    private final String directory;
    private final ArrayList<LineNote> notes;

    /**
     * Create a new XMLWriter to write the notes to the given directory
     * @param notes the notes to write
     * @param directory the given directory to write to
     * @param title the title of the show
     */
    public JSONWriter(ArrayList<LineNote> notes, String directory, String title) {
        //this.directory = directory + "/" + title + "/";
        this.directory = directory;
        this.notes = notes;
    }
    
    /**
     * Store the data in an XML file
     * @param characterName the name of the role to be saved
     * @throws IOException
     */
    public void storeXMLData(String characterName) throws IOException {
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
    
    /**
     *
     * @param characterName
     * @throws IOException
     */
    public void storeJsonData(String characterName) throws IOException {
        //String newFileLocation = directory + characterName.replaceAll(" ", "") + ".json";
        String newFileLocation = "out/Urinetown/notes/saved/" + characterName.replaceAll(" ", "") + ".json";
        System.out.println("new file location: " + newFileLocation);
        
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            @Override
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        xstream.alias("LineNote", LineNote.class);
        //FileWriter f = new FileWriter(new File(newFileLocation), true); // only for append mode
        FileWriter f = new FileWriter(new File(newFileLocation), false);
        try (ObjectOutputStream out = xstream.createObjectOutputStream(f)) {
            //for (LineNote note : notes) {
            //    out.writeObject(note);
            //}
            out.writeObject(notes);
        }
    }
}


/**
 * JACKIE SUCKS
 */