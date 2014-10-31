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
import java.util.Calendar;
import meta.LineNote;
import meta.Role;
import org.joda.time.DateTime;

/**
 *
 * @author Nathan
 */
public class FormatOutputData {
    private int noteIX;
    private int errorIX;
    private final Role role;
    private final DateTime date;
    private final PDFWriter writer;
    
    /**
     * Format the data to be outputted to PDF
     * @param role the role
     * @param date the rehearsal date
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public FormatOutputData(Role role, DateTime date) throws FileNotFoundException, DocumentException {
        this.role = role;
        this.date = date;
        writer = new PDFWriter(role, date);
    }
    
    /*
    We need to format the specific error depending on which it is.
    For a wrong order error, we need to split the error string + format it.
    
    For all cases, we need to reinsert the error into the line to make it clear
    what the error is.
    
    To do this, we have to set some attributes in the JSON file so that the text
    will be bold.
    */

    /**
     * Find the section of the line that should be bolded, given an error
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    
    public void format() throws DocumentException, FileNotFoundException {
        writer.makePDFHeader();
        
        for (LineNote note: role.getNotes()) {
            // Split the line into an array, length 4/5 for a word error, length 3 for anything else.
            String[] splitLines = {"", "", "", "", ""};

            switch(note.getNote()) {
                case("Wrong Word"):
                    noteIX = 0;
                    errorIX = note.getLine().indexOf(note.getError());
                    splitLines[0] = note.getLine().substring(0, errorIX);
                    splitLines[1] = note.getLine().substring(errorIX, errorIX + note.getError().length()); // The error
                    splitLines[2] = note.getLine().substring(errorIX + note.getError().length());
                    break;
                    
                case("Wrong Order"):
                    noteIX = 1;

                    String[] splitError = note.getError().split("\\|");
                    errorIX = note.getLine().indexOf(splitError[0]);
                    int error2IX = note.getLine().indexOf(splitError[1]);

                    splitLines[0] = note.getLine().substring(0, errorIX);
                    splitLines[1] = "[" + note.getLine().substring(errorIX, errorIX + splitError[0].length()) + "]"; // Error #1
                    splitLines[2] = note.getLine().substring(errorIX + splitError[0].length(), error2IX); // Will be "" if errors are adjacent
                    splitLines[3] = "[" + note.getLine().substring(error2IX, error2IX + splitError[1].length()) + "]"; // Error #2
                    splitLines[4] = note.getLine().substring(error2IX + splitError[1].length());
                    break;
                    
                case("Dropped"):
                    noteIX = 2;
                    errorIX = note.getLine().indexOf(note.getError());
                    splitLines[0] = note.getLine().substring(0, errorIX);
                    splitLines[1] = note.getLine().substring(errorIX, errorIX + note.getError().length()); // The error
                    splitLines[2] = note.getLine().substring(errorIX + note.getError().length());
                    break;
                    
                case("Added"):
                    noteIX = 3;
                    /*
                    Error format:
                    "error, before|after"
                    */
                    String[] parts = note.getError().split(",");
                    String error = " (" + parts[0] + ") ";
                    String[] splitWords = parts[1].split("\\|");
                    int wordEndIX = note.getLine().indexOf(splitWords[0]) + splitWords[0].length();
                    int word2IX = note.getLine().indexOf(splitWords[1]);
                    errorIX = note.getLine().indexOf(wordEndIX) + 1;
                    
                    splitLines[0] = note.getLine().substring(0, wordEndIX);
                    splitLines[1] = error; // The error
                    splitLines[2] = note.getLine().substring(word2IX);
                    break;
                    
                case("Called Line"):
                    noteIX = 4;
                    splitLines[0] = note.getLine();
                    break;
                    
                case("Check Line"):
                    noteIX = 5;
                    splitLines[0] = note.getLine();
                    break;
                    
                case("Jumped Line"):
                    noteIX = 6;
                    splitLines[0] = "";
                    splitLines[1] = "(" + note.getError() + ")"; // The error
                    splitLines[2] = note.getLine();
                    break;
            }
            writer.outputToPDF(note.getPageNum(), noteIX, splitLines);
        }
        writer.closePDF();
    }
}
