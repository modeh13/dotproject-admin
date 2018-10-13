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
public class GenericItem {
    //Properties
    private int Id;
    private String Value;

    //Getters and Setters
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }
    
    //Constructors
    public GenericItem() {}
    
    public GenericItem(int id, String value) {
        this.Id = id;
        this.Value = value;
    }
    
    //Methods
    @Override
    public String toString()
    {
        return this.Value;
    }
}