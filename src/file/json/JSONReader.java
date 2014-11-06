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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import meta.LineNote;
import meta.Role;

/**
 *
 * @author Nathan
 */
public class JSONReader {
    private final BufferedReader reader;
    
    /**
     * Create a new XMLReader
     * @param reader
     */
    public JSONReader(BufferedReader reader) {
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
        BufferedReader br = new BufferedReader(reader);
        Gson gson = new Gson();
        LineNote noteToAdd;
        ArrayList<LineNote> notes = new ArrayList<>();
        String fileContents = "";
        String line = "";
        while ((line = br.readLine()) != null) {
            fileContents += line;
        }
        
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(fileContents);
        
        Type listType = new TypeToken<ArrayList<LineNote>>() {}.getType();
        notes = gson.fromJson(jo.get("list"), listType);
        //notes = gson.fromJson(reader, LineNote.class);
       
        return notes;
    } 
}
