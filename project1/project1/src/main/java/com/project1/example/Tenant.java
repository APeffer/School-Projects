package com.project1.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Tenant extends Person{
    private int aptNum;

    /**
    * Constructor for a Tenant
    * 
    * @param String name
    * @param int aptNum
    */
    public Tenant(String name, int aptNum){
        this.name = name;
        this.aptNum = aptNum;
    }

    /**
    * returns this tenants name
    * @return String name
    */
    public String getName(){
        return name;
    }

    /**
    * returns this tenants apartment number
    * @return int aptNum
    */
    public int getAptNum(){
        return aptNum;
    }

    /**
    * Creates a new work request
    * adds the work request to this tenant's work request list
    * adds the work request to the master work request list
    *
    * @param partNeeded is the part that the tenant needs for their requested work
    * @param masterWorkList is used to add the work to the master work list
    */
    public void createWorkRequest(Part partNeeded, ArrayList<WorkRequest> masterWorkList){
        WorkRequest request = new WorkRequest(name, aptNum, LocalDate.now(), partNeeded);
        work.add(request);
        masterWorkList.add(request);
    }

    /**
    * Prints a list of this tenant's work requests
    * outputs their date, part required, priority, and status
    *
    */
    public void printWorkRequests(){
        if (work.size() > 0){
            System.out.println("\n" + name + "'s Work Requests:\n");
            System.out.println("   Date    | Part Required | Priority | Status");
            System.out.println("--------------------------------------------------");
            for (WorkRequest request : work){
                
                System.out.printf("%s | %-13s | %-9d| %s\n", request.getDate(), request.getPart().getName(), request.getPriority(), request.getStatus());
            }
            System.out.println("");
        }
        else{
            System.out.println(name + " has no work requests.");
        }
    }

    /**
    * Prints the menu for the user to interact with
    *
    */
    public void printMenu(){
        System.out.println("\nWelcome " + name + " - Apartment #: " + aptNum);
        System.out.println("c - Create a new work request");
        System.out.println("v - View all of your work requests.");
        System.out.println("b - Go back");
        
    }

    /**
    * Logs in to a specific tenant so that the user may function inside their menu.
    * c - Create a new work request
    * v - View all of your work requests
    * b - Go back
    *
    * @param scnr is a scanner to input user options
    * @param partlist is the list of all available parts
    * @param masterWorkList is the list of all work requests
    */
    public void login(Scanner scnr, ArrayList<Part> partList, ArrayList<WorkRequest> masterWorkList){
        boolean running = true;
        char userOption;
        String userString;

        do{
            printMenu();
            userOption = scnr.nextLine().charAt(0);
            switch (userOption) {

                // create a new work request
                case 'c':
                    System.out.println("\nWhat part do you need?");
                    System.out.println("Say: \"light bulb\", \"air filter\", \"paint\", or say \"back\" to go back");
                    userString = scnr.nextLine();
                    if (userString == "back"){
                        break;
                    }
                    boolean partFound = false;
                    for (Part part : partList){
                        if (part.getName().equals(userString)){
                            createWorkRequest(part, masterWorkList);
                            partFound = true;
                            System.out.println("Work reqest submitted for " + part.getName());
                            break;
                        } 
                    }

                    if (partFound == false){
                        System.out.println("Part does not exist.");
                    }

                    break;
                case 'v': // Print Work Requests for this tenant
                    printWorkRequests();
                    break;
                case 'b':
                    running = false;
                    break;
            
                default:
                    break;
            }


        } while (running);

    }
}