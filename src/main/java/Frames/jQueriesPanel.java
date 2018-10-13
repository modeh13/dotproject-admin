/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.SQLException;

import Global.Session;
import Models.Dao.TaskLogDotDao;
import Models.Dao.TaskLogTableModel;
import Models.User;
import Models.Dao.UserDao;
import Models.TaskLogDot;
import Util.Resources;
import Util.Utilities;
import org.hibernate.HibernateException;

/**
 *
 * @author german.ramirez
 */
public class jQueriesPanel extends javax.swing.JPanel {

    /**
     * Creates new form jPanelConsultas
     */
    public jQueriesPanel() {
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

        jLblTitle = new javax.swing.JLabel();
        jPaneFilters = new javax.swing.JPanel();
        jLblUser = new javax.swing.JLabel();
        jCbxUsers = new javax.swing.JComboBox<>();
        jxDpkStartDate = new org.jdesktop.swingx.JXDatePicker();
        jxDpkEndDate = new org.jdesktop.swingx.JXDatePicker();
        jLblStartDate = new javax.swing.JLabel();
        jLblEndDate = new javax.swing.JLabel();
        jBtnSearch = new javax.swing.JButton();
        jPanelResults = new javax.swing.JPanel();
        jScrollPaneTable = new javax.swing.JScrollPane();
        jTableTaskLogs = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1024, 600));

        jLblTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLblTitle.setText("Consultas");

        jPaneFilters.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLblUser.setText("Usuario:");
        jLblUser.setToolTipText("");

        jxDpkStartDate.setFormats(new SimpleDateFormat("yyyy-MM-dd"));

        jxDpkEndDate.setFormats(new SimpleDateFormat("yyyy-MM-dd"));

        jLblStartDate.setText("Fecha Inicial");

        jLblEndDate.setText("Fecha Final");

        jBtnSearch.setText("Consultar");
        jBtnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnSearchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPaneFiltersLayout = new javax.swing.GroupLayout(jPaneFilters);
        jPaneFilters.setLayout(jPaneFiltersLayout);
        jPaneFiltersLayout.setHorizontalGroup(
            jPaneFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneFiltersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPaneFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPaneFiltersLayout.createSequentialGroup()
                        .addGroup(jPaneFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCbxUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblUser))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPaneFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jxDpkStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblStartDate))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPaneFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLblEndDate)
                            .addComponent(jxDpkEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPaneFiltersLayout.setVerticalGroup(
            jPaneFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneFiltersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPaneFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblUser)
                    .addComponent(jLblStartDate)
                    .addComponent(jLblEndDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPaneFiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCbxUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jxDpkStartDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jxDpkEndDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTableTaskLogs.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableTaskLogs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPaneTable.setViewportView(jTableTaskLogs);

        javax.swing.GroupLayout jPanelResultsLayout = new javax.swing.GroupLayout(jPanelResults);
        jPanelResults.setLayout(jPanelResultsLayout);
        jPanelResultsLayout.setHorizontalGroup(
            jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneTable)
                .addContainerGap())
        );
        jPanelResultsLayout.setVerticalGroup(
            jPanelResultsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelResultsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneTable, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPaneFilters, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLblTitle)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPaneFilters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelResults, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold desc="Events designer" defaultstate="collapsed">
    private void jBtnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnSearchMouseClicked
        // TODO add your handling code here:
        try{
            User user = (User) jCbxUsers.getSelectedItem();
            List<TaskLogDot> taskLogList = objTaskLogDotDao.getListByUserId(user.getDotProjectId(), 
                                                                            Utilities.formatDate(Utilities.FULLDATE, jxDpkStartDate.getDate()), 
                                                                            Utilities.formatDate(Utilities.FULLDATE, Utilities.setMaximunDateTime(jxDpkEndDate.getDate())),                                                                            
                                                                            "",
                                                                            "ORDER BY task_log_date");
            jTableTaskLogs.setModel(new TaskLogTableModel(taskLogList));
            setColumnsWidth();     
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, Utilities.formatException(ex), Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(jQueriesPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);        
        }
    }//GEN-LAST:event_jBtnSearchMouseClicked
    //</editor-fold>
    
    private UserDao objDao;    
    private TaskLogDotDao objTaskLogDotDao;
    private List<User> userList;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnSearch;
    private javax.swing.JComboBox<Models.User> jCbxUsers;
    private javax.swing.JLabel jLblEndDate;
    private javax.swing.JLabel jLblStartDate;
    private javax.swing.JLabel jLblTitle;
    private javax.swing.JLabel jLblUser;
    private javax.swing.JPanel jPaneFilters;
    private javax.swing.JPanel jPanelResults;
    private javax.swing.JScrollPane jScrollPaneTable;
    private javax.swing.JTable jTableTaskLogs;
    private org.jdesktop.swingx.JXDatePicker jxDpkEndDate;
    private org.jdesktop.swingx.JXDatePicker jxDpkStartDate;
    // End of variables declaration//GEN-END:variables

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Main method to load the initials and globals variables of the jPanel.
     */
    private void initComponentsAditional(){
        objDao = new UserDao();        
        objTaskLogDotDao = new TaskLogDotDao();
        
        try
        {
            userList = objDao.getListActiveUsers(Session.getConfifuration().getId());

            jTableTaskLogs.setFillsViewportHeight(true);
            
            jCbxUsers.removeAllItems();
            jCbxUsers.addItem(new  User(0, Resources.getValueByKey("empty.option")));
            userList.stream().forEach(x -> {
                jCbxUsers.addItem(x);
            });

            // Establecer Fechas por defecto para los DateTimePicker's
            jxDpkStartDate.setDate(Utilities.getMinimumDateCurrentMonth());
            jxDpkEndDate.setDate(Utilities.getMaximumDateCurrentMonth());
        }
        catch(HibernateException ex)
        {
            JOptionPane.showMessageDialog(this, Utilities.formatException(ex), Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(jQueriesPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Set the columns width of the Table.
     */
    private void setColumnsWidth()
    {        
        jTableTaskLogs.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTableTaskLogs.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTableTaskLogs.getColumnModel().getColumn(2).setPreferredWidth(50);
        jTableTaskLogs.getColumnModel().getColumn(3).setPreferredWidth(120);
        jTableTaskLogs.getColumnModel().getColumn(4).setPreferredWidth(120);
        jTableTaskLogs.getColumnModel().getColumn(5).setPreferredWidth(130);
        jTableTaskLogs.getColumnModel().getColumn(6).setPreferredWidth(130);
        jTableTaskLogs.getColumnModel().getColumn(7).setPreferredWidth(500); 
    }
    //</editor-fold>
}