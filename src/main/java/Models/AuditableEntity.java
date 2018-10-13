/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Column;

/**
 *
 * @author german.ramirez
 */

@MappedSuperclass
public class AuditableEntity extends BaseEntity {

    //Properties
    @Column(name = "CreatorAdmin")    
    private int CreatorAdmin;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreationDate")
    private Date CreationDate;

    //Getters and Setters
    public int getCreatorAdmin() {
        return CreatorAdmin;
    }

    public void setCreatorAdmin(int CreatorAdmin) {
        this.CreatorAdmin = CreatorAdmin;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date CreationDate) {
        this.CreationDate = CreationDate;
    }
    
    //Constructors
    public AuditableEntity() {}
}