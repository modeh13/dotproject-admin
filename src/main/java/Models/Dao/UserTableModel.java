/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.Dao;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import Models.User;
import Util.ColumnTable;
import java.util.ArrayList;

/**
 * Class to load the TaskLog collection on the jTable control.
 * @author german.ramirez
 */
public class UserTableModel extends AbstractTableModel {
    
    private User user;
    private final List<User> list;
    private final ArrayList<ColumnTable> columns = new ArrayList<>();

    //Constructor
    public UserTableModel(List<User> lista){
        this.list = lista;
        
        // Agregar Columnas
        columns.add(new ColumnTable((short) 1, "Id", Integer.class));
        columns.add(new ColumnTable((short) 2, "DotProjectId", Integer.class));
        columns.add(new ColumnTable((short) 3, "Nombre", String.class));
        columns.add(new ColumnTable((short) 4, "NombreUsuario", String.class));
        columns.add(new ColumnTable((short) 5, "Estado", Boolean.class));
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
        user = list.get(rowIndex);
                
        switch(columns.get(columnIndex).getId()) {
            case 1:
                return user.getId();
            case 2:
                return user.getDotProjectId();
            case 3:         
                return user.getName();
            case 4:
                return user.getUsername();
            case 5:                
                return user.getState() == 1;              
            default:
                return "";
        }        
    }    
}