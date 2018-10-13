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
@Table(name = "Configurations")
public class Configuration extends AuditableEntity {
    
    //Properties
    public static Configuration DEFAULT = new Configuration(); 
    
    @Column
    private String Name;    
    
    @Column
    private String Header;
    
    @Column
    private boolean ShowRequestBox;
    
    @Column
    private String RequestTitle;
    
    @Column
    private boolean ShowSummaryFilter;
    
    @Column
    private boolean RoundNumbers;
    
    @Column
    private String OrderBy;

    //Getters and Setters
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    public String getHeader() {
        return Header;
    }

    public void setHeader(String Header) {
        this.Header = Header;
    }

    public boolean isShowRequestBox() {
        return ShowRequestBox;
    }

    public void setShowRequestBox(boolean ShowRequestBox) {
        this.ShowRequestBox = ShowRequestBox;
    }

    public String getRequestTitle() {
        return RequestTitle;
    }

    public void setRequestTitle(String RequestTitle) {
        this.RequestTitle = RequestTitle;
    }
    
    public boolean isShowSummaryFilter() {
        return ShowSummaryFilter;
    }

    public void setShowSummaryFilter(boolean ShowSummaryFilter) {
        this.ShowSummaryFilter = ShowSummaryFilter;
    }
    
    public boolean isRoundNumbers() {
        return RoundNumbers;
    }

    public void setRoundNumbers(boolean RoundNumbers) {
        this.RoundNumbers = RoundNumbers;
    }
    
    public String getOrderBy() {
        return OrderBy;
    }

    public void setOrderBy(String OrderBy) {
        this.OrderBy = OrderBy;
    }
    
    //Constructors
    public Configuration(){}    
}