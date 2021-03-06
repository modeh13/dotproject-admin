/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.util.Date;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ItemEvent;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import java.util.logging.Logger;

import Global.Session;
import Models.Dao.UserDotDao;
import Models.Dao.UserTableModel;
import Models.User;
import Models.UserDot;
import Models.Dao.UserDao;
import Util.Utilities;
import Util.Resources;

/**
 *
 * @author german.ramirez
 */
public class jUsersPanel extends javax.swing.JPanel {

    /**
     * Creates new form JPanelUsuarios
     */
    public jUsersPanel() {
        initComponents();
        initComponentsAditional();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMainPanel = new javax.swing.JPanel();
        jScrollPaneTable = new javax.swing.JScrollPane();
        jTableUsers = new javax.swing.JTable();
        jPaneForm = new javax.swing.JPanel();
        jLblForm = new javax.swing.JLabel();
        jLblId = new javax.swing.JLabel();
        jTxtId = new javax.swing.JTextField();
        jLblDotId = new javax.swing.JLabel();
        jTxtDotId = new javax.swing.JTextField();
        jChkState = new javax.swing.JCheckBox();
        jLblUsername = new javax.swing.JLabel();
        jLblName = new javax.swing.JLabel();
        jTxtName = new javax.swing.JTextField();
        jBtnSave = new javax.swing.JButton();
        jCbxUsersDot = new javax.swing.JComboBox<>();
        jBtnDelete = new javax.swing.JButton();
        jLblTitle = new javax.swing.JLabel();
        jBtnCreate = new javax.swing.JButton();

        jMainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTableUsers.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneTable.setViewportView(jTableUsers);

        javax.swing.GroupLayout jMainPanelLayout = new javax.swing.GroupLayout(jMainPanel);
        jMainPanel.setLayout(jMainPanelLayout);
        jMainPanelLayout.setHorizontalGroup(
            jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneTable)
        );
        jMainPanelLayout.setVerticalGroup(
            jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );

        jPaneForm.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLblForm.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLblForm.setText("Formulario");

        jLblId.setLabelFor(jTxtId);
        jLblId.setText("Id:");

        jTxtId.setEnabled(false);

        jLblDotId.setLabelFor(jTxtDotId);
        jLblDotId.setText("DotProjectId");

        jTxtDotId.setEnabled(false);

        jChkState.setText("Estado");

        jLblUsername.setText("NombreUsuario:");

        jLblName.setLabelFor(jTxtName);
        jLblName.setText("Nombre:");

        jBtnSave.setText("Guardar");
        jBtnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSaveMouseClicked(evt);
            }
        });

        jBtnDelete.setText("Eliminar");
        jBtnDelete.setPreferredSize(new java.awt.Dimension(71, 23));
        jBtnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnDeleteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPaneFormLayout = new javax.swing.GroupLayout(jPaneForm);
        jPaneForm.setLayout(jPaneFormLayout);
        jPaneFormLayout.setHorizontalGroup(
            jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneFormLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneFormLayout.createSequentialGroup()
                        .addGroup(jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblId)
                            .addComponent(jTxtId, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPaneFormLayout.createSequentialGroup()
                                .addComponent(jTxtDotId, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jChkState))
                            .addComponent(jLblDotId))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneFormLayout.createSequentialGroup()
                        .addGroup(jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPaneFormLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jBtnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPaneFormLayout.createSequentialGroup()
                                .addGroup(jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCbxUsersDot, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPaneFormLayout.createSequentialGroup()
                                        .addGroup(jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLblForm)
                                            .addComponent(jLblUsername))
                                        .addGap(0, 115, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLblName)
                                    .addComponent(jTxtName, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(14, 14, 14))))
        );
        jPaneFormLayout.setVerticalGroup(
            jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblForm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblId)
                    .addComponent(jLblDotId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jChkState)
                    .addComponent(jTxtId, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jTxtDotId))
                .addGap(18, 18, 18)
                .addGroup(jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblUsername)
                    .addComponent(jLblName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCbxUsersDot, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jTxtName))
                .addGap(8, 8, 8)
                .addGroup(jPaneFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLblTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLblTitle.setText("Usuarios");

        jBtnCreate.setText("Crear");
        jBtnCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnCreateMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPaneForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLblTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jBtnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPaneForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold desc="Events designer" defaultstate="collapsed">
    private void jBtnCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnCreateMouseClicked
        jTxtId.setText("");
        jTxtDotId.setText("");
        jTxtName.setText("");        
        jCbxUsersDot.setSelectedIndex(0);        
        jChkState.setSelected(true);
        jBtnDelete.setVisible(false);
        jTableUsers.clearSelection();
        user = new User(0);      
    }//GEN-LAST:event_jBtnCreateMouseClicked

    private void jBtnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSaveMouseClicked
        // TODO add your handling code here:
        user.setName(jTxtName.getText().trim());
        user.setState(jChkState.isSelected() ? 1 : 0);
        user.setConfigurationId(Session.getConfifuration().getId());
        user.setCreatorAdmin(Session.getAdministrator().getId());
        user.setCreationDate(new Date());
        
        if(user != null && validateUser()) {
            try
            {
                if(user.getId() > 0) {                
                    objDao.update(user);                
                }

                if(user.getId() == 0) {                
                    objDao.save(user);
                }
                
                resetForm();
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(this, Utilities.formatException(ex), Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(jUsersPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jBtnSaveMouseClicked

    private void jBtnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnDeleteMouseClicked
        // TODO add your handling code here:
        if(user.getId() > 0) {
            if(JOptionPane.showConfirmDialog(this, Resources.getValueByKey("user.delete.confirmDialog"), Resources.getValueByKey("dialog.warning"), 
                                             JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
                
                try
                {
                    objDao.delete(user);
                    resetForm();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(this, Utilities.formatException(ex), Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(jUsersPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }        
    }//GEN-LAST:event_jBtnDeleteMouseClicked
    //</editor-fold>    
    
    private UserDao objDao;
    private UserDotDao objDotDao;
    private List<User> usersList;
    private List<UserDot> usersDotList;
    private User user; // User seleccionado
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCreate;
    private javax.swing.JButton jBtnDelete;
    private javax.swing.JButton jBtnSave;
    private javax.swing.JComboBox<Models.UserDot> jCbxUsersDot;
    private javax.swing.JCheckBox jChkState;
    private javax.swing.JLabel jLblDotId;
    private javax.swing.JLabel jLblForm;
    private javax.swing.JLabel jLblId;
    private javax.swing.JLabel jLblName;
    private javax.swing.JLabel jLblTitle;
    private javax.swing.JLabel jLblUsername;
    private javax.swing.JPanel jMainPanel;
    private javax.swing.JPanel jPaneForm;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JTable jTableUsers;
    private javax.swing.JTextField jTxtDotId;
    private javax.swing.JTextField jTxtId;
    private javax.swing.JTextField jTxtName;
    // End of variables declaration//GEN-END:variables
    
    // <editor-fold defaultstate="collapsed" desc="Methods">     
    /**
     * Main method to load the initial objects and properties of the Form.
     */
    private void initComponentsAditional() {    
        // AWT -- Abstract Windows Toolkit
        objDao = new UserDao();
        objDotDao = new UserDotDao();
                
        jTableUsers.setFillsViewportHeight(true);
        jTableUsers.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            jTableUsuariosListSelectionEvent(event);
        });

        updateUsersList();

        jCbxUsersDot.addItemListener((ItemEvent event) -> {
            jCbxUsuariosDotItemStateChange(event);
        });        

        jBtnDelete.setVisible(false);
        jPaneForm.setEnabled(false);
    }
    
    /**
     * Set the columns width for the users table according to the container size.
     * @param width Container width.
     */
    private void setColumnsWidth(int width) {
        jTableUsers.getColumnModel().getColumn(0).setPreferredWidth((int) (0.10 * width));
        jTableUsers.getColumnModel().getColumn(1).setPreferredWidth((int) (0.15 * width));
        jTableUsers.getColumnModel().getColumn(2).setPreferredWidth((int) (0.40 * width));
        jTableUsers.getColumnModel().getColumn(3).setPreferredWidth((int) (0.25 * width));
        jTableUsers.getColumnModel().getColumn(4).setPreferredWidth((int) (0.10 * width));        
    }
    
    /**
     * Update the user list.
     */
    private void updateUsersList() {
        try
        {
            user = new User(0);
            usersList = objDao.getListByConfigurationId(Session.getConfifuration().getId());
            usersDotList = objDotDao.getList();
            bindingUsersDot();
            jTableUsers.setModel(new UserTableModel(usersList));
            setColumnsWidth(getPreferredSize().width - 20);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, Utilities.formatException(ex), Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(jUsersPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Reset the value for the form controls.
     */
    private void resetForm() {
        jTxtId.setText("");
        jTxtName.setText("");
        jBtnDelete.setVisible(false);
        updateUsersList();
    }    
    
    /**
     * Load the DotProject users on the ComboBox.
     */
    private void bindingUsersDot() {
        if(usersDotList.size() > 0) {
            usersDotList = usersDotList.stream()
                                       .filter(x -> !usersList.stream().anyMatch(y -> y.getUsername().equals(x.getUsername())))
                                       .collect(Collectors.toList());
        
        }
       
        jCbxUsersDot.removeAllItems();
        jCbxUsersDot.addItem(new UserDot(0, Resources.getValueByKey("empty.option")));
        
        usersDotList.stream().forEach(x -> {
            jCbxUsersDot.addItem(x);            
        });
    }
    
    /**
     * Load the user data for the user selected.
     */
    private void loadUser() {
        jPaneForm.setEnabled(true);
        jTxtId.setText(Integer.toString(user.getId()));
        jTxtDotId.setText(Integer.toString(user.getDotProjectId()));
        jCbxUsersDot.getModel().setSelectedItem(new UserDot(user.getDotProjectId(), user.getUsername()));
        jTxtName.setText(user.getName());
        jChkState.setSelected(user.getState() == 1);
        jBtnDelete.setVisible(true);
    }
    
    /**
     * Validates the mandatory fields when try to save or update User.
     */
    private boolean validateUser() {
        String messageError = "";
        
        if(user.getDotProjectId() == 0) {
            messageError += Resources.getValueByKey("userDot.missing") + "\n";
        }
        
        if(user.getName() == null || user.getName().isEmpty()) {
            messageError += Resources.getValueByKey("user.name.missing");
        }
        
        if(!messageError.isEmpty()) {
            JOptionPane.showMessageDialog(this, messageError, Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
        }
        
        return messageError.isEmpty();        
    }
    // </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="Events">
    protected void jTableUsuariosListSelectionEvent(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {//This line prevents double events            
            if (jTableUsers.getSelectedRow() > -1) {
                int usuarioId = (int) jTableUsers.getValueAt(jTableUsers.getSelectedRow(), 0);
                user = usersList.stream().filter(x -> x.getId() == usuarioId).findFirst().orElse(User.DEFAULT);
                loadUser();
            }
        }
    }
    
    protected void jCbxUsuariosDotItemStateChange(ItemEvent event) {
        if(jCbxUsersDot.getSelectedIndex() > -1) {
            if(event.getStateChange() == ItemEvent.SELECTED) {
                if(jCbxUsersDot.getSelectedIndex() > 0){
                    UserDot usuarioDot = (UserDot) event.getItem();               
                    
                    jTxtDotId.setText(String.valueOf(usuarioDot.getId()));
                    jTxtName.setText(usuarioDot.getFullname());
                    
                    user.setDotProjectId(usuarioDot.getId());
                    user.setUsername(usuarioDot.getUsername());
                    user.setName(usuarioDot.getFullname());
                } else {
                    jTxtDotId.setText("");
                    jTxtName.setText("");
                }                
            }  
        }     
    }
    //</editor-fold>
}