/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author german.ramirez
 */
public class UserDot {

    //Properties
    private int Id;
    private String Username;
    private String Fullname;
    
    //Getters and Setters
    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }
    
    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    //Constructors
    public UserDot() {}
    
    public UserDot(int id, String Username) {
        this.Id = id;
        this.Username = Username;    
    }
    
    //Methods    
    @Override
    public String toString() {        
        if(this.Fullname == null){
            return this.Username;
        } else {
            return String.format("%s (%s)", this.Fullname, this.Username);
        }
    }
}