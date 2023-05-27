package PhoneNetworkApp;

import GraphFramework.Vertex;


/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
public class Office extends Vertex {

    public Office(String label) {
        super(label);
    }

    // Methods
    @Override
    public void setLabel(String label) {
        super.setLabel("O"+label);
    }
    @Override
    public void displayInfo() {
        super.displayInfo();
    } 

} 
