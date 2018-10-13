/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

/**
 *
 * @author german.ramirez
 */
public class ConnectionDB extends ConnectionBase {
    
    //Properties
    private Connection connection;
    private ResultSet rst;
    private CachedRowSet crs;
    private Statement stmt;
    private PreparedStatement pstm;    
    private List<SQLParameter> sqlParameters;
    
    // Constructors
    public ConnectionDB(){
        this.sqlParameters = new ArrayList<>();
    }
    
    // Methods
    /**
     * Open the connection with the database resource.
     */
    private void open() {        
        try {
            if(connection == null || connection.isClosed()){
                Class.forName(super.getDriverClass());
                connection = DriverManager.getConnection(super.getUrl(), super.getUser(), super.getPassword());                
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    /**
     * Close the database connection.
     */
    private void close(){
    
        try {
            if(connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    /**
     * Clean parameters list.
     */
    public void clearParameters() {
        this.sqlParameters.clear();
    }
    
    /**
     * Add new parameter to Connection.
     * @param valor
     * @param tipo 
     */
    public void addParameter(Object valor, SQLDataType tipo) {
        this.sqlParameters.add(new SQLParameter(valor, tipo));
    }
    
    /**
     * Sets the parameters added to the PreparedStatement.
     * @throws SQLException 
     */
    private void setParameters() throws SQLException {
        
        try {
            int i = 1;
            
            for(SQLParameter param : sqlParameters) {
                switch (param.getType()) {
                    case STRING:
                        pstm.setString(i, String.valueOf(param.getValue()));
                        break;
                        
                    case INT:
                        pstm.setInt(i, (int) param.getValue());
                        break;
                        
                    case SHORT:
                        pstm.setShort(i, (short) param.getValue());
                        break;
                }                
                i++;
            }
        }
        catch(SQLException ex) {            
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Execute a Query SQL sentence.
     * @param sql
     * @return 
     */
    public CachedRowSet executeQuery(String sql) {
        
        try {
            open();
            stmt = connection.createStatement();
            rst = stmt.executeQuery(sql);   
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(rst);            
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
        close();            
        }
                
        return crs;
    }
    
    /**
     * Execute a DML Query sentence.
     * @param sql
     * @return 
     */
    public int executeUpdate(String sql) {
        int rowsAffected = 0;
    
        try {
            open();            
            pstm = connection.prepareStatement(sql);
            setParameters();
            rowsAffected = pstm.executeUpdate();
            pstm.close();            
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            close();
        }
        
        return rowsAffected;
    }
}