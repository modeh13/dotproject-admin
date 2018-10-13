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
@Table(name = "Users")
public class User extends AuditableEntity {

    //Properties
    public static User DEFAULT = new User();    
    
    @Column(name = "Name")
    private String Name;    
    
    @Column(name = "State")
    private int State;
    
    @Column
    private int DotProjectId;
    
    @Column(name = "Username")
    private String Username;    
    
    @Column(name = "ConfigurationId")
    private int ConfigurationId;
   
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
    
    /**
     * @return the DotProjectId
     */
    public int getDotProjectId() {
        return DotProjectId;
    }

    /**
     * @param DotProjectId the DotProjectId to set
     */
    public void setDotProjectId(int DotProjectId) {
        this.DotProjectId = DotProjectId;
    }    

    /**
     * @return the NombreUsuario
     */
    public String getUsername() {
        return Username;
    }

    /**
     * @param Username the NombreUsuario to set
     */
    public void setUsername(String Username) {
        this.Username = Username;
    }   
    
    /**
    * @return the ConfigurationId
    */
    public int getConfigurationId() {
        return ConfigurationId;
    }

    /**
     * @param ConfigurationId the ConfigurationId to set
     */
    public void setConfigurationId(int ConfigurationId) {
        this.ConfigurationId = ConfigurationId;
    }
    
    //Constructors
    public User() {}
    
    public User(int id) {
        super.setId(id);
    }
    
    public User(int id, String name) {
        super.setId(id);
        this.Name = name;
    }
    
    //Methods
     @Override
    public String toString() {
        if(this.Username == null){
            return this.Name;
        } else {
            return String.format("%s (%s)", this.Name, this.Username);
        }
    }
}