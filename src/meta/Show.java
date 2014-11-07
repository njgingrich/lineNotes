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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Nathan
 */
public class Show {
    private String title;
    private ArrayList<Role> characterList;
    private String directory;
    
    /**
     * Create a new show
     * @param file the location of the show file
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     */
    public Show(File file) throws ClassNotFoundException, FileNotFoundException {
        FileInput in = new FileInput(file);
        characterList = new ArrayList<>();
        
        try {
            ArrayList<String> showData;
            showData = in.getShowInformation();
            parseShowFile(showData);
            directory = in.getDirectory();
            
            for (Role role : characterList) {
                /*
                Need to handle FileNotFound exceptions here
                */
                try {
                    FileInput savedData = new FileInput(directory + "/notes/saved/", role);
                    ArrayList<LineNote> notesToAdd = savedData.getSavedLineNotes();
                    for(LineNote note: notesToAdd) {
                        role.addNote(note);
                    }
                } catch (FileNotFoundException ex) {
                    System.out.println("No entries for " + role.getName());
                    //ex.printStackTrace();
                }
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
        return getTitle();
             
        /*
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver() {
            public HierarchicalStreamWriter createWriter(Writer writer) {
                return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE);
            }
        });
        xstream.setMode(XStream.NO_REFERENCES);
        xstream.alias("LineNotes", LineNote.class);
        //Product product = (Product)xstream.fromXML(json);*/
        
    }
    
    public void writeShowFile(File file) throws IOException {
        //File file = new File(directory + showFile + ".show");
        System.out.println("writing to" + file.getAbsolutePath());
        if (!file.exists()) {
            file.createNewFile();
        }
        
        FileWriter fw = new FileWriter(file);
        try (BufferedWriter out = new BufferedWriter(fw)) {
            out.write("#Show Name\n");
            out.write(title + "\n");
            
            out.write("#Characters\n");
            for (Role role : characterList) {
                out.write(role.getName() + ">" + role.getActor().getName() + "\n");
            }
        }
        System.out.println("Wrote file.");
    }
    
    
    /**
     * @return the characterList
     */
    public ArrayList<Role> getCharacterList() {
        return characterList;
    }

    /**
     * @return the directory
     */
    public String getDirectory() {
        return directory;
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sort the roles alphabetically
     */
    public void sortRoles() {
        Collections.sort(characterList, new Comparator<Role>() {
            @Override
            public int compare(Role r1, Role r2) {
                return r1.getName().compareTo(r2.getName());
            }
        });
    }
    
    public void addRole(Role role) {
        characterList.add(role);
        sortRoles();
    }
    
    public void deleteRole(int index) {
        characterList.remove(index);
        sortRoles();
    }
}
