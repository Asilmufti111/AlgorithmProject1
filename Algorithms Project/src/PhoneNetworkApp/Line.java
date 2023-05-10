package PhoneNetworkApp;

import GraphFramework.*;

public class Line extends Edge {

    private int Ilength;

    public Line(Vertex source, Vertex target, int weight) {
        super(source, target, weight);
        this.Ilength = weight * 5;
    }

    // Methods
    @Override
    public void displayInfo() {
        // Output as required: road name: road x1 road size: 20
        System.out.print("line length: " + getIlength());
    } // End of Method

    public int getIlength() {
        return Ilength;
    }

} // End of Class
