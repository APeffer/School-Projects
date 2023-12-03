package com.project1.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        ArrayList<Tenant> tenantList = new ArrayList<Tenant>();
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        ArrayList<Part> stockRoom = new ArrayList<Part>();
        ArrayList<WorkRequest> masterWorkList = new ArrayList<WorkRequest>();
        char userOption;
        String name;
        boolean mainRunning = true;
        boolean loginRunning = false;
        boolean personFound = false;

        Boss boss = new Boss("Admin");
        boss.setEmployeeList(employeeList);



        Tenant Alex = new Tenant("Alex", 1001);
        tenantList.add(Alex);

        Tenant Jelly = new Tenant("Jelly", 1002);
        tenantList.add(Jelly);

        Tenant Bob = new Tenant("Bob", 1003);
        tenantList.add(Bob);

        Employee Jeff = new Employee("Jeff");
        employeeList.add(Jeff);
        Jeff.giveList(tenantList);

        // create parts and add to stockroom
        Part lightbulb = new Part("light bulb");
        Part airFilter = new Part("air filter");
        Part paint = new Part("paint");
        stockRoom.add(lightbulb);
        stockRoom.add(airFilter);
        stockRoom.add(paint);

        // login menu
        while(mainRunning){
            System.out.println("\n---- Login ----");
            System.out.println("Enter \'b\' for boss, \'e\' for employee, \'t\' for Tenant, or \'q\' to quit.");
            userOption = scnr.nextLine().charAt(0);
            switch (userOption) {
            case 'b':
                System.out.println("\nEnter your Boss name:");
                name = scnr.nextLine();
                
                if (boss.getName().equals(name)){
                    personFound = true;
                    boss.login(scnr, stockRoom, masterWorkList);
                }
                if (personFound == false){
                    System.out.println("\nError: That is not the boss's login name.\n");
                }

                personFound = false;
                break;
            case 'e':
                loginRunning = true;
                while(loginRunning){

                    System.out.println("\nEnter your Employee name:");
                    
                    name = scnr.nextLine();
                    for(Employee employee : employeeList){
                        if(employee.getName().equals(name)){
                            personFound = true;
                            employee.login(scnr, stockRoom, masterWorkList);
                        }
                    }

                    if (personFound == false){
                        System.out.println("\nEmployee does not exist.\n");
                    }

                    personFound = false;
                    loginRunning = false;
                }
                break;
            case 't':
                loginRunning = true;
                while(loginRunning){

                    System.out.println("\nEnter your Tenant name:");
                    
                    name = scnr.nextLine();
                    for(Tenant tenant : tenantList){
                        if(tenant.getName().equals(name)){
                            personFound = true;
                            tenant.login(scnr, stockRoom, masterWorkList);
                        }
                    }

                    if (personFound == false){
                        System.out.println("\nTenant does not exist.\n");
                    }

                    personFound = false;
                    loginRunning = false;
                }
                break;
            case 'q':
                mainRunning = false;
                break;
        
            default:
                System.out.println("That is not a valid login option.");
                break;
            }
        }   
        
        scnr.close();
    }
}