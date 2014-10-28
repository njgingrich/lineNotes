package json;

import com.google.gson.Gson;
import file.FileOutput;
import file.FormatOutputData;
import java.io.IOException;
import java.util.ArrayList;
import meta.LineNote;

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
/**
 *
 * @author Nathan
 */
public class JSONWriter {
    private ArrayList<LineNote> notes;
    private String baseDirectory = "C:/Users/Nathan/Documents/Programming/Projects/2014/lineNotes/src/data/notes";
    
    public JSONWriter(ArrayList<LineNote> notes) {
        this.notes = notes;
    }
    
    public void storeData(String characterName) throws IOException {
        Gson gson = new Gson();
        FileOutput f = new FileOutput();
        
        String json = "";
        for (LineNote note : notes) {
            //json += gson.toJson(note);
            FormatOutputData data = new FormatOutputData();
            System.out.println(data.format(note));
            //System.out.println(json);
        }
        f.writeFile(f.setFileName(characterName), json);
    }
}
