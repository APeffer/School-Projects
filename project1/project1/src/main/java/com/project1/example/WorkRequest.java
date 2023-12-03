package com.project1.example;

import java.time.LocalDate;

/**
* The WorkRequest class is used to create work requests
* A tenant creates work requests, the boss manages work requests, and the employee works on work requests.
*
* @author Alex Peffer
*/
public class WorkRequest {
    private String tenantName;
    private int aptNum;
    private LocalDate date;
    private int priority;
    private Employee assignedEmployee;
    private Part partRequired;
    private String status;

    /**
    * Constructor for the WorkRequest class
    *
    * @param tenantName the name of the tenant creating the work request
    * @param aptNum the tenant's apartment number
    * @param date today's date
    * @param partRequired the part required for this work request
    */
    public WorkRequest(String tenantName, int aptNum, LocalDate date, Part partRequired){
        this.tenantName = tenantName;
        this.aptNum = aptNum;
        this.date = date;
        this.priority = 0;
        this.assignedEmployee = null;
        this.partRequired = partRequired;
        this.status = "Open";
    }

    //getters and setters

    /**
    * returns the tenant's name who requested this work
    *
    * @return tenantName string of the tenant's name
    */
    public String getName(){
        return tenantName;
    }

    /**
    * sets the tenant's name who requested this work
    *
    * @param tenantName string of the tenant's name
    */
    public void setName(String tenantName){
        this.tenantName = tenantName;
    }

    /**
    * returns the tenant's apartment number
    *
    * @return aptNum int of the tenant's apartment number
    */
    public int getAptNum(){
        return aptNum;
    }

    /**
    * sets the apartment number for this work request
    *
    * @param aptNum int of the tenant's apartment number
    */
    public void setAptNum(int aptNum){
        this.aptNum = aptNum;
    }

    /**
    * returns the date of this work request
    *
    * @return date LocalDate of the date this work request was created
    */
    public LocalDate getDate(){
        return date;
    }

    /**
    * returns the employee that is assigned to this work request
    *
    * @return assignedEmployee employee that this work request is assigned to
    */
    public Employee getEmployee(){
        return assignedEmployee;
    }

    /**
    * sets the employee that is assigned to this work request
    *
    * @param employee employee that this work request will be assigned to
    */
    public void setEmployee(Employee employee){
        this.assignedEmployee = employee;
    }

    /**
    * returns the priority level of this work request
    * 0 = unassigned, 1 = emergency, 2 = urgent, 3 = routine
    *
    * @return priority int of the priority level of this work request
    */
    public int getPriority(){
        return priority;
    }

    /**
    * sets the part priority level for this work request
    * 0 = unassigned, 1 = emergency, 2 = urgent, 3 = routine
    * 
    * @param prioLevel int of what priority this work request should be set to.
    */
    public void setPriority(int prioLevel){
        this.priority = prioLevel;
    }

    /**
    * returns the part required for this work request
    *
    * @return partRequired Part required to complete this work request
    */
    public Part getPart(){
        return partRequired;
    }

    /**
    * returns the status of this work request
    * open = requires work, part = awaiting part order, closed = work completed
    *
    * @return status string of the status of this work request
    */
    public String getStatus(){
        return status;
    }

    /**
    * sets the status of this work request
    * open = requires work, part = awaiting part order, closed = work completed
    * 
    * @param status string of the status to set for this work request
    */
    public void setStatus(String status){
        this.status = status;
    }
    
}
