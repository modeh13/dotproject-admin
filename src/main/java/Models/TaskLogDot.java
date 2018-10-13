/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author german.ramirez
 */
public class TaskLogDot {
    
    //Properties
    private int task_log_id;
    private Date task_log_date;
    private double task_log_hours;
    private String task_log_name;
    private String company_name;
    private String project_name;
    private String task_name;
    private String task_log_description;

    //Getters and Setters
    public int getTask_log_id() {
        return task_log_id;
    }

    public void setTask_log_id(int task_log_id) {
        this.task_log_id = task_log_id;
    }

    public Date getTask_log_date() {
        return task_log_date;
    }

    public void setTask_log_date(Date task_log_date) {
        this.task_log_date = task_log_date;
    }

    public double getTask_log_hours() {
        return task_log_hours;
    }

    public void setTask_log_hours(double task_log_hours) {
        this.task_log_hours = task_log_hours;
    }

    public String getTask_log_name() {
        return task_log_name;
    }

    public void setTask_log_name(String task_log_name) {
        this.task_log_name = task_log_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_log_description() {
        return task_log_description;
    }

    public void setTask_log_description(String task_log_description) {
        this.task_log_description = task_log_description;
    }
    
    //Constructors
    public TaskLogDot() {}             
}