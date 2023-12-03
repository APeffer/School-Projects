package com.project1.example;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;


/**
* The Boss class creates a boss that can:
* view all work requests, assign work to employees, set work request priority, or change work request status.
*
* @author Alex Peffer
*/
public class Boss extends Person{
    private ArrayList<Employee> employeeList;
    ArrayList<WorkRequest> masterWorkList;
    private JFrame bossFrame;
    
    /**
    * Constructor for a Boss
    *
    * @param String name
    */
    public Boss(String name){
        this.name = name;
    }

    /**
    * Returns the boss's name
    *
    * @return String name
    */
    public String getName(){
        return name;
    }

    /**
    * Gives the boss access to the list of all existing employees
    *
    * @param employeeList  a list of all existing employees
    */
    public void setEmployeeList(ArrayList<Employee> employeeList){
        this.employeeList = employeeList;
    }

    /**
    * Prints the menu for the boss to interact with when signed into boss
    *
    */
    public void printMenu(){
        
        System.out.println("\nv - View all work requests.");
        System.out.println("a - Assign work to an employee");
        System.out.println("p - Set work request priority level");
        System.out.println("s - change the status of work request");
        System.out.println("b - Go back");
        
    }

    /**
    * Logs in to a specific employee so that the user may function inside their menu.
    * v - View all work requests
    * a - Assign work to an employee
    * p - Set work request priority level
    * s - change the status of work request
    * b - Go back
    *
    * @param scnr is a scanner to input user options
    * @param partlist is the list of all available parts
    * @param masterWorkList is the list of all work requests
    */
    public void printWorkRequests(ArrayList<WorkRequest> masterWorkList){
        if (masterWorkList.size() > 0){
            System.out.println("\n All Work Requests:\n");
            System.out.println("  #  |     Date      | Part Required | Priority |    Status     |   Assigned    |   Apt #   | Tenant ");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            for (WorkRequest request : masterWorkList){
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
    * Assigns a work request to an employee
    *
    * @param workRequestNumber the number of the work request that should be assigned to the given employee.
    * @param employee the employee that the work request should be assigned to
    * @param masterWorkList the master work list. given to update the info on the master work list.
    */
    public void assignWork(int workRequestNumber, Employee employee, ArrayList<WorkRequest> masterWorkList){
        masterWorkList.get(workRequestNumber).setEmployee(employee);
        System.out.println("\nWork request #" + workRequestNumber + " assigned to " + employee.getName());
        employee.addWork(masterWorkList.get(workRequestNumber));
    }


     /**
    * Logs in to a boss so that the user may function inside their menu.
    * a - Assign work to an employee
    * p - Set work request priority level
    * s - change the status of work request
    * b - Go back
    *
    * @param scnr is a scanner to input user options
    * @param partlist is the list of all available parts
    * @param masterWorkList is the list of all work requests
    */
    public JFrame login(ArrayList<Part> partList, ArrayList<WorkRequest> masterWorkList){
        System.out.println(this.name + " has logged in succesfully");
        bossFrame = new JFrame(this.name + "'s Account");
        JPanel panel; 
        JButton button;
        JTable wrTable;
        //wrTextArea.setEditable(false);
        System.out.println("  #  |     Date      | Part Required | Priority |    Status     |   Assigned    |   Apt #   | Tenant ");
        String [] columnNames = {"#", "Date", "Part Required", "Priority", "Status", "Assigned", "Apt #", "Tenant"};

        Object[][] data = new Object[25][8];
        JLabel label = new JLabel("All Work Requests:");

        panel = new JPanel(new FlowLayout());
        panel.setSize(new Dimension(645,500));
        panel.setPreferredSize(new Dimension(645,500));

        panel.add(label);

        String [] dataString = new String[8];
        if (masterWorkList.size()>0){
            String temp = "Unassigned";
            for (int i = 0; i < masterWorkList.size(); i++){
                if(masterWorkList.get(i).getEmployee() != null){
                    temp = masterWorkList.get(i).getEmployee().getName();
                }
                else{
                    temp = "Unassigned";
                }
                dataString[0] = String.valueOf(i);
                dataString[1] = masterWorkList.get(i).getDate().toString();
                dataString[2] = masterWorkList.get(i).getPart().getName();
                dataString[3] = String.valueOf(masterWorkList.get(i).getPriority());
                dataString[4] = masterWorkList.get(i).getStatus();
                dataString[5] = temp;
                dataString[6] = String.valueOf(masterWorkList.get(i).getAptNum());
                dataString[7] = masterWorkList.get(i).getName();
                System.out.println(dataString[0] + dataString[1] + dataString[2] + dataString[3]);
                for (int j = 0; j < 8; j++){
                    
                    data[i][j] = dataString[j];
                    
                }
            }
            wrTable = new JTable(data, columnNames);
            wrTable.setColumnSelectionAllowed(false);
            wrTable.setRowSelectionAllowed(false);
            JScrollPane jScrollPane = new JScrollPane(wrTable);
            jScrollPane.setSize(600, 400);
            jScrollPane.setPreferredSize(new Dimension(600, 400));
            //for (int i = 0; i < masterWorkList.size(); i++)
            panel.add(jScrollPane);
        }
        else{
            JLabel emptyLabel = new JLabel("No work requests");
            panel.add(emptyLabel);
        }

        // WORK REQUEST NUMBER ComboBox
        String[] wrNums = new String[25];
        for (int i = 0; i < masterWorkList.size(); i++){
            wrNums[i] = String.valueOf(i);
        }
        JComboBox wrNumComboBox = new JComboBox(wrNums);
        

        //WORK REQUEST PRIO COMBO BOX
        String[] wrPrio = new String[4];
        for (int i = 0; i < 4; i++){
            wrPrio[i] = String.valueOf(i);
        }
        JComboBox wrPrioComboBox = new JComboBox(wrPrio);
        
        //WORK REQUEST EMPLOYEE ComboBox
        String[] wrEmp = new String[25];
        for (int i = 0; i < employeeList.size(); i++){
            wrEmp[i] = employeeList.get(i).getName();
        }
        JComboBox wrEmpComboBox = new JComboBox(wrEmp);

        
        
        
        

        button = new JButton("Confirm Change");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Attempting to change WR");
                int tempInt = Integer.parseInt(wrNumComboBox.getSelectedItem().toString());
                Employee tempEmp = null;
                for (int i = 0; i < employeeList.size(); i++){
                    if (employeeList.get(i).getName().equals(wrEmpComboBox.getSelectedItem())){
                        System.out.println("employee found");
                        tempEmp = employeeList.get(i);
                        break;
                    }
                }
                if (tempEmp != null){
                    assignWork(tempInt, tempEmp, masterWorkList);
                    System.out.println("assigning work");
                }
                System.out.println("^^^ was emp found?");

                masterWorkList.get(tempInt).setPriority(Integer.parseInt(wrPrioComboBox.getSelectedItem().toString()));
            }
        });

        if (masterWorkList.size() > 0){
            panel.add(new JLabel("Request Number: "));
            panel.add(wrNumComboBox);
            panel.add(new JLabel("Priority: "));
            panel.add(wrPrioComboBox);
            panel.add(new JLabel("Employee: "));
            panel.add(wrEmpComboBox);
            panel.add(button);
        }

        

        bossFrame.add(panel);

        bossFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        System.out.println(this.name + " has logged in succesfully");
        bossFrame.pack();
        bossFrame.setVisible(true);
        return bossFrame;
        /* 
        boolean running = true;
        boolean validAssign = false;
        char userOption;
        String userString;
        int userInt = -1;
        int priority = 0;

        do{
            printMenu();
            userOption = scnr.nextLine().charAt(0);
            switch (userOption) {

                case 'v': // Print all work requests
                    printWorkRequests(masterWorkList);
                    break;
                case 'a': // Assign work to an employee
                    
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
                            
                            // get user input for employee
                            System.out.println("\nEnter the employee to assign this request to:");
                            System.out.println("-1 to go back");
                            userString = scnr.nextLine();

                            // if -1 then go back
                            if (userString == "-1"){
                                break;
                            }
                            
                            for (Employee employee : employeeList){
                                if (employee.getName().equals(userString)){
                                    validAssign = true;
                                    assignWork(userInt, employee, masterWorkList);
                                    break;
                                }
                            }
                            if (validAssign == false){
                                throw new Exception("\nInvalid input. Employee not found.");
                            }
                            else{
                                validAssign = false;
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
                case 'p': // set priority
                    while(true){
                        
                        try {
                            System.out.println("\nEnter the work request number:");
                            System.out.println("-1 to go back");

                            // get user input for work request number
                            userInt = scnr.nextInt();
                            scnr.nextLine();
                            
                            // if -1 then go back
                            if (userInt == -1){
                                System.out.println("userInt == -1");
                                break;
                            }

                            System.out.println("after userInt == -1 block");
                            System.out.println("userint: " + userInt);

                            // this line will throw a InputMismatch exception if the work request number does not exist
                            masterWorkList.get(userInt);
                            
                            // get user input for employee
                            System.out.println("\nEnter the priority for this work request");
                            System.out.println("0  for Unassigned");
                            System.out.println("1  for Emergency");
                            System.out.println("2  for Urgent");
                            System.out.println("3  for Routine");
                            System.out.println("-1 to go back");
                            priority = scnr.nextInt();
                            scnr.nextLine();
                            
                            //break if -1
                            if (userInt == -1){
                                System.out.println("userInt == -1");
                                break;
                            }

                            // check for valid integer
                            if ((userInt < -1) || (userInt > 3)){
                                throw new InputMismatchException();
                            }  
                            else{
                                masterWorkList.get(userInt).setPriority(priority);
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
                    break;
                case 's': //change the status of the work request          
                    while(true){
                        
                        try {
                            System.out.println("\nEnter the work request number:");
                            System.out.println("-1 to go back\n");

                            // get user input for work request number
                            userInt = scnr.nextInt();
                            scnr.nextLine();
                            
                            // if -1 then go back
                            if (userInt == -1){
                                break;
                            }

                            // this line will throw a InputMismatch exception if the work request number does not exist
                            masterWorkList.get(userInt);
                            
                            // get user input for employee
                            System.out.println("\nEnter the status of this work order:");
                            System.out.println("open - still needs to be worked on");
                            System.out.println("part - awaiting part");
                            System.out.println("closed - work complete");
                            System.out.println("-1 to go back\n");
                            userString = scnr.nextLine();
                            if (userString == "-1"){
                                break;
                            }

                            if ((userString.equals("open")) || (userString.equals("part")) || (userString.equals("closed"))){
                                masterWorkList.get(userInt).setStatus(userString);
                                System.out.println("Status of work order #" + userInt + " changed to " + userString);
                                break;
                            }
                            else{
                                System.out.println("\nNot a valid input for a work request status\n");
                            }                     

                        }
                        catch (InputMismatchException e) {
                            System.out.println("\nInvalid input. Please enter a valid integer.");
                            scnr.nextLine(); // Consume the invalid input to avoid an infinite loop
                        }
                        catch (IndexOutOfBoundsException e){
                            System.out.println("\nInvalid input. That work request does not exist.");
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
                case 'b': // go back
                    running = false;
                    break;
            
                default:
                    break;
            }


        } while (running);
    */}
}

