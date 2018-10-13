/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Dao;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import Models.TaskLogDot;
import Util.ColumnTable;
import Util.Utilities;

/**
 * Class to load the TaskLog collection on the jTable control.
 * @author german.ramirez
 */
public class TaskLogTableModel extends AbstractTableModel {
    private TaskLogDot taskLog;
    private final List<TaskLogDot> list;
    private final ArrayList<ColumnTable> columns = new ArrayList<>();

    public TaskLogTableModel(List<TaskLogDot> list){
        this.list = list;
        
        // Agregar Columnas
        columns.add(new ColumnTable((short) 1, "Id", Integer.class));
        columns.add(new ColumnTable((short) 2, "Fecha", String.class));
        columns.add(new ColumnTable((short) 3, "Horas", Double.class));
        columns.add(new ColumnTable((short) 4, "Resumen", String.class));
        columns.add(new ColumnTable((short) 5, "Compañia", String.class));
        columns.add(new ColumnTable((short) 6, "Proyecto", String.class));
        columns.add(new ColumnTable((short) 7, "Tarea", String.class));
        columns.add(new ColumnTable((short) 8, "Descripción", String.class));
    }
    
    @Override
    public int getRowCount() {
        return this.list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }
    
    @Override
    public String getColumnName(int columnIndex){
         return columns.get(columnIndex).getName();
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        return columns.get(columnIndex).getType();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        taskLog = list.get(rowIndex);
                
        switch(columns.get(columnIndex).getId()) {
            case 1:
                return taskLog.getTask_log_id();
            case 2:
                return Utilities.formatDate(Utilities.SHORDATE, taskLog.getTask_log_date());
            case 3:         
                return taskLog.getTask_log_hours();
            case 4:
                return taskLog.getTask_log_name();
            case 5:                
                return taskLog.getCompany_name();
            case 6:                
                return taskLog.getProject_name();
            case 7:                
                return taskLog.getTask_name();
            case 8:                
                return taskLog.getTask_log_description();
            default:
                return "";
        }        
    }
}