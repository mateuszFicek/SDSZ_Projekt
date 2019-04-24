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
            car1.Neighbourhood[1][5] = testLane.lane[5];
            car1.Neighbourhood[1][6] = testLane.lane[6];
            car1.Neighbourhood[1][7] = testLane.lane[7];
            car1.Neighbourhood[1][8] = testLane.lane[8];
            car1.Neighbourhood[1][9] = testLane.lane[9];
            car1.Neighbourhood[1][10] = testLane.lane[10];
            car2.Neighbourhood[1][5] = testLane.lane[9];
            car2.Neighbourhood[1][6] = testLane.lane[10];
            car2.Neighbourhood[1][7] = testLane.lane[11];
            car2.Neighbourhood[1][8] = testLane.lane[12];
            car2.Neighbourhood[1][9] = testLane.lane[13];
            car2.Neighbourhood[1][10] = testLane.lane[14];
            car1.calculateNextVelocity();
            car2.calculateNextVelocity();
        }
    }
}
