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
import file.PDFWriter;
import file.XMLWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultComboBoxModel;
import meta.Role;
import meta.Show;
import meta.LineNote;

/**
 *
 * @author Nathan
 */
public class Interface extends javax.swing.JFrame {
    DefaultListModel characters;
    DefaultListModel notes;
    DefaultComboBoxModel charactersComboBox;
    Show show;
    /**
     * Creates new form Interface
     */
    public Interface() {
        characters = new DefaultListModel<>();
        notes = new DefaultListModel<>();
        charactersComboBox = new DefaultComboBoxModel<>();
        initComponents();
        characterList.addListSelectionListener(characterListListener);
        notesList.addListSelectionListener(notesListListener);
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
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        notesList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        characterList = new javax.swing.JList();
        exportButton = new javax.swing.JButton();
        addNoteButton = new javax.swing.JButton();
        newShowButton = new javax.swing.JButton();
        deleteNoteButton = new javax.swing.JButton();

        jMenuItem1.setText("jMenuItem1");
        noteRightClickMenu.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Line Notes Editor");

        notesList.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        notesList.setModel(notes);
        jScrollPane2.setViewportView(notesList);

        jSplitPane1.setRightComponent(jScrollPane2);

        jScrollPane3.setMinimumSize(new java.awt.Dimension(60, 60));

        characterList.setModel(characters);
        characterList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(characterList);

        jSplitPane1.setLeftComponent(jScrollPane3);

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

        newShowButton.setText("Open Show");
        newShowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newShowButtonActionPerformed(evt);
            }
        });

        deleteNoteButton.setText("Delete Note");
        deleteNoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteNoteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newShowButton)
                        .addGap(203, 203, 203)
                        .addComponent(addNoteButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteNoteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportButton)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                        .addGap(10, 10, 10))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addNoteButton, deleteNoteButton, exportButton, newShowButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportButton)
                    .addComponent(addNoteButton)
                    .addComponent(newShowButton)
                    .addComponent(deleteNoteButton))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addNoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNoteButtonActionPerformed
        //AddLineNote dialog = new AddLineNote(new javax.swing.JFrame(), true);
        for (Role role : show.getCharacterList()) {
            charactersComboBox.addElement(role.getName());
        }
        AddLineNote dialog = new AddLineNote(this, true, charactersComboBox);
        dialog.setVisible(true);
        LineNote noteReturned = dialog.getLineNote();
        String charName = dialog.getCharacterName();
        if (noteReturned == null || charName == null) {
            return;
        }
        
        for (Role role : show.getCharacterList()) {
            if (dialog.getCharacterName().equals(role.getName())) {
                role.addNote(noteReturned);
            }
        }
    }//GEN-LAST:event_addNoteButtonActionPerformed

    private void newShowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newShowButtonActionPerformed
        iniFile = JOptionPane.showInputDialog(null, 
                "Enter the name of the show.", 
                "New Show", 
                JOptionPane.INFORMATION_MESSAGE);
        show = new Show(iniFile);
        for (Role role : show.getCharacterList()) {
            characters.addElement(role.getName());
        }
        
        this.setTitle(getTitle() + " - " + iniFile);
        deleteNoteButton.setEnabled(false);
    }//GEN-LAST:event_newShowButtonActionPerformed

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        // Format the date from the infobox
        String dateString = JOptionPane.showInputDialog(null, 
                                "Enter the date of the rehearsal (MM/DD/(YY)YY).", 
                                "Enter Date", 
                                JOptionPane.INFORMATION_MESSAGE);
        int[] dateArray = {0,0,0};
        String[] splitDate = dateString.split("/");
        for (int i = 0; i < splitDate.length; i++) {
           dateArray[i] = Integer.parseInt(splitDate[i]);
        }
        Calendar calendar = new GregorianCalendar(dateArray[2], dateArray[0], dateArray[1]);
        
        for (Role role : show.getCharacterList()) {
            if (!role.getNotes().isEmpty()) {
                FormatOutputData data = new FormatOutputData();
                try {
                    data.format(show.getCharacterList(), calendar);
                } catch (DocumentException | FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                
                // Save the output for a later date
                XMLWriter writer = new XMLWriter(role.getNotes());
                writer.storeData(role.getName());
            }
        }
    }//GEN-LAST:event_exportButtonActionPerformed

    private void deleteNoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteNoteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteNoteButtonActionPerformed
    
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
    private javax.swing.JButton addNoteButton;
    private javax.swing.JList characterList;
    private javax.swing.JButton deleteNoteButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton newShowButton;
    private javax.swing.JPopupMenu noteRightClickMenu;
    private javax.swing.JList notesList;
    // End of variables declaration//GEN-END:variables
    private String iniFile;
    private ListSelectionListener characterListListener = new ListSelectionListener() {
        @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    notes.clear();
                    
                    String roleName = characterList.getSelectedValue().toString();
                    for (Role role : show.getCharacterList()) {
                        if (role.getName() == roleName) {
                            if (role.getNotes().isEmpty()) {
                                notes.addElement("No notes found.");
                            } else {
                                for (LineNote note : role.getNotes()) {
                                    notes.addElement(note);
                                }
                            }
                        }
                    }
                }
            }
    };
    private ListSelectionListener notesListListener = new ListSelectionListener() {
        @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    // NullPointerException when switching characters unless you check if the notesList is null
                    if (notesList != null || !notesList.getSelectedValue().toString().equals("No notes found.")) {
                        deleteNoteButton.setEnabled(true);
                    }   
                }
            }
    };
}
