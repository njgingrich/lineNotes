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

import meta.LineNote;

/**
 *
 * @author Nathan
 */
public class FormatOutputData {
    private String boldText = " {\"style\":\"bold\", }";
    private String boldChunk = "[\"chunk\"," + boldText;
    private String pdfMetaData = "{\"right-margin\":50,\n" +
                                 "\"subject\":\"Urinetown Line Notes\",\n" +
                                 "\"creator\":\"Nathan Gingrich\",\n" +
                                 "\"doc-header\":[\"Line Notes\"],\n" +
                                 "\"bottom-margin\":55,\n" +
                                 "\"author\":\"John Doe\",\n" +
                                 "\"header\":\"page header\",\n" +
                                 "\"left-margin\":50,\n" +
                                 "\"title\":\"LineNotes\",\n" +
                                 "\"size\":\"a4\",\n" +
                                 "\"footer\":\"page\",\n" +
                                 "\"top-margin\":50},\n";
    private String errorOutput;
    
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
    public String format(LineNote note) {
        switch(note.getNote()) {
            case("Wrong Word"):
                // First, find the error within the line and boldify it.
                String line = note.getLine();
                /*int errorLocation = line.indexOf(note.getNote());
                line = new StringBuilder(line).insert(errorLocation, ", " + boldChunk + note.getError() + "], \"").toString();*/
                line = line.replace(note.getError(), ", " + boldChunk + note.getError() + "], \"");
                
                // Then, make sure the output of the possible notes has the
                // correct one bolded.
                errorOutput = "[\"phrase\", "
                            + "[\"chunk\", {\"style\":\"bold\"}, \"Wrong Word    \"], "
                            + "[\"chunk\", \"Wrong Order    \"], "
                            + "[\"chunk\", \"Dropped    \"], "
                            + "[\"chunk\", \"Added    \"], "
                            + "[\"chunk\", \"Called Line    \"], "
                            + "[\"chunk\", \"Check Complete Line    \"], "
                            + "[\"chunk\", \"Jumped Line\"]";
                break;
            case("Wrong Order"):
                errorOutput = "[\"phrase\", "
                            + "[\"chunk\", \"Wrong Word    \"], "
                            + "[\"chunk\", {\"style\":\"bold\"}, \"Wrong Order    \"], "
                            + "[\"chunk\", \"Dropped    \"], "
                            + "[\"chunk\", \"Added    \"], "
                            + "[\"chunk\", \"Called Line    \"], "
                            + "[\"chunk\", \"Check Complete Line    \"], "
                            + "[\"chunk\", \"Jumped Line\"]";
                break;
            case("Dropped"):
                
                errorOutput = "[\"phrase\", "
                            + "[\"chunk\", \"Wrong Word    \"], "
                            + "[\"chunk\", \"Wrong Order    \"], "
                            + "[\"chunk\", {\"style\":\"bold\"}, \"Dropped    \"], "
                            + "[\"chunk\", \"Added    \"], "
                            + "[\"chunk\", \"Called Line    \"], "
                            + "[\"chunk\", \"Check Complete Line    \"], "
                            + "[\"chunk\", \"Jumped Line\"]";
                break;
            case("Added"):
                errorOutput = "[\"phrase\", "
                            + "[\"chunk\", \"Wrong Word    \"], "
                            + "[\"chunk\", \"Wrong Order    \"], "
                            + "[\"chunk\", \"Dropped    \"], "
                            + "[\"chunk\", {\"style\":\"bold\"}, \"Added    \"], "
                            + "[\"chunk\", \"Called Line    \"], "
                            + "[\"chunk\", \"Check Complete Line    \"], "
                            + "[\"chunk\", \"Jumped Line\"]";
                break;
            case("Called Line"):
                errorOutput = "[\"phrase\", "
                            + "[\"chunk\", \"Wrong Word    \"], "
                            + "[\"chunk\", \"Wrong Order    \"], "
                            + "[\"chunk\", \"Dropped    \"], "
                            + "[\"chunk\", \"Added    \"], "
                            + "[\"chunk\", {\"style\":\"bold\"}, \"Called Line    \"], "
                            + "[\"chunk\", \"Check Complete Line    \"], "
                            + "[\"chunk\", \"Jumped Line\"]";
                break;
            case("Check Line"):
                errorOutput = "[\"phrase\", "
                            + "[\"chunk\", \"Wrong Word    \"], "
                            + "[\"chunk\", \"Wrong Order    \"], "
                            + "[\"chunk\", \"Dropped    \"], "
                            + "[\"chunk\", \"Added    \"], "
                            + "[\"chunk\", \"Called Line    \"], "
                            + "[\"chunk\", {\"style\":\"bold\"}, \"Check Complete Line    \"], "
                            + "[\"chunk\", \"Jumped Line\"]";
                break;
            case("Jumped Line"):
                errorOutput = "[\"phrase\", "
                            + "[\"chunk\", \"Wrong Word    \"], "
                            + "[\"chunk\", \"Wrong Order    \"], "
                            + "[\"chunk\", \"Dropped    \"], "
                            + "[\"chunk\", \"Added    \"], "
                            + "[\"chunk\", \"Called Line    \"], "
                            + "[\"chunk\", \"Check Complete Line    \"], "
                            + "[\"chunk\", {\"style\":\"bold\"}, \"Jumped Line\"]";
                break;
        }
    
    
    return pdfMetaData + errorOutput;
    }
}
