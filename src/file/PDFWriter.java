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

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import meta.LineNote;
import meta.Role;

/**
 *
 * @author Nathan
 */
public class PDFWriter {
    
    /*
    IMPORTANT:
    Need to convert this function to use a Role, so you can get the character name
    */
    public void outputToPDF(Role role, String[] chunks, int errorIX) 
            throws DocumentException, FileNotFoundException {
        File file = new File("test.pdf");
        FileOutputStream fileout = new FileOutputStream(file);
        Document document = new Document();
        PdfWriter.getInstance(document, fileout);
        document.addAuthor("Nathan Gingrich");
        document.addTitle("Line Notes");

        document.open();
        Font lineFont = new Font(FontFamily.TIMES_ROMAN);
        lineFont.setStyle(Font.UNDERLINE);
        Font errorFont = new Font(FontFamily.TIMES_ROMAN);
        errorFont.setStyle(Font.ITALIC);
        
        Paragraph header = new Paragraph();
        Chunk dateTitle = new Chunk("Date: ");
        Chunk date = new Chunk(" 10/27 ");
        date.setFont(lineFont);
        Chunk nameTitle = new Chunk("Character: ");
        Chunk name = new Chunk(" " + role.getName() + " ");
        name.setFont(lineFont);
        Chunk spacer  = new Chunk("                                                         ");
        
        header.add(dateTitle);
        header.add(date);
        header.add(spacer);
        header.add(nameTitle);
        header.add(name);
        document.add(header);
        document.add(Chunk.NEWLINE);
        
        for (LineNote note : role.getNotes()) {
            Paragraph line = new Paragraph("Line: ", lineFont);
            line.add(note.getLine());
            document.add(line);
            document.add(Chunk.NEWLINE);

            for (int i = 0; i < chunks.length; i++) {
                Chunk chunk = new Chunk(chunks[i]);
                errorFont.setStyle(Font.ITALIC);
                if (i == errorIX) {
                    errorFont.setStyle(Font.BOLDITALIC);
                }
                chunk.setFont(errorFont);
                document.add(chunk);
                if (i != chunks.length-1) {
                    document.add(new Chunk("    "));
                }
            }
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
        }
        document.close();
    }
}
