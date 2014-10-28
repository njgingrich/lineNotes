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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author Nathan
 */
public class FileInput {
    private final String dir = "C:/Users/Nathan/Documents/Programming/Projects/2014/lineNotes/src/data";
    
    public ArrayList<String> getShowInformation(String fileName) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(dir + "/" + fileName + ".ini"));
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return lines;
    }
}
