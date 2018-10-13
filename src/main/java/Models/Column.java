/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.persistence.*;

/**
 *
 * @author german.ramirez
 */

@Entity
@Table(name = "Columns")
public class Column extends BaseEntity {
    
    //Properties
    @javax.persistence.Column
    private String Name;    
    
    @javax.persistence.Column
    private int State;
    
    @javax.persistence.Column
    private String Description;
    
    @javax.persistence.Column
    private String DataType;    
        
    @OneToOne(mappedBy="Column")
    private ConfigurationColumn ConfigurationColumn;
    
    //Getters and Setters
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }      
    
    public int getState() {
        return State;
    }

    public void setState(int State) {
        this.State = State;
    }
    
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    public String getDataType() {
        return DataType;
    }

    public void setDataType(String DataType) {
        this.DataType = DataType;
    }
    
    public ConfigurationColumn getConfigurationColumn() {
        return ConfigurationColumn;
    }

    public void setConfigurationColumn(ConfigurationColumn ConfigurationColumn) {
        this.ConfigurationColumn = ConfigurationColumn;
    }
    
    //Constructors
    public Column() {}
}