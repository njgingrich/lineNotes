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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * For outputting data to be saved
 * @author Nathan Gingrich
 */
public class FileOutput {    
    /**
     * Save the data to disk to the given file
     * @param fileName
     * @param data
     */
    public void writeFile(String fileName, String data) {
        try {
            File file = new File(fileName);
            FileWriter fstream = new FileWriter(file, true);
            try (BufferedWriter out = new BufferedWriter(fstream)) {
                out.write(data);
                out.write("\n");
                out.flush();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
