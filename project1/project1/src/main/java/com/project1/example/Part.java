package com.project1.example;

/**
* The Part class is used to create parts 
* parts are added to the stockRoom parts list
* the 3 parts are "light bulb", "air filter", or "paint".
*
* @author Alex Peffer
*/
public class Part {
    private String partName;
    private int quantity;

    /**
    * Constructor for the Part class
    * the quantity of all parts upon initialization is 1.
    *
    * @param partName the name of the part.
    */
    public Part(String partName){
        this.partName = partName;
        this.quantity = 1;
    }

    /**
    * Returns the name of the part
    *
    * @return partName a string with the name of the part
    */
    public String getName(){
        return partName;
    }

    /**
    * Returns the quantity of the part
    *
    * @return quantity a int representing how many of this part exist
    */
    public int getQuantity(){
        return quantity;
    }

    /**
    * This method is used when an employee works on a work request.
    * 1 part is consumed for each request.
    *
    */
    public void usePart(){
        quantity--;
    }

    /**
    * This method is used when an employee needs to order more of a part to complete their work.
    * although the method can take any amount, the employee will always only order 2 at a time.
    *
    */
    public void orderMore(int amount){
        this.quantity += amount;
    }
}
