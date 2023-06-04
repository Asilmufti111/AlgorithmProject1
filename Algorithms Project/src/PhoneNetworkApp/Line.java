package PhoneNetworkApp;

import GraphFramework.*;

/*
 *  @authors Asil, Qamar, Aroub,Khalida,Huda
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

        String srcLabel =  super.getSource().getLabel();
        
        super.getSource().setLabel("O"+srcLabel);
        
        String trgtLabel =  super.getTarget().getLabel();
        
        super.getTarget().setLabel("O"+trgtLabel);

        System.out.print("Office No. " + super.getSource().getLabel() );
        
        System.out.print(" - ");
        
        System.out.print("Office No. " + super.getTarget().getLabel() +" ");

                
        super.getSource().setLabel(srcLabel);
        
        
        super.getTarget().setLabel(trgtLabel);
        
        
        System.out.print("line length: " + getIlength());
    } // End of Method

    public int getIlength() {
        return Ilength;
    }

} // End of Class
