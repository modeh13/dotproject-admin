/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util.Data;

import java.io.Serializable;

/**
 *
 * @author german.ramirez
 */
public class SQLParameter implements Serializable {
    
    // Members
    private final Object value;
    private final SQLDataType type;
    
    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @return the type
     */
    public SQLDataType getType() {
        return type;
    }
     
    // Constructors
    public SQLParameter(Object value, SQLDataType type) {
        this.value = value;
        this.type = type;    
    }
}