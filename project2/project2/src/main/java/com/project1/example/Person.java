package com.project1.example;

import java.util.ArrayList;
import javax.swing.JFrame;

/**
* The Person abstract class is used to give structure to the Boss Employee and Tenant classes
* @author Alex Peffer
*/

abstract class Person {
    protected String name;
    protected ArrayList<WorkRequest> work = new ArrayList<WorkRequest>();


    /**
    * The handler of the user's responses to the menu.
    *
    * @param scnr handles user inputs
    * @param partlist allows manipulation or checking for existance of a part
    * @param masterWorkList allows manipulation or viewing of the master work request list.
    */
    abstract JFrame login(ArrayList<Part> partList, ArrayList<WorkRequest> masterWorkList);
}
