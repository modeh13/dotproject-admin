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
@Table(name = "Administrators")
public class Administrator extends BaseEntity {
 
    //Properties
    @Column(name = "Name")
    private String Name;    
    
    @Column(name = "State")
    private int State;
    
    @Column
    private String IpAddress;
    
    @Column
    private short SuperAdmin;

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
    
    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String IpAddress) {
        this.IpAddress = IpAddress;
    }

    public short getSuperAdmin() {
        return SuperAdmin;
    }

    public void setSuperAdmin(short SuperAdmin) {
        this.SuperAdmin = SuperAdmin;
    }
    
    //Constructors
    public Administrator() {}    
}