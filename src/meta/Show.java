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

import java.util.ArrayList;

import file.FileInput;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Nathan
 */
public class Show {
    private String title;
    private ArrayList<Role> characterList;
    private String directory;
    
    public Show(File file) throws ClassNotFoundException, FileNotFoundException {
        FileInput in = new FileInput(file);
        characterList = new ArrayList<>();
        
        try {
            ArrayList<String> showData;
            showData = in.getShowInformation();
            parseShowFile(showData);
            directory = in.getDirectory();
            
            for (Role role : characterList) {
                in.getSavedLineNotes(role);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private String parseShowFile(ArrayList<String> showData) {
        for (int i = 0; i < showData.size(); i++) {
            String line = showData.get(i);
            // First lines until the second comment will be the show name
            if (line.equals("#Show Name")) {
                title = showData.get(i+1);
                i += 2;
            } else if (line.startsWith("#")) {
                // Line is a comment
                continue;
            } else {
                // Data is in format Character Name>Actor Name
                String[] split = line.split(">");
                String[] names = split[1].split(" ");
                String email = names[0] + "." + names[1] + "@hope.edu";
                Actor actor = new Actor(names[0], names[1], email);
                Role role = new Role(actor, split[0]);
                characterList.add(role);
            }
        }
        return title;
    }
    
    public ArrayList<Role> getCharacterList() {
        return characterList;
    }
    
    public String getDirectory() {
        return directory;
    }
}
