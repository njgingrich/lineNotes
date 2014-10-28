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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nathan
 */
public class Show {
    private String title;
    private ArrayList<Role> characterList;
    
    public Show(String iniFileName) {
        FileInput in = new FileInput();
        characterList = new ArrayList<>();
        
        try {
            ArrayList<String> iniData;
            iniData = in.getShowInformation(iniFileName);
            parseIniFile(iniData);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Show.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void parseIniFile(ArrayList<String> iniData) {
        for (int i = 0; i < iniData.size(); i++) {
            String line = iniData.get(i);
            // First lines until the second comment will be the show name
            if (line.equals("#Show Name")) {
                title = iniData.get(i+1);
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
    }
    
    public ArrayList<Role> getCharacterList() {
        return characterList;
    }
}
