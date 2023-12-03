package com.project1.example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;



public class Tenant extends Person{
    private int aptNum;
    private JFrame tenantFrame;
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
    public void createWorkRequest(ArrayList<WorkRequest> masterWorkList){
        JDialog dialog = new JDialog(tenantFrame);
        JButton createButton = new JButton("Create");
        JButton cancelButton = new JButton("Cancel");
        

        dialog.setLayout(new FlowLayout());
        dialog.setSize(new Dimension(420,420));
        dialog.setPreferredSize(new Dimension(420,420));

        ButtonGroup btnGroup = new ButtonGroup();

        JRadioButton lightBtn = new JRadioButton("light bulb", true);
        lightBtn.setActionCommand("light bulb");
        dialog.add(lightBtn);
        JRadioButton airBtn = new JRadioButton("air filter");
        airBtn.setActionCommand("air filter");
        dialog.add(airBtn);
        JRadioButton paintBtn = new JRadioButton("paint");
        paintBtn.setActionCommand("paint");
        dialog.add(paintBtn);

        btnGroup.add(lightBtn);
        btnGroup.add(airBtn);
        btnGroup.add(paintBtn);

        createButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                    String temp = btnGroup.getSelection().getActionCommand();
                    WorkRequest request = new WorkRequest(name, aptNum, LocalDate.now(), new Part(temp));
                    work.add(request);
                    masterWorkList.add(request);
                    System.out.println("New work order for: " + temp +" hopefully created");
            }
        });

        createButton.addActionListener(e-> dialog.dispose());
        cancelButton.addActionListener(e-> dialog.dispose());

        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.PAGE_AXIS));
        dialog.setTitle("Create A Work Request");
        dialog.add(new JLabel("Select a part: "));
        dialog.add(lightBtn);
        dialog.add(airBtn);
        dialog.add(paintBtn);
        dialog.add(createButton);
        dialog.add(cancelButton);

        dialog.setVisible(true);

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
    public JFrame login(ArrayList<Part> partList, ArrayList<WorkRequest> masterWorkList){
        tenantFrame = new JFrame(this.name + "'s Account");
        JPanel panel; 
        JButton button;
        JTable wrTable;
        //wrTextArea.setEditable(false);

        String [] columnNames = {"Date", "Part Required", "Priority", "Status"};

        Object[][] data = new Object[10][4];
        JLabel label = new JLabel(name + "'s Work Requests:");

        panel = new JPanel(new FlowLayout());
        panel.setSize(new Dimension(665,300));
        panel.setPreferredSize(new Dimension(665,300));

        panel.add(label);

        String [] dataString = new String[4];
        if (work.size()>0){
            for (int i = 0; i < work.size(); i++){
                dataString[0] = work.get(i).getDate().toString();
                dataString[1] = work.get(i).getPart().getName();
                dataString[2] = String.valueOf(work.get(i).getPriority());
                dataString[3] = work.get(i).getStatus();
                System.out.println(dataString[0] + dataString[1] + dataString[2] + dataString[3]);
                for (int j = 0; j < 4; j++){                 
                    data[i][j] = dataString[j];
                }
            }
            wrTable = new JTable(data, columnNames);
            wrTable.setEnabled(false);
            //panel.add(wrTable);
            JScrollPane jScrollPane = new JScrollPane(wrTable);
            jScrollPane.setSize(600, 182);
            jScrollPane.setPreferredSize(new Dimension(600, 182));
            //for (int i = 0; i < masterWorkList.size(); i++)
            panel.add(jScrollPane);
        }
        else{
            JLabel emptyLabel = new JLabel("No work requests");
            panel.add(emptyLabel);
        }

        button = new JButton("New Work Request");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if (masterWorkList.size() > 25){
                    button.setEnabled(false);
                    JLabel label = new JLabel("Work Request list is Full!");
                    label.setForeground(Color.RED);
                    panel.add(label);
                    tenantFrame.pack();
                }
                else{
                    createWorkRequest(masterWorkList);
                    panel.revalidate();
                    panel.repaint();
                }
                
                
            }
        });

        panel.add(button);

        tenantFrame.add(panel);

        tenantFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        System.out.println(this.name + " has logged in succesfully");
        tenantFrame.pack();
        tenantFrame.setVisible(true);
        return tenantFrame;
        }
}