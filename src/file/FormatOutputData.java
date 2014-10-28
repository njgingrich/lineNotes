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

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import meta.LineNote;
import meta.Role;

/**
 *
 * @author Nathan
 */
public class FormatOutputData {
    private String[] errorOutput = new String[] {"Wrong Word", "Wrong Order", "Dropped", "Added", "Called Line", "Check Complete Line", "Jumped Line"};
    private int errorIX;
    
    public FormatOutputData() {
    }
    
    /*
    We need to format the specific error depending on which it is.
    For a wrong order error, we need to split the error string + format it.
    
    For all cases, we need to reinsert the error into the line to make it clear
    what the error is.
    
    To do this, we have to set some attributes in the JSON file so that the text
    will be bold.
    */
    public void format(ArrayList<Role> characters) throws DocumentException, FileNotFoundException {
        PDFWriter writer = new PDFWriter();
        for (Role role : characters) {
            for (LineNote note: role.getNotes()) {
                switch(note.getNote()) {
                    case("Wrong Word"):
                        errorIX = 0;
                        break;
                    case("Wrong Order"):
                        errorIX = 1;
                        break;
                    case("Dropped"):
                        errorIX = 2;
                        break;
                    case("Added"):
                        errorIX = 3;
                        break;
                    case("Called Line"):
                        errorIX = 4;
                        break;
                    case("Check Line"):
                        errorIX = 5;
                        break;
                    case("Jumped Line"):
                        errorIX = 6;
                        break;
                }
            writer.outputToPDF(role, errorOutput, errorIX);
            }
        }
    }
}
