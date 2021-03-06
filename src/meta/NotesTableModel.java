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
package meta;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Nathan
 */
public class NotesTableModel extends AbstractTableModel {
    private ArrayList<String> columnNames = new ArrayList();
    private ArrayList<ArrayList> data = new ArrayList();
    
    public void addRow(LineNote note)
    {
        ArrayList<String> list = new ArrayList();
        list.add(note.getPageNum());
        list.add(note.getLine());
        list.add(note.getError());
        list.add(note.getNote());
        data.add(list);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
    
    public void addColumns(ArrayList<String> list) {
        columnNames = list;
    }
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getValueAt(int row, int col) {
        return data.get(row).get(col).toString();
    }
    
    @Override
    public String getColumnName(int col)
    {
        try
        {
            return columnNames.get(col);
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    @Override
    public Class getColumnClass(int c)
    {
        return getValueAt(0, c).getClass();
    }
    
    public LineNote getRow(int index) {
        // pagenum, line, error, note
        ArrayList<String> lines = data.get(index);
        
        // line, error, pagenum, note
        return new LineNote(lines.get(1), lines.get(2), lines.get(0), lines.get(3));
    }
    
    @Override
    public boolean isCellEditable(int row, int col)
    {
        return false;
    }
    
    public void setRowCount(int num) {
        while (data.size() > num) {
            data.remove(0);
        }
    }
    
    public void removeRow(int index) {
        data.remove(index);
        fireTableRowsDeleted(0, data.size() - 1);
    }
}
