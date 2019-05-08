package Model;

import Model.Highway.Lane;
import Model.Vehicles.Car;
import Model.Vehicles.Vehicle;


public class Test {

    public static void main(String[] args){
            Simulation simulation = new Simulation();
        while(true){
            simulation.getHighway().roads[0].generateNextFrame();
        }
    }
}
