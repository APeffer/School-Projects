package com.project1.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
* The Person abstract class is used to give structure to the Boss Employee and Tenant classes
* @author Alex Peffer
*/

abstract class Person {
    protected String name;
    protected ArrayList<WorkRequest> work = new ArrayList<WorkRequest>();

    /**
    * Prints the menu for the user to interact with
    *
    */
    abstract void printMenu();

    /**
    * The handler of the user's responses to the menu.
    *
    * @param scnr handles user inputs
    * @param partlist allows manipulation or checking for existance of a part
    * @param masterWorkList allows manipulation or viewing of the master work request list.
    */
    abstract void login(Scanner scnr, ArrayList<Part> partList, ArrayList<WorkRequest> masterWorkList);
}
