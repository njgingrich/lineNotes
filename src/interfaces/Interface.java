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
package interfaces;

import com.itextpdf.text.DocumentException;
import file.FormatOutputData;
import file.ShowFileFilter;
import file.json.JSONWriter;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import meta.Role;
import meta.Show;
import meta.LineNote;
import meta.NotesTableModel;
import org.joda.time.DateTime;
import preferences.Preferences;

/**
 * TO DO:
 * Add edit note functionality (include a delete button, just bring up addNote dialog + edit it)
 * Change error dialogs to be easier to use
 * Get inline error styling when displaying errors?
 * When sorting, you have to check for line + page number if they're the same
 * Fix nullPointerExceptions when clicking on buttons you shouldn't be
 * Make output modular
 */

/**
 *
 * @author Nathan
 */
public class Interface extends javax.swing.JFrame {
    private DefaultListModel characters;
    private DefaultTableModel notes;
    private DefaultComboBoxModel charactersComboBox;
    private Show show;
    private File showFile;
    private Preferences prefs;
    private NotesTableModel notesTableModel;
    
    /**
     * Creates new form Interface
     */
    public Interface() {
        setup();
        initComponents();
        characterList.addListSelectionListener(characterListListener);
        
        notesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                JTable table =(JTable)me.getSource();
                Point p = me.getPoint();
                int row = table.rowAtPoint(p);
                if (me.getClickCount() == 2) {
                    editLineNote(notesTableModel.getRow(row), characterList.getSelectedValue().toString());
                }
            }
        });
        notesTable.getColumn("Page").setMaxWidth(40);
        notesTable.getColumn("Page").setMinWidth(40);
        notesTable.getColumn("Line").setPreferredWidth(300);
        notesTable.getColumn("Error").setPreferredWidth(150);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        noteRightClickMenu = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        saveFileChooser = new javax.swing.JFileChooser();
        openFileChooser = new javax.swing.JFileChooser(".");
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        characterList = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        notesTable = new javax.swing.JTable();
        exportButton = new javax.swing.JButton();
        addNoteButton = new javax.swing.JButton();
        deleteNoteButton = new javax.swing.JButton();
        testerButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newShowMenuButton = new javax.swing.JMenuItem();
        openShowMenuButton = new javax.swing.JMenuItem();
        saveShowMenuButton = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        optionsMenuButton = new javax.swing.JMenuItem();
        exitMenuButton = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        addNewNoteMenuButton = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exportShowMenuButton = new javax.swing.JMenuItem();
        showMenu = new javax.swing.JMenu();
        addNewCharacterMenuButton = new javax.swing.JMenuItem();
        deleteCharacterMenuButton = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");
        noteRightClickMenu.add(jMenuItem1);

        openFileChooser.setFileFilter(new ShowFileFilter());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Line Notes Editor");

        jScrollPane3.setMinimumSize(new java.awt.Dimension(60, 60));

        characterList.setModel(characters);
        characterList.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        characterList.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        characterList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(characterList);

        jSplitPane1.setLeftComponent(jScrollPane3);

        notesTable.setAutoCreateRowSorter(true);
        notesTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        notesTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        notesTableModel = new NotesTableModel();
        ArrayList<String> list = new ArrayList<>();
        list.add("Page");
        list.add("Line");
        list.add("Error");
        list.add("Note");
        notesTableModel.addColumns(list);
        notesTable.setModel(notesTableModel);
        notesTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        notesTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(notesTable);
        notesTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jSplitPane1.setRightComponent(jScrollPane1);

        exportButton.setText("Export Notes");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        addNoteButton.setText("Add New Note");
        addNoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNoteButtonActionPerformed(evt);
            }
        });

        deleteNoteButton.setText("Delete Note");
        deleteNoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteNoteButtonActionPerformed(evt);
            }
        });

        testerButton.setText("Test Tomfoolery");
        testerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testerButtonActionPerformed(evt);
            }
        });

        fileMenu.setText("File");

        newShowMenuButton.setText("New Show");
        fileMenu.add(newShowMenuButton);

        openShowMenuButton.setText("Open Show...");
        openShowMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openShowMenuButtonActionPerformed(evt);
            }
        });
        fileMenu.add(openShowMenuButton);

        saveShowMenuButton.setText("Save Show");
        saveShowMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveShowMenuButtonActionPerformed(evt);
            }
        });
        fileMenu.add(saveShowMenuButton);
        fileMenu.add(jSeparator1);

        optionsMenuButton.setText("Options...");
        optionsMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionsMenuButtonActionPerformed(evt);
            }
        });
        fileMenu.add(optionsMenuButton);

        exitMenuButton.setText("Exit");
        exitMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuButtonActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuButton);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        addNewNoteMenuButton.setText("Add New Note...");
        editMenu.add(addNewNoteMenuButton);
        editMenu.add(jSeparator2);

        exportShowMenuButton.setText("Export Show");
        exportShowMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportShowMenuButtonActionPerformed(evt);
            }
        });
        editMenu.add(exportShowMenuButton);

        menuBar.add(editMenu);

        showMenu.setText("Show");

        addNewCharacterMenuButton.setText("Add New Character");
        addNewCharacterMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewCharacterMenuButtonActionPerformed(evt);
            }
        });
        showMenu.add(addNewCharacterMenuButton);

        deleteCharacterMenuButton.setText("Delete Character");
        deleteCharacterMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCharacterMenuButtonActionPerformed(evt);
            }
        });
        showMenu.add(deleteCharacterMenuButton);

        menuBar.add(showMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(testerButton)
                        .addGap(192, 192, 192)
                        .addComponent(addNoteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteNoteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportButton)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                        .addGap(10, 10, 10))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addNoteButton, deleteNoteButton, exportButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportButton)
                    .addComponent(addNoteButton)
                    .addComponent(deleteNoteButton)
                    .addComponent(testerButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addNoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNoteButtonActionPerformed
        for (Role role : show.getCharacterList()) {
            charactersComboBox.addElement(role.getName());
        }
        LineNoteDialog dialog = new LineNoteDialog(this, true, charactersComboBox);
        dialog.setVisible(true);
        addNote(dialog);
    }//GEN-LAST:event_addNoteButtonActionPerformed

    private boolean addNote(LineNoteDialog dialog) {
        LineNote noteReturned = dialog.getLineNote();
        String charName = dialog.getCharacterName();
        if (noteReturned == null || charName == null) {
            return true;
        }
        for (Role role : show.getCharacterList()) {
            if (dialog.getCharacterName().equals(role.getName())) {
                role.addNote(noteReturned);
                if (role.getNotes().size() == 1) {
                    //notes.removeElementAt(0);
                }
                if (charName.equals(role.getName())) {
                    //notes.addElement(noteReturned);
                    notesTableModel.addRow(noteReturned);
                }
                role.sortNotes();
            }
        }
        if(prefs.Autosave()) {
            saveShow(showFile);
        }
        return false;
    }

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        exportAction();
    }//GEN-LAST:event_exportButtonActionPerformed

    private void exportAction() throws HeadlessException {
        int errors = 0;
        try {
            DateTime dt = getDateInput();
            errors = exportData(dt);
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            JOptionPane.showMessageDialog(this, "" + errors + " notes outputted.", "Export Successful", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteNoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteNoteButtonActionPerformed
        for (Role role : show.getCharacterList()) {
            if (role.getName().equals(characterList.getSelectedValue().toString())) {
                int index = notesTable.getSelectedRow();
                deleteNote(role, index);
            }
        }
    }//GEN-LAST:event_deleteNoteButtonActionPerformed

    private void deleteNote(Role role, int index) {
        role.removeNote(index);
        notesTableModel.removeRow(index);
        role.sortNotes();
        notesTable.setModel(notes);
    }

    private void openShowMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openShowMenuButtonActionPerformed
        openFileChooser.setCurrentDirectory(new File("./out/"));
        openFileChooser.showOpenDialog(this);
        showFile = openFileChooser.getSelectedFile();
        try {
            show = new Show(showFile);
            show.sortRoles();
        } catch (ClassNotFoundException | FileNotFoundException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Role role : show.getCharacterList()) {
            characters.addElement(role.getName());
        }
    }//GEN-LAST:event_openShowMenuButtonActionPerformed

    private void exitMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuButtonActionPerformed

    private void saveShowMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveShowMenuButtonActionPerformed
        /*
        Only exports the XML data for characters atm, will support .show saving
        */
        saveFileChooser.setCurrentDirectory(new File(show.getDirectory()));
        int returnVal = saveFileChooser.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            saveShow(saveFileChooser.getSelectedFile());
        }
    }//GEN-LAST:event_saveShowMenuButtonActionPerformed

    private void saveShow(File f) {
        try {
            show.writeShowFile(f);
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        exportAction();
    }

    private void exportShowMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportShowMenuButtonActionPerformed
        exportAction();
    }//GEN-LAST:event_exportShowMenuButtonActionPerformed

    private void addNewCharacterMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewCharacterMenuButtonActionPerformed
        // TODO add your handling code here:
        /**
         * Bring up a dialog with boxes for First, Last names + the Role name
         */
        AddCharacter dialog = new AddCharacter(this, true);
        dialog.setVisible(true);
        Role role = dialog.getRole();
        
        show.addRole(role);
        characters.addElement(role.getName());
        show.sortRoles();
    }//GEN-LAST:event_addNewCharacterMenuButtonActionPerformed

    private void deleteCharacterMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCharacterMenuButtonActionPerformed
        // TODO add your handling code here:
        /**
         * Bring up a dialog with a box for the role name
         */
        String response = JOptionPane.showInputDialog(this, 
                                                      "Enter the character name to delete.", 
                                                      "Delete Role", 
                                                      JOptionPane.INFORMATION_MESSAGE);
        ArrayList<Role> newRoles = new ArrayList<>(show.getCharacterList());
        Iterator<Role> itr = newRoles.iterator();
        while (itr.hasNext()) {
            if (itr.next().getName().equals(response)) {
                characters.removeElement(itr);
                itr.remove();
                break;
            }
        }
        show.sortRoles();
    }//GEN-LAST:event_deleteCharacterMenuButtonActionPerformed

    private void optionsMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionsMenuButtonActionPerformed
        optionMenu menu = new optionMenu(this, true);
        menu.setVisible(true);
        
        
        
    }//GEN-LAST:event_optionsMenuButtonActionPerformed

    private void testerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testerButtonActionPerformed
        DateTime dt = getDateInput();
        
        for (Role role : show.getCharacterList()) {
            // Only make an output file if there are lines to be written.
            if (!role.getNotes().isEmpty()) {
                JSONWriter writer = new JSONWriter(role.getNotes(), show.getDirectory(), show.getTitle());
                try {
                    System.out.println("Storing JSON data.");
                    writer.storeJsonData(role.getName());
                } catch (IOException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_testerButtonActionPerformed
    
    private int exportData(DateTime dt) throws IOException, FileNotFoundException, DocumentException {
        int errors = 0;
        
        for (Role role : show.getCharacterList()) {
            // Only make an output file if there are lines to be written.
            if (!role.getNotes().isEmpty()) {
                FormatOutputData data;
                data = new FormatOutputData(role, dt, show.getTitle());
                errors += data.format();
                
                // Save the output for a later date
                JSONWriter writer = new JSONWriter(role.getNotes(), show.getDirectory(), show.getTitle());
                writer.storeJsonData(role.getName());
            }
        }
        return errors;
    }

    private DateTime getDateInput() throws NumberFormatException, HeadlessException {
        String dateString = JOptionPane.showInputDialog(this,
                "Enter the date of the rehearsal (MM/DD/(YY)YY).",
                "Enter Date",
                JOptionPane.INFORMATION_MESSAGE);
        int[] dateArray = {0,0,0};
        String[] splitDate = dateString.split("/");
        for (int i = 0; i < splitDate.length; i++) {
            dateArray[i] = Integer.parseInt(splitDate[i]);
        }
        DateTime dt = new DateTime(dateArray[2], dateArray[0], dateArray[1], 0, 0);
        return dt;
    }
    
    public void editLineNote(LineNote note, String character) {
        LineNoteDialog dialog = new LineNoteDialog(this, true, charactersComboBox, note, character);
        dialog.setVisible(true);
        addNote(dialog);
        for (Role role : show.getCharacterList()) {
            if (role.getName().equals(character)) {
                int index = notesTable.getSelectedRow();
                deleteNote(role, index);
            }
        }
    }
        
    private void setup() {
        characters = new DefaultListModel<>();
        // Setup up the NotesTable with columns
        
        charactersComboBox = new DefaultComboBoxModel<>();
        
        prefs = new Preferences(true, true, false, "hope.edu"); // replace when you load the json show file
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addNewCharacterMenuButton;
    private javax.swing.JMenuItem addNewNoteMenuButton;
    private javax.swing.JButton addNoteButton;
    private javax.swing.JList characterList;
    private javax.swing.JMenuItem deleteCharacterMenuButton;
    private javax.swing.JButton deleteNoteButton;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JMenuItem exportShowMenuButton;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newShowMenuButton;
    private javax.swing.JPopupMenu noteRightClickMenu;
    private javax.swing.JTable notesTable;
    private javax.swing.JFileChooser openFileChooser;
    private javax.swing.JMenuItem openShowMenuButton;
    private javax.swing.JMenuItem optionsMenuButton;
    private javax.swing.JFileChooser saveFileChooser;
    private javax.swing.JMenuItem saveShowMenuButton;
    private javax.swing.JMenu showMenu;
    private javax.swing.JButton testerButton;
    // End of variables declaration//GEN-END:variables
    private final ListSelectionListener characterListListener = new ListSelectionListener() {
        @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    notesTableModel.setRowCount(0);
                    
                    String roleName = characterList.getSelectedValue().toString();
                    for (Role role : show.getCharacterList()) {
                        if (role.getName().equals(roleName)) {
                            if (!role.getNotes().isEmpty()) {
                                role.sortNotes();
                                for (LineNote note : role.getNotes()) {
                                    notesTableModel.addRow(note);
                                    
                                }
                            }
                        }
                    }
                }
            }
    };
    private final TableModelListener notesTableListener = new TableModelListener() {
        @Override
        public void tableChanged(TableModelEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    };
}
