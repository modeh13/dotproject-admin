/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javax.persistence.*;
import javax.persistence.Column; 

/**
 *
 * @author german.ramirez
 */
@Entity
@Table(name = "Configuration_Columns")
public class ConfigurationColumn extends AuditableEntity {
    
    //Properties
    @Column
    private int ConfigurationId;
   
    @Column
    private int ColumnId;
    
    @Column
    private String Description;
    
    @Column
    private double Width;
    
    @Column
    private int Order;
    
    @Column
    private int HorizontalAlignment;
    
    @Column
    private boolean Totalize;
        
    @OneToOne
    @JoinColumn (name="ColumnId", insertable=false, updatable=false)
    private Models.Column Column;

    //Getters and Setters
    public int getConfigurationId() {
        return ConfigurationId;
    }

    public void setConfigurationId(int ConfigurationId) {
        this.ConfigurationId = ConfigurationId;
    }

    public int getColumnId() {
        return ColumnId;
    }

    public void setColumnId(int ColumnId) {
        this.ColumnId = ColumnId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public double getWidth() {
        return Width;
    }

    public void setWidth(double Width) {
        this.Width = Width;
    }

    public int getOrder() {
        return Order;
    }

    public void setOrder(int Order) {
        this.Order = Order;
    }    
    
    public int getHorizontalAlignment() {
        return HorizontalAlignment;
    }

    public void setHorizontalAlignment(int HorizontalAlignment) {
        this.HorizontalAlignment = HorizontalAlignment;
    }
    
    public boolean isTotalize() {
        return Totalize;
    }

    public void setTotalize(boolean Totalize) {
        this.Totalize = Totalize;
    }
    
    public Models.Column getColumn() {
        return Column;
    }

    public void setColumn(Models.Column Column) {
        this.Column = Column;
    }
    
    //Constructors
    public ConfigurationColumn() {}
}