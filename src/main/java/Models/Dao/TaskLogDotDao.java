/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Dao;

import Util.Data.DotConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;

import Models.TaskLogDot;
/**
 *
 * @author german.ramirez
 */
public class TaskLogDotDao extends DotConnection {
    
    private CachedRowSet crs;    
    private final String sqlLogs = "SELECT TL.task_log_id, TL.task_log_date, TL.task_log_hours, TL.task_log_name, CP.company_name, PR.project_name, " +
                                   "TK.task_name, TL.task_log_description " +
                                   "FROM dotproject.dotp_task_log TL " +
                                   "INNER JOIN dotproject.dotp_tasks TK ON TK.task_id = TL.task_log_task " +
                                   "INNER JOIN dotproject.dotp_projects PR ON PR.project_id = TK.task_project " +
                                   "INNER JOIN dotproject.dotp_companies CP ON CP.company_id = PR.project_company " +
                                   "WHERE task_log_creator = %1$d AND task_log_date BETWEEN '%2$s' AND '%3$s'";
   
    private final String sqlTaskLogName = "SELECT DISTINCT TL.task_log_name " +
                                          "FROM dotproject.dotp_task_log TL " +                                        
                                          "WHERE TL.task_log_creator IN (%1$s) AND TL.task_log_date BETWEEN '%2$s' AND '%3$s' " +
                                          "ORDER BY TL.task_log_name";
    
    /**
     * Create a new instance to TaskLogDot class according to RowSet.
     * @param crs
     * @return 
     */
    protected TaskLogDot createObject(CachedRowSet crs) {
        TaskLogDot taskLog = new TaskLogDot();
        
        try {
            taskLog.setTask_log_id(crs.getInt("task_log_id"));
            taskLog.setTask_log_date(crs.getDate("task_log_date"));
            taskLog.setTask_log_hours(crs.getDouble("task_log_hours"));
            taskLog.setTask_log_name(crs.getString("task_log_name"));
            taskLog.setCompany_name(crs.getString("company_name"));
            taskLog.setProject_name(crs.getString("project_name"));
            taskLog.setTask_name(crs.getString("task_name"));
            taskLog.setTask_log_description(crs.getString("task_log_description"));            
        } catch (SQLException ex) {
            Logger.getLogger(TaskLogDotDao.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
        return taskLog;    
    }
    
    /**
     * Get the TaskLogDot list by User ID.
     * @param id
     * @param startDate
     * @param endDate
     * @param summaryFilter
     * @param orderBy
     * @return
     * @throws SQLException 
     */
    public List<TaskLogDot> getListByUserId(int id, String startDate, String endDate, String summaryFilter, String orderBy) throws SQLException {
        List<TaskLogDot> list = new ArrayList<>();
        
        try {
            String sql = String.format(sqlLogs, id, startDate, endDate);
            sql += !summaryFilter.isEmpty() ? " AND " + summaryFilter : "";
            sql += !orderBy.isEmpty() ? " " + orderBy : "";
            
            crs = super.executeQuery(sql);
            
            while(crs.next()){
                list.add(createObject(crs));       
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskLogDotDao.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }        
        
        return list;
    }
    
    /**
     * Get TaskLogName list.
     * @param ids
     * @param startDate
     * @param endDate
     * @return
     * @throws SQLException 
     */
    public List<String> getListTaskLogName(String ids, String startDate, String endDate) throws SQLException {
        List<String> list = new ArrayList<>();
        
        try {
            String d = String.format(sqlTaskLogName, ids, startDate, endDate);
            crs = super.executeQuery(String.format(sqlTaskLogName, ids, startDate, endDate));
            
            while(crs.next()){
                list.add(crs.getString("task_log_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TaskLogDotDao.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }        
        
        return list;
    }
}