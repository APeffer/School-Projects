package com.project1.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class to give an employee its operations and functionality
 * 
 * @author Alex Peffer
 */
public class Employee extends Person {
    private ArrayList<Tenant> tenantList;

    /**
    * Constructor
    *
    * @param name a string to set the employee object's name (used for login)
    */
    public Employee(String name){
        this.name = name;
    }
    
    /**
    * Returns a String of the employee's name
    *
    * @return employee's name
    */
    public String getName(){
        return name;
    }

    /**
    * Allow employee to access the list of tenants that will allow them to view, add, or remove tenants
    *
    * @param tenantList is a list from main that includes all tenants
    */
    public void giveList(ArrayList<Tenant> tenantList){
        this.tenantList = tenantList;
    }

    /**
    * Prints a list of the employee's work.
    * Work Request number, date, part, priority, status, assigned employee, aptNum, and tenant name.
    *
    * @param masterWorkList allows the employee to print their work from the master work list.
    */
    public void printWorkRequests(ArrayList<WorkRequest> masterWorkList){
        if (work.size() > 0){
            System.out.println("\n" + name + "'s Work Requests:\n");
            System.out.println("  #  |     Date      | Part Required | Priority |    Status     |   Assigned    |   Apt #   | Tenant ");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            for (WorkRequest request : work){
                String temp;
                if (request.getEmployee() == null){
                    temp = "none";
                }
                else{
                    temp = request.getEmployee().getName();
                }
                System.out.printf("  %-2d | %-13s | %-13s | %-9d| %-13s | %-13s | %-9d | %s \n", masterWorkList.indexOf(request), request.getDate(), request.getPart().getName(), request.getPriority(), request.getStatus(), temp, request.getAptNum(), request.getName());
            }
            System.out.println("");
        }
        else{
            System.out.println(name + " has no work requests.");
        }
    }

    /**
    * When work is assigned to an employee by a Boss, the addwork method will add the Work Request to their particular list of work.
    *
    * @param request the request to be given to this employee
    */
    public void addWork(WorkRequest request){
        work.add(request);
    }
    
    /**
    * Prints the menu for the user to interact with when signed into this employee
    *
    */
    public void printMenu(){
        
        System.out.println("\nv - View all of your work requests.");
        System.out.println("w - Work on a request");
        System.out.println("o - Order more parts");
        System.out.println("t - View all tenants");
        System.out.println("a - Add a new tenant");
        System.out.println("r - Remove a tenant");
        System.out.println("b - Go back\n");
        
    }


    /**
    * Logs in to a specific employee so that the user may function inside their menu.
    * v - View all of your work requests
    * w - Work on a request
    * o - Order more parts
    * t - View all tenants
    * a - Add a new tenant
    * r - Remove a tenant
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
        int userInt = -1;

        do{
            printMenu();
            userOption = scnr.nextLine().charAt(0);
            switch (userOption) {

                case 'v': // Print Work Requests for this employee
                    printWorkRequests(masterWorkList);
                    break;
                case 'w': // Work on a work request
                    
                    while(true){
                        
                        try {
                            System.out.println("\nEnter the work request number:");
                            System.out.println("-1 to go back");

                            // get user input for work request number
                            userInt = scnr.nextInt();
                            scnr.nextLine();
                            
                            // if -1 then go back
                            if (userInt == -1){
                                break;
                            }

                            // this line will throw a InputMismatch exception if the work request number does not exist
                            masterWorkList.get(userInt);

                            if (masterWorkList.get(userInt).getEmployee() != null && masterWorkList.get(userInt).getEmployee().equals(this)){
                                if (masterWorkList.get(userInt).getStatus().equals("closed")){
                                System.out.println("This work request has already been closed and cannot be worked on unless the boss changes the status.");
                                break;
                                }

                                // CASE: not enough parts to complete the work request
                                if (masterWorkList.get(userInt).getPart().getQuantity() < 1){
                                    System.out.println("Cannot work on this request.");
                                    System.out.println("Stockroom needs more of part: " + masterWorkList.get(userInt).getPart().getName());
                                    System.out.println("Work request #" + userInt + " status changed to \"part\".");
                                    masterWorkList.get(userInt).setStatus("part");
                                    break;
                                } //CASE: has a part. -1 of that part.
                                else {
                                    System.out.println("Work successfully completed" + userInt);
                                    masterWorkList.get(userInt).getPart().usePart();
                                    System.out.println("1 " + masterWorkList.get(userInt).getPart().getName() + " has been used. There are " + masterWorkList.get(userInt).getPart().getQuantity() + " remaining.");
                                    masterWorkList.get(userInt).setStatus("closed");
                                    System.out.println("Work request #" + userInt + " status changed to \"closed\".");
                                    break;
                                }
                            }
                            else{
                                System.out.println("\nThat work request is not assigned to this employee.");
                                break;
                            }                     
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            scnr.nextLine(); // Consume the invalid input to avoid an infinite loop
                        }
                        catch (IndexOutOfBoundsException e){
                            System.out.println("Invalid input. That work request does not exist.");
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }

                        if (userInt == -1){
                            break;
                        }
                        
                    }
                    
                    
                    System.out.println("");
                    break;
                case 'o':
                    try {
                            System.out.println("\nEnter the work request number:");
                            System.out.println("-1 to go back");

                            // get user input for work request number
                            userInt = scnr.nextInt();
                            scnr.nextLine();
                            
                            // if -1 then go back
                            if (userInt == -1){
                                break;
                            }

                            // this line will throw a InputMismatch exception if the work request number does not exist
                            masterWorkList.get(userInt);

                            if (masterWorkList.get(userInt).getStatus().equals("part")){
                                masterWorkList.get(userInt).getPart().orderMore(2);
                                System.out.println("2 more of part: " + masterWorkList.get(userInt).getPart().getName() + " have been ordered.");
                                break;
                            }          
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            scnr.nextLine(); // Consume the invalid input to avoid an infinite loop
                        }
                        catch (IndexOutOfBoundsException e){
                            System.out.println("Invalid input. That work request does not exist.");
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }

                        if (userInt == -1){
                            break;
                        }
                    System.out.println("");
                    break;
                case 't': // View all tenants

                    // if tenant list empty, show message and exit
                    if (tenantList.size() <= 0){
                        System.out.println("\nTenant list is empty.");
                        break;
                    }
                        System.out.println("\n Name         | Apartment Number");
                        System.out.println("----------------------------------");
                    for (Tenant tenant : tenantList){
                        System.out.printf("%-13s | %-13d\n", tenant.getName(), tenant.getAptNum());
                    }
                    break;
                case 'a':
                    while(true){
                        System.out.println("\nAdd a new tenant!\n");
                        System.out.println("Enter a name:");
                        userString = scnr.nextLine();
                        
                        try{    
                            for (Tenant ten : tenantList){
                                if (ten.getName().equals(userString)){
                                    throw new Exception("That name is already taken, please choose a unique name.");
                                }
                                else{
                                    break;
                                }
                            }

                            
                            System.out.println("Enter an apartment number:");
                            userInt = scnr.nextInt();
                            scnr.nextLine();
                        }
                        catch(InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                            scnr.nextLine(); // Consume the invalid input to avoid an infinite loop
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                            break;
                        }

                        Tenant tenant = new Tenant(userString, userInt);
                        tenantList.add(tenant);
                        break;
                    }    
                    break;
                case 'r': // Remove a tenant

                    // if tenant list empty, show message and exit
                    if (tenantList.size() <= 0){
                        System.out.println("\nTenant list is empty.");
                        break;
                    }

                    System.out.println("\nRemove a new tenant\n");
                    boolean tenantFound = false;
                    while(!tenantFound){
                        System.out.println("Enter a name:");
                        userString = scnr.nextLine();
                        try{    
                            for (Tenant ten : tenantList){
                                if (ten.getName().equals(userString)){
                                    tenantFound = true;
                                    System.out.println("\nTenant " + ten.getName() + " has been removed.");
                                    tenantList.remove(ten);
                                    break;
                                }
                            }
                            if (tenantFound == false){
                                throw new Exception("\nA tenant by that name does not exist.");
                            }
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                            break;
                        }
                        break;
                    }   
                    break;
                case 'b': // go back
                    running = false;
                    break;
            
                default:
                    break;
            }

        } while (running);
    }
    
}
