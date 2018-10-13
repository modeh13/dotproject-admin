/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

import Models.UserDot;
import Util.Data.DotConnection;

/**
 *
 * @author german.ramirez
 */
public class UserDotDao extends DotConnection {    
    
    private CachedRowSet crs;
    private final String sqlUsers = "SELECT US.user_id, US.user_username, " + 
                                    "REPLACE(CONCAT(IFNULL(CT.contact_first_name, ''), ' ', IFNULL(CT.contact_last_name, '')), '  ', ' ') AS user_full_name " +
                                    "FROM dotproject.dotp_users US " +
                                    "INNER JOIN dotproject.dotp_contacts CT ON CT.contact_id = US.user_contact " +
                                    "ORDER BY CT.contact_first_name, CT.contact_last_name;";
   
    private final String sqlLogs = "SELECT TL.task_log_id, TL.task_log_date, TL.task_log_hours, TL.task_log_name, CP.company_name, PR.project_name, " +
                                   "TK.task_name, TL.task_log_description " +
                                   "FROM dotproject.dotp_task_log TL " +
                                   "INNER JOIN dotproject.dotp_tasks TK ON TK.task_id = TL.task_log_task " +
                                   "INNER JOIN dotproject.dotp_projects PR ON PR.project_id = TK.task_project " +
                                   "INNER JOIN dotproject.dotp_companies CP ON CP.company_id = PR.project_company " +
                                   "WHERE task_log_creator = %1$d AND task_log_date BETWEEN '%2$s' AND '%3$s' ";
    
    /**
     * Create a new instance to UserDot class.
     * @param crs
     * @return 
     */
    protected UserDot createObject(CachedRowSet crs) {
        UserDot user = new UserDot();
        
        try {
            user.setId(crs.getInt("user_id"));
            user.setUsername(crs.getString("user_username"));
            user.setFullname(crs.getString("user_full_name"));
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDotDao.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
        return user;    
    }
    
    /**
     * Get UserDot list.
     * @return 
     */
    public List<UserDot> getList() {
        List<UserDot> list = new ArrayList<>();
        
        try {
            crs = super.executeQuery(sqlUsers);
            
            while(crs.next()){
                list.add(createObject(crs));       
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDotDao.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return list;
    }
    
    /**
     * Get the array with TaskLog by UserDotProject ID.
     * @param id
     * @param startDate
     * @param endDate
     * @return 
     */
    public ArrayList<Object[]> getTaskLog(int id, String startDate, String endDate) {
        
        ArrayList<Object[]> logs = new ArrayList<>();
        
        try {
            crs = super.executeQuery(String.format(sqlLogs, id, startDate, endDate));
            
            while(crs.next())
            {                
                logs.add(new Object[]{ 
                    crs.getInt("task_log_id"),
                    crs.getDate("task_log_date"),
                    crs.getDouble("task_log_hours"),
                    crs.getString("task_log_name"),
                    crs.getString("company_name"),
                    crs.getString("project_name"),
                    crs.getString("task_name"),
                    crs.getString("task_log_description")});
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDotDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return logs;
    }
}