/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;
import java.util.stream.Collectors;
import java.sql.SQLException;
import java.util.logging.Level;
import org.hibernate.HibernateException;
import org.jdesktop.swingx.JXDatePicker;

import Global.Session;
import Models.ConfigurationColumn;
import Models.Dao.ConfigurationColumnDao;
import Models.Dao.TaskLogDotDao;
import Models.Dao.UserDao;
import Models.User;
import Util.GenericItem;
import Util.Report;
import Util.Resources;
import Util.Utilities;
import Util.IProgressBar;

/**
 *
 * @author german.ramirez
 */
public class jReportPanel extends javax.swing.JPanel implements IProgressBar {

    /**
     * Creates new form jReportPanel
     */
    public jReportPanel() {
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

        jLblReport = new javax.swing.JLabel();
        jMainPanel = new javax.swing.JPanel();
        jLblStartDate = new javax.swing.JLabel();
        jxDpkStartDate = new org.jdesktop.swingx.JXDatePicker();
        jxDpkEndDate = new org.jdesktop.swingx.JXDatePicker();
        jLblEndDate = new javax.swing.JLabel();
        jBtnCreate = new javax.swing.JButton();
        jPrgProcess = new javax.swing.JProgressBar();
        jLblFile = new javax.swing.JLabel();
        jLblSummaryFilter = new javax.swing.JLabel();
        jCbxSummary = new javax.swing.JComboBox<>();
        jBtnCreateExcel = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(440, 225));

        jLblReport.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLblReport.setText("Reporte");

        jMainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLblStartDate.setText("Fecha Inicial");

        jxDpkStartDate.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        jxDpkStartDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jxDpkStartDateActionPerformed(evt);
            }
        });

        jxDpkEndDate.setFormats(new SimpleDateFormat("yyyy-MM-dd"));
        jxDpkEndDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jxDpkEndDateActionPerformed(evt);
            }
        });

        jLblEndDate.setText("Fecha Final");

        jBtnCreate.setText("Crear");
        jBtnCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnCreateMouseClicked(evt);
            }
        });

        jLblFile.setText("jLabel3");

        jLblSummaryFilter.setText("Resumen");

        jBtnCreateExcel.setText("Crear Excel");
        jBtnCreateExcel.setActionCommand("CrearExcel");
        jBtnCreateExcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBtnCreateExcelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jMainPanelLayout = new javax.swing.GroupLayout(jMainPanel);
        jMainPanel.setLayout(jMainPanelLayout);
        jMainPanelLayout.setHorizontalGroup(
            jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPrgProcess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jMainPanelLayout.createSequentialGroup()
                        .addGroup(jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jMainPanelLayout.createSequentialGroup()
                                .addGroup(jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jxDpkStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLblStartDate))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLblEndDate)
                                    .addGroup(jMainPanelLayout.createSequentialGroup()
                                        .addComponent(jxDpkEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jBtnCreateExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jBtnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLblFile)
                            .addComponent(jLblSummaryFilter)
                            .addComponent(jCbxSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 10, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jMainPanelLayout.setVerticalGroup(
            jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblStartDate)
                    .addComponent(jLblEndDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jxDpkStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jxDpkEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblSummaryFilter)
                    .addComponent(jBtnCreateExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCbxSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPrgProcess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLblFile)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBtnCreateExcel.getAccessibleContext().setAccessibleName("CrearExcel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblReport))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLblReport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnCreateMouseClicked
        // TODO add your handling code here:
        validateAndCreateReport(Report.TYPE.WORD);        
    }//GEN-LAST:event_jBtnCreateMouseClicked

    private void jxDpkStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jxDpkStartDateActionPerformed
        // TODO add your handling code here:
        if(evt.getActionCommand().equals(JXDatePicker.COMMIT_KEY))
        {
            if(!jxDpkStartDate.getDate().after(jxDpkEndDate.getDate())) {
                validateShowSummaryFilter();                
            }
            updateValue(0);
        }       
    }//GEN-LAST:event_jxDpkStartDateActionPerformed

    private void jxDpkEndDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jxDpkEndDateActionPerformed
        // TODO add your handling code here:
        if(evt.getActionCommand().equals(JXDatePicker.COMMIT_KEY))
        {
            if(jxDpkEndDate.getDate().after(jxDpkStartDate.getDate())) {
                validateShowSummaryFilter();                
            }
            updateValue(0);
        }
    }//GEN-LAST:event_jxDpkEndDateActionPerformed

    private void jBtnCreateExcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBtnCreateExcelMouseClicked
        // TODO add your handling code here:
        validateAndCreateReport(Report.TYPE.EXCEL);       
    }//GEN-LAST:event_jBtnCreateExcelMouseClicked

    private UserDao objUserDao;
    private TaskLogDotDao objTaskLogDotDao;
    List<User> usersList;
    List<String> taskLogNameList;
    private String usersIds;
    private String summaryFilter;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCreate;
    private javax.swing.JButton jBtnCreateExcel;
    private javax.swing.JComboBox<GenericItem> jCbxSummary;
    private javax.swing.JLabel jLblEndDate;
    private javax.swing.JLabel jLblFile;
    private javax.swing.JLabel jLblReport;
    private javax.swing.JLabel jLblStartDate;
    private javax.swing.JLabel jLblSummaryFilter;
    private javax.swing.JPanel jMainPanel;
    private javax.swing.JProgressBar jPrgProcess;
    private org.jdesktop.swingx.JXDatePicker jxDpkEndDate;
    private org.jdesktop.swingx.JXDatePicker jxDpkStartDate;
    // End of variables declaration//GEN-END:variables

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Main method to load the initials and globals variables of the jPanel.
     */
    private void initComponentsAditional(){
        objUserDao = new UserDao();
        objTaskLogDotDao = new TaskLogDotDao();
        jLblFile.setText("");        
        jxDpkStartDate.setDate(Utilities.getMinimumDateCurrentMonth());
        jxDpkEndDate.setDate(Utilities.getMaximumDateCurrentMonth());
        jLblSummaryFilter.setVisible(false);
        jCbxSummary.setVisible(false);
        
        try
        {
            usersList = objUserDao.getListActiveUsers(Session.getConfifuration().getId());
            validateShowSummaryFilter();
        }
        catch(HibernateException ex)
        {
            JOptionPane.showMessageDialog(this, Utilities.formatException(ex), Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(jReportPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Validate if the summery filter is displayed or not.
     */
    private void validateShowSummaryFilter()
    {
        try
        {
            if(usersList.size() > 0 && Session.getConfifuration().isShowSummaryFilter())
            {
                usersIds = String.join(",", usersList.stream().map(u -> String.valueOf(u.getDotProjectId())).collect(Collectors.toList()));
                taskLogNameList = objTaskLogDotDao.getListTaskLogName(usersIds, 
                                                                      Utilities.formatDate(Utilities.FULLDATE, jxDpkStartDate.getDate()),
                                                                      Utilities.formatDate(Utilities.FULLDATE, Utilities.setMaximunDateTime(jxDpkEndDate.getDate())));
                if(taskLogNameList.size() > 0)
                {
                    jCbxSummary.removeAllItems();
                    jCbxSummary.addItem(new  GenericItem(0, Resources.getValueByKey("empty.option")));
                    taskLogNameList.stream().forEach(x -> {
                        jCbxSummary.addItem(new GenericItem(0, x));
                    });

                    jLblSummaryFilter.setVisible(true);
                    jCbxSummary.setVisible(true);
                    jBtnCreateExcel.setVisible(true);
                }
                else{
                    jLblSummaryFilter.setVisible(false);
                    jCbxSummary.setVisible(false);
                    jBtnCreateExcel.setVisible(false);
                }                
            }        
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, Utilities.formatException(ex), Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(jReportPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);        
        }   
    }
    
    @Override    
    /**
     * Update the value for the Progress Bar.
     */
    public void updateValue(int value)
    {
        jPrgProcess.setValue(value);
    }
    
    /**
     * Get filter criterion for the log.
     * @return The filter selected for the Summary field.
     */
    private String getSummaryFilter()
    {
        String filter = "";
        
        if(Session.getConfifuration().isShowSummaryFilter() && jCbxSummary.getSelectedIndex() > 0)
        {
            filter = "task_log_name = '" + ((GenericItem) jCbxSummary.getSelectedItem()).getValue() + "'";
        }
        
        return filter;
    }
    
    /**
     * Validate and create report if It is valid.
     * @param type 
     */
    private void validateAndCreateReport(Report.TYPE type) {
        if(jxDpkStartDate.getDate().after(jxDpkEndDate.getDate())) {
            JOptionPane.showMessageDialog(this, Resources.getValueByKey("report.validation.dates"), Resources.getValueByKey("dialog.info"), JOptionPane.INFORMATION_MESSAGE);
        } else {
            if(usersList.size() < 0) {
                JOptionPane.showMessageDialog(this, Resources.getValueByKey("report.validation.users"), Resources.getValueByKey("dialog.info"), JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                try
                {         
                    updateValue(0); 
                    summaryFilter = getSummaryFilter();
                    ConfigurationColumnDao objDao = new ConfigurationColumnDao();
                    List<ConfigurationColumn> columns = objDao.getListByConfigurationId(Session.getConfifuration().getId()); 

                    Report report = new Report(this, Session.getConfifuration(), columns, type);
                    report.createReport(jxDpkStartDate.getDate(), Utilities.setMaximunDateTime(jxDpkEndDate.getDate()), summaryFilter);
                }
                catch(InvalidFormatException | XmlException ex)
                {
                    JOptionPane.showMessageDialog(this, Utilities.formatException(ex), Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(jReportPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (SQLException | NoSuchFieldException | IllegalAccessException ex) {
                    Logger.getLogger(jReportPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }       
        }
    }
    //</editor-fold>    
}