package com.project1.example.GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.*;
import com.project1.example.Boss;
import com.project1.example.Employee;
import com.project1.example.Part;
import com.project1.example.Tenant;
import com.project1.example.WorkRequest;


public class MainFrame extends Frame{

    /**
     * GUI COMPONENTS
     */
    private JFrame frame;
    // Login
    private JPanel mainPanel = new JPanel();
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;

    private JPanel cards;

    // Boss
    private JPanel bossLoginPanel = new JPanel();
    private JTextField bossLoginTextField = new JTextField();
    private JButton bossLoginButton = new JButton("Login");

    private JPanel empLoginPanel = new JPanel();
    private JTextField empLoginTextField = new JTextField();
    private JButton empLoginButton = new JButton("Login");

    private JPanel tenLoginPanel = new JPanel();
    private JTextField tenLoginTextField = new JTextField();
    private JButton tenLoginButton = new JButton("Login");
    private JFrame loggedInFrame;

    CardLayout cl = new CardLayout();

    // call initialize
    public MainFrame(){
        initialize();
    }

    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());
    }

    private void initialize(){
        //-------- Initialize Lists
        ArrayList<Tenant> tenantList = new ArrayList<Tenant>();
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        ArrayList<Part> stockRoom = new ArrayList<Part>();
        ArrayList<WorkRequest> masterWorkList = new ArrayList<WorkRequest>();


        //------- Initialize Boss. Name = Admin
        Boss boss = new Boss("Admin");
        boss.setEmployeeList(employeeList);

        //-------- Initialize some tenants
        Tenant Alex = new Tenant("Alex", 1001);
        tenantList.add(Alex);

        Tenant Jelly = new Tenant("Jelly", 1002);
        tenantList.add(Jelly);

        Tenant Bob = new Tenant("Bob", 1003);
        tenantList.add(Bob);


        //-------- Initialize some Employees
        Employee Jeff = new Employee("Jeff");
        employeeList.add(Jeff);
        Jeff.giveList(tenantList);

        Employee Steve = new Employee("Steve");
        employeeList.add(Steve);
        Steve.giveList(tenantList);

        //--------create parts and add to stockroom
        Part lightbulb = new Part("light bulb");
        Part airFilter = new Part("air filter");
        Part paint = new Part("paint");
        stockRoom.add(lightbulb);
        stockRoom.add(airFilter);
        stockRoom.add(paint);


        // ------------ create main frame
        frame = new JFrame("Work Request Program");
        frame.setSize(420,420);
        frame.setPreferredSize(new Dimension(310, 180));
        


        //-------------------------------------------------------BEGIN create menu items
        menuBar = new JMenuBar();
        menu = new JMenu("Log in");
        menuBar.add(menu);

        menuItem = new JMenuItem("Boss");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Boss menu option clicked");
                cl.show(mainPanel, "BossLogin");
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Employee");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Employee menu option clicked");
                cl.show(mainPanel, "EmployeeLogin");
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Tenant");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Tenant menu option clicked");
                cl.show(mainPanel, "TenantLogin");
                
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Log out");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Log out clicked");
                cl.show(mainPanel, "BossLogin");

                //close logged-in frame
                if(loggedInFrame != null){
                    loggedInFrame.dispose();
                }
                

                // reset disabled fields
                bossLoginButton.setEnabled(true);
                bossLoginTextField.setText("");
                bossLoginTextField.setEnabled(true);
                empLoginButton.setEnabled(true);
                empLoginTextField.setText("");
                empLoginTextField.setEnabled(true);
                tenLoginButton.setEnabled(true);
                tenLoginTextField.setText("");
                tenLoginTextField.setEnabled(true);

                menu.setEnabled(true);
                menuItem.setEnabled(false);

                
            }
        });
        menuItem.setEnabled(false);
        menuBar.add(menuItem);
        
//-----------------------------------------------------------------END create menu items
        //card layout
        mainPanel.setLayout(cl);

        /**
         * BOSS LOGIN AREA
         */       
        bossLoginPanel.setBackground(Color.GRAY);
        bossLoginPanel.add(new JLabel("Boss Login: "));
        bossLoginTextField.setPreferredSize(new Dimension(250,32));
        bossLoginPanel.add(bossLoginTextField);
        bossLoginPanel.add(bossLoginButton);
        bossLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println("Boss login attempt:");
                if (bossLoginTextField.getText().equals("Admin")){
                    //disable login options
                    bossLoginButton.setEnabled(false);
                    bossLoginTextField.setEnabled(false);
                    menu.setEnabled(false);
                    // enable logout option
                    menuItem.setEnabled(true);
                    //login
                    loggedInFrame = boss.login(stockRoom, masterWorkList);
                } 
                else{
                        JOptionPane.showMessageDialog(bossLoginPanel, "That is not the boss's username");
                }
            }
        });
        
        /**
         * EMPLOYEE LOGIN AREA
         */  
        empLoginPanel.setBackground(Color.GREEN);
        empLoginPanel.add(new JLabel("Employee Login: "));
        empLoginTextField.setPreferredSize(new Dimension(250,32));
        empLoginPanel.add(empLoginTextField);
        empLoginPanel.add(empLoginButton);
        empLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                boolean employeeFound = false;
                System.out.println("Employee login attempt:");
                for (Employee employee : employeeList){
                    if (empLoginTextField.getText().equals(employee.getName())){
                        employeeFound = true;
                        //disable login options
                        empLoginButton.setEnabled(false);
                        empLoginTextField.setEnabled(false);
                        menu.setEnabled(false);
                        // enable logout option
                        menuItem.setEnabled(true);
                        //login
                        loggedInFrame = employee.login(stockRoom, masterWorkList);
                        break;
                    }
                }
                if (!employeeFound){  
                    JOptionPane.showMessageDialog(empLoginPanel, "That employee doesn't exist");
                }
                
            }
        });
        
        /**
         * TENANT LOGIN AREA
         */
        tenLoginPanel.setBackground(Color.YELLOW);
        tenLoginPanel.add(new JLabel("Tenant Login: "));
        tenLoginTextField.setPreferredSize(new Dimension(250,32));
        tenLoginPanel.add(tenLoginTextField);
        tenLoginPanel.add(tenLoginButton);
        tenLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                boolean tenantFound = false;
                System.out.println("Tenant login attempt:");
                for (Tenant tenant : tenantList){
                    if (tenLoginTextField.getText().equals(tenant.getName())){
                        tenantFound = true;
                        //disable login options
                        tenLoginButton.setEnabled(false);
                        tenLoginTextField.setEnabled(false);
                        menu.setEnabled(false);
                        // enable logout option
                        menuItem.setEnabled(true);
                        //login
                        loggedInFrame = tenant.login(stockRoom, masterWorkList);

                        break;
                    }
                }
                if (!tenantFound){  
                    JOptionPane.showMessageDialog(tenLoginPanel, "That tenant doesn't exist");
                }
                
            }
        });


        //add to main panel
        mainPanel.add(bossLoginPanel, "BossLogin");
        mainPanel.add(empLoginPanel, "EmployeeLogin");
        mainPanel.add(tenLoginPanel, "TenantLogin");

        cl.show(mainPanel, "BossLogin");

        //add everything into frame
        frame.setJMenuBar(menuBar);
        frame.add(mainPanel);
  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void show(){
        frame.setVisible(true);
    }
            
}
