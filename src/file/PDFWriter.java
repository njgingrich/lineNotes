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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import meta.LineNote;
import meta.Role;

/**
 *
 * @author Nathan
 */
public class PDFWriter {
    private String directory = "C:/Users/Nathan/Documents/Programming/Projects/2014/lineNotes/src/data/notes/";
    private String[] errorOutput = new String[] {"Wrong Word", "Wrong Order", "Dropped", "Added", "Called Line", "Check Line", "Jumped Line"};
    Document document;
    Role role;
    Calendar calendar;
    Font lineFont = new Font(FontFamily.TIMES_ROMAN);
    Font lineFontUnderline = new Font(FontFamily.TIMES_ROMAN);
    Font errorFont = new Font(FontFamily.TIMES_ROMAN);
    Font lineFontBold = new Font(FontFamily.TIMES_ROMAN);
    Font errorFontBold = new Font(FontFamily.TIMES_ROMAN);

    public PDFWriter(Role role, Calendar calendar) throws FileNotFoundException, DocumentException {
        this.role = role;
        this.calendar = calendar;
        lineFontUnderline.setStyle(Font.UNDERLINE);
        lineFontBold.setStyle(Font.BOLD);
        errorFont.setStyle(Font.ITALIC);
        errorFontBold.setStyle(Font.BOLDITALIC);
        
        File file = new File(directory + 
                             "UT_LineNotes" + 
                             role.getName().replaceAll(" ", "") + 
                             calendar.get(Calendar.MONTH) + "." +
                             calendar.get(Calendar.DAY_OF_MONTH) + "." +
                             calendar.get(Calendar.YEAR) +
                             ".pdf");
        FileOutputStream fileout = new FileOutputStream(file);
        document = new Document();
        PdfWriter.getInstance(document, fileout);
        document.addAuthor("Nathan Gingrich");
        document.addTitle("Line Notes = " + role.getName());
        document.open();
    }
    
    public void outputToPDF(String page, int errorIX, String[] splitLines) 
            throws DocumentException, FileNotFoundException {
        
        /*
        Using a table so the layout works better
        */                
        System.out.println("Outputting data for note. (for loop within outputToPDF()");

        PdfPTable lineTable = new PdfPTable(2);
        PdfPCell pageCell = new PdfPCell(new Paragraph("Page: " + page, lineFont));
        pageCell.setPadding(20);
        pageCell.setPaddingLeft(0);
        pageCell.setBorder(PdfPCell.NO_BORDER);

        System.out.println("SplitLines within outputToPDF() for loop");
        for (String splitLine : splitLines) {
            System.out.println(splitLine);
        }
        /*PdfPCell lineCell = new PdfPCell(new Paragraph("Line: " + note.getLine() 
                + "                                                            ", lineFontUnderline));*/
        Chunk lineSegment1 = new Chunk(splitLines[0]); 
        lineSegment1.setFont(lineFont);
        Chunk error1 = new Chunk(splitLines[1]);
        error1.setFont(lineFontBold);
        Chunk lineSegment2 = new Chunk(splitLines[2]);
        lineSegment2.setFont(lineFont);
        Chunk error2 = new Chunk(splitLines[3]);
        error2.setFont(lineFontBold);
        Chunk lineSegment3 = new Chunk(splitLines[4]);
        lineSegment3.setFont(lineFont); 
        
        Phrase line = new Phrase(lineSegment1);
        line.add(error1);
        line.add(lineSegment2);
        line.add(error2);
        line.add(lineSegment3);

        PdfPCell lineCell = new PdfPCell(line);
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
        for (int i = 0; i < errorOutput.length; i++) {
            errorFont.setStyle(Font.ITALIC);
            PdfPCell cell;
            if (i == errorIX) {
                cell = new PdfPCell(new Paragraph(errorOutput[i], errorFontBold));
            } else {
                cell = new PdfPCell(new Paragraph(errorOutput[i], errorFont));
            }
            cell.setBorder(PdfPCell.NO_BORDER);
            errorTable.addCell(cell);
        }
        document.add(errorTable);
    }
    
    public void makePDFHeader() throws DocumentException {
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);
        PdfPCell dateCell = new PdfPCell(new Paragraph("Date: " +
                                                       calendar.get(Calendar.MONTH) + "/" +
                                                       calendar.get(Calendar.DAY_OF_MONTH), lineFont));
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
    }
    
    public void closePDF() {
        document.close();
    }
}
