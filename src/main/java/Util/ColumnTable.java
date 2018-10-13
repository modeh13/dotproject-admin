/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author german.ramirez
 */
public class ColumnTable {

    //Properties
    private short id;
    private String name;
    private Class<?> type;
    
    //Getters and Setters
    /**
     * @return the Id
     */
    public short getId() {
        return id;
    }

    /**
     * @param id the Id to set
     */
    public void setId(short id) {
        this.id = id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public Class<?> getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setTipo(Class<?> type) {
        this.type = type;
    }
    
    //Constructors
    public ColumnTable(short id, String name, Class<?> type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }    
}