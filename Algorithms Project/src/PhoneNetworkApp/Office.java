package PhoneNetworkApp;

import GraphFramework.Vertex;


/*
 *  @authors Asil, Qamar, Arub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
public class Office extends Vertex {

    public Office() {
        setLabel();
    }

    // Methods
    public void setLabel() {
        super(label);

    }

    @Override
    public void displayInfo() {
        // 
        System.out.print("The lable is:" + label);
    } // End of method

} // End of class

