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
import java.util.ArrayList;
import meta.LineNote;

/**
 *
 * @author Nathan
 */
public class XMLWriter {
    private String directory = "C:/Users/Nathan/Documents/Programming/Projects/2014/lineNotes/src/data/notes/saved/";
    private ArrayList<LineNote> notes;
    
    public XMLWriter(ArrayList<LineNote> notes) {
        this.notes = notes;
    }
    
    public void storeData(String characterName) {
        String newDirectory = directory + characterName.replaceAll(" ", "") + ".xml";
        
        XStream xstream = new XStream();
        //xstream.alias("note", LineNote.class);
        String xml = "";
        for (LineNote note : notes) {
            xml += xstream.toXML(note) + "\n";
        }
        FileOutput f = new FileOutput();
        f.writeFile(newDirectory, xml);
    }
}
