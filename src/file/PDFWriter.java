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
import meta.Role;
import org.joda.time.DateTime;

/**
 *
 * @author Nathan
 */
public class PDFWriter {
    private final String directory;
    private final String[] errorOutput = new String[] {"Wrong Word", "Wrong Order", "Dropped", "Added", "Called Line", "Check Line", "Jumped Line"};
    Document document;
    Role role;
    DateTime date;
    Font lineFont;
    Font lineFontUnderline;
    Font errorFont;
    Font lineFontBold;
    Font errorFontBold;

    /**
     * Create a PDF Writer to write line notes to disk
     * @param role the role
     * @param date the rehearsal date
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    public PDFWriter(Role role, DateTime date, String title) throws FileNotFoundException, DocumentException {
        this.role = role;
        this.date = date;
        directory = "out/" + title + "/notes/";
        setFontStyles();
        
        System.out.println(directory + 
                             "UT_LineNotes" + 
                             role.getName().replaceAll(" ", "") + 
                             date.monthOfYear().getAsString() + "-" +
                             date.dayOfMonth().getAsString() + "-" +
                             date.year().get() + ".pdf");
        File file = new File(directory + 
                             "UT_LineNotes" + 
                             role.getName().replaceAll(" ", "") + 
                             date.monthOfYear().getAsString() + "-" +
                             date.dayOfMonth().getAsString() + "-" +
                             date.year().get() + ".pdf");
        FileOutputStream fileout = new FileOutputStream(file);
        document = new Document();
        PdfWriter.getInstance(document, fileout);
        document.addAuthor("Nathan Gingrich");
        document.addTitle("Line Notes " + role.getName());
        document.open();
    }

    private void setFontStyles() {
        lineFont = new Font(FontFamily.TIMES_ROMAN);
        lineFontUnderline = new Font(FontFamily.TIMES_ROMAN);
        errorFont = new Font(FontFamily.TIMES_ROMAN);
        lineFontBold = new Font(FontFamily.TIMES_ROMAN);
        errorFontBold = new Font(FontFamily.TIMES_ROMAN);
        lineFontUnderline.setStyle(Font.UNDERLINE);
        lineFontBold.setStyle(Font.BOLD);
        errorFont.setStyle(Font.ITALIC);
        errorFontBold.setStyle(Font.BOLDITALIC);
    }
    
    /**
     * Output the data to a PDF
     * @param page the page number of the line error
     * @param errorIX the index of the error
     * @param splitLines the array of lines with the error bolded
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    public void outputToPDF(String page, int errorIX, String[] splitLines) 
            throws DocumentException, FileNotFoundException {

        addLineTableToPDF(splitLines, page);
        addErrorTableToPDF(errorIX);
    }

    private void addErrorTableToPDF(int errorIX) throws DocumentException {
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

    private void addLineTableToPDF(String[] splitLines, String page) throws DocumentException {
        PdfPTable lineTable = new PdfPTable(2);
        PdfPCell pageCell = new PdfPCell(new Paragraph("Page: " + page, lineFont));
        pageCell.setPadding(20);
        pageCell.setPaddingLeft(0);
        pageCell.setBorder(PdfPCell.NO_BORDER);
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
    }
    
    /**
     * Make the initial header to the PDF, with the date and character
     * @throws DocumentException
     */
    public void makePDFHeader() throws DocumentException {
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);
                
        PdfPCell dateCell = new PdfPCell(new Paragraph("Date: " +
                                                       date.monthOfYear().getAsString() + "/" +
                                                       date.dayOfMonth().getAsString(), lineFont));
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
    
    /**
     * Close the PDF
     */
    public void closePDF() {
        document.close();
    }
}
