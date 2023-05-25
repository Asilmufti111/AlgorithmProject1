package PhoneNetworkApp;

import GraphFramework.*;

/*
 *  @authors Asil, Qamar, Aroub,Khalida
 * B9A
 * CPCS-324
 * Project Code
 * 18th may. 2023
 */
public class Line extends Edge {

    private int Ilength;

    public Line(Vertex source, Vertex target, int weight) {
        super(source, target, weight);
        this.Ilength = weight * 5;
    }

    // Methods
    @Override
    public void displayInfo() {
        
        System.out.print("Office No. " +super.getSource().getLabel() );
        System.out.print(" - ");
        System.out.print("Office No. " + super.getTarget().getLabel() +" ");
        System.out.print("line length: " + getIlength());
    } // End of Method

    public int getIlength() {
        return Ilength;
    }

} // End of Class
