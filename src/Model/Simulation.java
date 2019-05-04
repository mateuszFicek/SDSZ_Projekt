package Model;

import Model.Highway.Highway;
import Model.Vehicles.Car;
import Model.Vehicles.Vehicle;

public class Simulation {

    private Highway highway;
    private int time;

    public Simulation(){
        highway = new Highway(2);
        highway.setupHighway();

//       CODE FOR TESTING
//        Vehicle car = new Car();
//        Vehicle car2 = new Car();
//        highway.roads[0].road[2].lane[50].vehicle = car;
//        highway.roads[0].road[2].lane[50].occupied = true;
//        highway.roads[0].road[2].lane[51].vehicle = car;
//        highway.roads[0].road[2].lane[51].occupied = true;
    }


    public Highway getHighway() {
        return highway;
    }

    public void setHighway(Highway highway) {
        this.highway = highway;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
