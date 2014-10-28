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
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
        Font lineFontUnderline = new Font(FontFamily.TIMES_ROMAN);
        lineFontUnderline.setStyle(Font.UNDERLINE);
        Font errorFont = new Font(FontFamily.TIMES_ROMAN);
        errorFont.setStyle(Font.ITALIC);
        Font errorFontBold = new Font(FontFamily.TIMES_ROMAN);
        errorFontBold.setStyle(Font.BOLDITALIC);
        
        /*
        Trying it the normal way
        */
        /*Paragraph header = new Paragraph();
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
            Paragraph line = new Paragraph("Page: " + note.getPageNum() + "      Line: ", lineFont);
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
        }*/
        
        /*
        Trying to use a table so the layout works better
        */
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);
        PdfPCell dateCell = new PdfPCell(new Paragraph("Date: 10/27", lineFont));
        dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateCell.setBorder(PdfPCell.NO_BORDER);
        dateCell.setPadding(0.3f);
        PdfPCell characterCell = new PdfPCell(new Paragraph("Character: " + role.getName(), lineFont));
        characterCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        characterCell.setBorder(PdfPCell.NO_BORDER);
        
        headerTable.addCell(dateCell);
        headerTable.addCell(characterCell);
        document.add(headerTable);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        
        
        for (LineNote note : role.getNotes()) {
            PdfPTable lineTable = new PdfPTable(2);
            PdfPCell pageCell = new PdfPCell(new Paragraph("Page: " + note.getPageNum(), lineFont));
            pageCell.setPadding(20);
            pageCell.setPaddingLeft(0);
            pageCell.setBorder(PdfPCell.NO_BORDER);
            PdfPCell lineCell = new PdfPCell(new Paragraph("Line: " + note.getLine(), lineFontUnderline));
            lineCell.setPadding(20);
            lineCell.setBorder(PdfPCell.NO_BORDER);
            lineTable.setWidthPercentage(100);
            lineTable.setWidths(new int[] {2, 10});
            lineTable.setSpacingBefore(1f);
            lineTable.setSpacingAfter(1f);
            
            lineTable.addCell(pageCell);
            lineTable.addCell(lineCell);
            document.add(lineTable);
            document.add(Chunk.NEWLINE);

            
            PdfPTable errorTable = new PdfPTable(7);
            errorTable.setWidthPercentage(100);
            for (int i = 0; i < chunks.length; i++) {
                errorFont.setStyle(Font.ITALIC);
                PdfPCell cell;
                if (i == errorIX) {
                    System.out.println(chunks[i]);
                    cell = new PdfPCell(new Paragraph(chunks[i], errorFontBold));
                } else {
                    cell = new PdfPCell(new Paragraph(chunks[i], errorFont));
                }
                cell.setBorder(PdfPCell.NO_BORDER);
                errorTable.addCell(cell);
            }
            document.add(errorTable);
        }
        
        document.close();
    }
}
