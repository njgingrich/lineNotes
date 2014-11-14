/*
 * Copyright (C) 2014 Nathan Gingrich
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

import file.json.JSONReader;
import meta.LineNote;
import meta.Role;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Create a file input for a JSON file, either the show file or saved line notes.
 * @author Nathan Gingrich
 */
public class FileInput {
    BufferedReader in;
    Role role;
    String directory;

    /**
     * Create a new FileInput with the given file
     * @param f the file to input
     * @throws FileNotFoundException
     */
    public FileInput(File f) throws FileNotFoundException {
        this.in = new BufferedReader(new FileReader(f));
        directory = f.getParent();
    }

    /**
     * Create a new FileInput with a directory + role
     * @param directory
     * @param role
     * @throws FileNotFoundException
     */
    public FileInput(String directory, Role role) throws FileNotFoundException {
        this.directory = directory;
        this.role = role;
        String filePath = (directory + role.getName().replaceAll(" ", "") + ".json");
        System.out.println("Path: " + filePath);
        this.in = new BufferedReader(new FileReader(new File(filePath)));
    }
    
    /**
     * Get information from the show file
     * @return An arrayList of strings from the .show file
     * @throws IOException
     */
    public ArrayList<String> getShowInformation() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        for (String line = in.readLine(); line != null; line = in.readLine()) {
            lines.add(line);
        }
        return lines;
    }
    
    /**
     * Return the directory of the file you want input from.
     * @return the directory
     */
    public String getDirectory() {
        return directory;
    }
    
    /**
     * Get line notes previously saved
     * @return an arrayList of the saved LineNotes
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     */
    public ArrayList<LineNote> getSavedLineNotes() throws IOException, ClassNotFoundException, FileNotFoundException {
        JSONReader read = new JSONReader(in);
        return read.readData(role);
    }
}
