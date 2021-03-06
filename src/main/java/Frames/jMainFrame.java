/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.MenuElement;

import Controls.CheckBoxMenuItem;
import Global.Session;
import Models.Administrator;
import Models.Configuration;
import Models.Dao.AdministratorDao;
import Models.Dao.ConfigurationDao;
import Util.Data.ConnectionDB;
import Util.Resources;
import Util.Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 *
 * @author german.ramirez
 */
public class jMainFrame extends javax.swing.JFrame {

    /**
     * Creates new form jfrmPrincipal
     */
    public jMainFrame() {
        initComponents();
        initComponentesAditional();        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar = new javax.swing.JMenuBar();
        jMenuConfigurations = new javax.swing.JMenu();
        jMenuUsers = new javax.swing.JMenu();
        jMenuProjects = new javax.swing.JMenu();
        jMenuQueries = new javax.swing.JMenu();
        jMenuReport = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jMenuBar.setPreferredSize(new java.awt.Dimension(163, 35));

        jMenuConfigurations.setText("Configuraciones");
        jMenuBar.add(jMenuConfigurations);

        jMenuUsers.setText("Usuarios");
        jMenuUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuUsersMouseClicked(evt);
            }
        });
        jMenuBar.add(jMenuUsers);

        jMenuProjects.setText("Proyectos");
        jMenuBar.add(jMenuProjects);

        jMenuQueries.setText("Consultas");
        jMenuQueries.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuQueriesMouseClicked(evt);
            }
        });
        jMenuBar.add(jMenuQueries);

        jMenuReport.setText("Reporte");
        jMenuReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuReportMouseClicked(evt);
            }
        });
        jMenuBar.add(jMenuReport);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 532, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //<editor-fold desc="Events Designer" defaultstate="collapsed">    
    private void jMenuUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuUsersMouseClicked
        // TODO add your handling code here:    
        clearControls();
        jUsersPanel = new jUsersPanel();
        
        Dimension dmsUsers = jUsersPanel.getPreferredSize();
        Dimension dmsMenu = jMenuBar.getPreferredSize();
        
        setSize(new Dimension(dmsUsers.width + margin, (dmsMenu.height + dmsUsers.height + (margin * 3))));
        setLayout(new BorderLayout());
        add(jUsersPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuUsersMouseClicked

    private void jMenuQueriesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuQueriesMouseClicked
        // TODO add your handling code here:
        clearControls();
        jQueriesPanel = new jQueriesPanel();
        
        Dimension dmsQueries = jQueriesPanel.getPreferredSize();
        Dimension dmsMenu = jMenuBar.getPreferredSize();
        
        setSize(new Dimension(dmsQueries.width + margin, (dmsMenu.height + dmsQueries.height + (margin * 3))));
        setLayout(new BorderLayout());
        add(jQueriesPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuQueriesMouseClicked

    private void jMenuReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuReportMouseClicked
        // TODO add your handling code here:
        clearControls();
        jReportPanel = new jReportPanel();
        
        Dimension dmsReporte = jReportPanel.getPreferredSize();
        Dimension dmsMenu = jMenuBar.getPreferredSize();
        
        setSize(new Dimension(dmsReporte.width + margin, (dmsMenu.height + dmsReporte.height + (margin * 3))));
        setLayout(new BorderLayout());
        add(jReportPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);  
    }//GEN-LAST:event_jMenuReportMouseClicked
    //</editor-fold>
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new jMainFrame().setVisible(true);
        });
    }
    
    // Own variables
    private final int margin = 10;
    private jUsersPanel jUsersPanel;
    private jQueriesPanel jQueriesPanel;
    private jReportPanel jReportPanel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuConfigurations;
    private javax.swing.JMenu jMenuProjects;
    private javax.swing.JMenu jMenuQueries;
    private javax.swing.JMenu jMenuReport;
    private javax.swing.JMenu jMenuUsers;
    // End of variables declaration//GEN-END:variables

    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Main method to load the initial and global variables of the Application.
     */
    private void initComponentesAditional() {
        // Load images ICONS
        final URL ICON16 = this.getClass().getResource("/Images/ico_16.png");
        final URL ICON24 = this.getClass().getResource("/Images/ico_24.png");
        final URL ICON32 = this.getClass().getResource("/Images/ico_32.png");
        final URL ICON48 = this.getClass().getResource("/Images/ico_48.png");

        List<Image> images = new ArrayList<>();
        
        try {
            images.add(ImageIO.read(ICON16));
            images.add(ImageIO.read(ICON24));
            images.add(ImageIO.read(ICON32));
            images.add(ImageIO.read(ICON48));
        } catch (IOException e) {
            Logger.getLogger(jMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }

        //ConnectionDataBase();
        
        // Define a small and large app icon
        this.setIconImages(images);        
        setLocationRelativeTo(null);
        jMenuProjects.setVisible(false);        
        setVisibleMenuItems(false);
        loadAdministrator();
    }  
    
    public void ConnectionDataBase() {
        Connection connection = null;
        try {
            if(connection == null || connection.isClosed()){
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://192.168.0.6:3306/admin_dotproject", "acces_user", "APPreportes#2017");
                
                if(connection != null) {
                   Logger.getLogger(jMainFrame.class.getName()).log(Level.INFO, "Conexión Establecida.");
                   connection.close();
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //org.junit.Assert.assertNotNull(connection);        
    }
    
    /**
     * Get the Administrator object by IpAddress List.
     * @param ipList
     * @return 
     */
    private Administrator getByIpAddress(List<String> ipList)
    {
        AdministratorDao objDao = new AdministratorDao();
        Administrator admin = null;
        
        try
        {            
            for(String ip : ipList)
            {            
                admin = objDao.getByIpAddress(ip);                
                if(admin != null) break;
            }
        }
        catch(Exception ex)
        {
            Logger.getLogger(jMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, Utilities.formatException(ex), Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
        }
        
        return admin;
    }
    
    /**
     * Get the IP address for the machine to consult if there is some Administrator configured.
     */
    private void loadAdministrator()
    {
        try
        {
            ArrayList<String> ipAddress = Utilities.GetIpsLocal();         
            Administrator admin = getByIpAddress(ipAddress);
            
            if(admin != null)
            {
                ConfigurationDao configDao = new ConfigurationDao();        
                
                setTitle(admin.getName());
                Session.setAdministrator(admin);
                Session.setConfigurations(configDao.getListByAdmin(admin));
                loadConfigurations();
            }
            else{
                JOptionPane.showMessageDialog(this, Resources.getValueByKey("administrator.notFound"), Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
        catch(HeadlessException ex)
        {            
            JOptionPane.showMessageDialog(this, Utilities.formatException(ex), Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(jMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            System.exit(0);
        }
    }    
    
    /**
     * Load the configurations enabled for the Administrator.
     */
    private void loadConfigurations()
    {
        List<Configuration> configList = Session.getConfigurations();
        
        if(configList != null && !configList.isEmpty())
        {
            if(configList.size() > 1)
            {
                CheckBoxMenuItem menuItem;
            
                for(Configuration config : configList){
                    menuItem = new CheckBoxMenuItem(config.getName(), config.getId());
                    menuItem.addItemListener((ItemEvent event) -> {
                        jChkBoxMenuItem_Configuraciones(event);                    
                    });

                    jMenuConfigurations.add(menuItem);
                }
            }
            else {
                Session.setConfifuration(configList.get(0));
                setTitle(String.format("%s  -  %s", Session.getAdministrator().getName(), Session.getConfifuration().getName()));
                setVisibleMenuItems(true);
                jMenuConfigurations.setVisible(false);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, Resources.getValueByKey("configuration.notFound"), Resources.getValueByKey("dialog.error"), JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    /**
     * Get the first or default configuration by ID in the Configurations collection of Administrator.     
     */    
    private Configuration getConfigurationById(int id)
    {
        return Session.getConfigurations().stream().filter(x -> x.getId() == id).findFirst().orElse(Configuration.DEFAULT);
    }
    
    /**
     * Show and hide the secondary menus of the main menu.
     */
    private void setVisibleMenuItems(boolean enabled) {
        jMenuUsers.setVisible(enabled);
        //jMenuProjects.setVisible(enabled);
        jMenuQueries.setVisible(enabled);
        jMenuReport.setVisible(enabled);        
    }
    
    /**
     * Clean and remove the containers of  controls that are loaded.
     */
    private void clearControls() {
        if(jUsersPanel != null) {
            remove(jUsersPanel);
            jUsersPanel = null;
        }
        
        if(jQueriesPanel != null) {
            remove(jQueriesPanel);
            jQueriesPanel = null;
        }
        
        if(jReportPanel != null) {
            remove(jReportPanel);
            jReportPanel = null;
        }
    }
    //</editor-fold>
    
    //<editor-fold desc="Events" defaultstate="collapsed">
    /**
     * Event that manage the status change of the menu items of the configurations.
     * @param e ItemEvent
     */
    private void jChkBoxMenuItem_Configuraciones(ItemEvent e)
    {
        CheckBoxMenuItem menuItem = ((CheckBoxMenuItem)e.getItem());        
        
        if(menuItem.isSelected())
        {         
            CheckBoxMenuItem item;
            for(MenuElement element : jMenuConfigurations.getPopupMenu().getSubElements())
            {
               item = (CheckBoxMenuItem) element;
               
               if(item.getId() != menuItem.getId() && item.isSelected())
               {
                   item.setSelected(false);
               }
            }
            
            Session.setConfifuration(getConfigurationById(menuItem.getId()));
            setTitle(String.format("%s  -  %s", Session.getAdministrator().getName(), Session.getConfifuration().getName()));
            setVisibleMenuItems(true);
        }
        else {
            setTitle(Session.getAdministrator().getName());
            clearControls();
            validate();
            repaint();
            setVisibleMenuItems(false);
        }
    }
    //</editor-fold>
}