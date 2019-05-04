package Model;

import Model.Highway.Lane;
import Model.Vehicles.Car;
import Model.Vehicles.Vehicle;


public class Test {

    public static void main(String[] args){
        Lane testLane = new Lane();
        Vehicle car1 = new Car();
        Vehicle car2 = new Car();
        testLane.lane[5].vehicle = car1;
        testLane.lane[5].occupied = true;
        testLane.lane[8].vehicle = car2;
        testLane.lane[8].occupied = true;
        while(true){
            car1.calculateNextVelocity();
            car2.calculateNextVelocity();
        }
    }
}
