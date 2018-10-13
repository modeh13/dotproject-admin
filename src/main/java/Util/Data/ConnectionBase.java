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
public abstract class ConnectionBase implements Serializable {

    //Properties
    private String user = "";
    private String password = "";
    private String url = "";
    private String driverClass = "";
    
    //Getters and Setters
    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the URL to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the driverClass
     */
    public String getDriverClass() {
        return driverClass;
    }

    /**
     * @param driverClass the driverClass to set
     */
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }
}