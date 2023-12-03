package com.project1.example;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;







/**
 * Class to give an employee its operations and functionality
 * 
 * @author Alex Peffer
 */
public class Employee extends Person {
    private ArrayList<Tenant> tenantList;
    JFrame employeeFrame;

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
    public JFrame login(ArrayList<Part> partList, ArrayList<WorkRequest> masterWorkList){
        System.out.println(this.name + " has logged in succesfully");
        employeeFrame = new JFrame("Employee " + this.name + "'s Account");
        JPanel panel; 
        JButton button;
        JTable wrTable;
        //wrTextArea.setEditable(false);
        JLabel label = new JLabel("All Work Requests:");

        panel = new JPanel(new FlowLayout());
        panel.setSize(new Dimension(645,500));
        panel.setPreferredSize(new Dimension(645,500));

        panel.add(label);
        

        System.out.println("  #  |     Date      | Part Required | Priority |    Status     |   Assigned    |   Apt #   | Tenant ");

        String [] columnNames = {"#", "Date", "Part Required", "Priority", "Status", "Assigned", "Apt #", "Tenant"};
        Object[][] data = new Object[25][8];
        String [] dataString = new String[8];
        if (work.size()>0){
            for (int i = 0; i < work.size(); i++){
                dataString[0] = String.valueOf(masterWorkList.indexOf(work.get(i)));
                dataString[1] = work.get(i).getDate().toString();
                dataString[2] = work.get(i).getPart().getName();
                dataString[3] = String.valueOf(work.get(i).getPriority());
                dataString[4] = work.get(i).getStatus();
                dataString[5] = this.name;
                dataString[6] = String.valueOf(work.get(i).getAptNum());
                dataString[7] = work.get(i).getName();
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
        
        String[] wrNums = new String[10];
        for (int i = 0; i < work.size(); i++){
            wrNums[i] = String.valueOf(masterWorkList.indexOf(work.get(i)));
        }
        JComboBox wrNumComboBox = new JComboBox(wrNums);

        
        
        

        button = new JButton("Work");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Doing work on " + wrNumComboBox.getSelectedItem());
                int myWr = Integer.parseInt(wrNumComboBox.getSelectedItem().toString());
                if(masterWorkList.get(myWr).getStatus().equals("Closed")){
                    JOptionPane.showMessageDialog(employeeFrame, "That request is already closed");
                }else{
                    masterWorkList.get(myWr).setStatus("Closed");
                    System.out.println("Work complete");
                }
                
            }
        });

        
        if (work.size()>0 ){
            panel.add(new JLabel("Request Number: "));
            panel.add(wrNumComboBox);
            panel.add(button);
        }
        

        employeeFrame.add(panel);

        employeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        System.out.println(this.name + " has logged in succesfully");
        employeeFrame.pack();
        employeeFrame.setVisible(true);
        return employeeFrame;
        }
    
}
