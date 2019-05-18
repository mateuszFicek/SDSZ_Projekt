package Model;

import Model.Highway.Highway;
import Model.Vehicles.Car;
import Model.Vehicles.Vehicle;
import java.util.Random;

public class Simulation {

    Random rand = new Random();
    private Highway highway;
    private int startingNumberOfCars;

    public Simulation() {
        highway = new Highway(2);
        highway.setupHighway();


//       CODE FOR TESTING
        Vehicle car = new Car();
        car.increaseVelocity(5);
        Vehicle car2 = new Car();
        Vehicle car3 = new Car();
        car.increaseVelocity(0);
        highway.roads[0].road[1].lane.get(50).vehicle = car;
        highway.roads[0].road[1].lane.get(50).occupied = true;
        highway.roads[0].road[1].lane.get(51).vehicle = car2;
        highway.roads[0].road[1].lane.get(51).occupied = true;
        highway.roads[0].road[1].lane.get(52).vehicle= car3;
        highway.roads[0].road[1].lane.get(52).occupied = true;
        highway.roads[0].moveCarsNeighbourhoods(1);
    }

    public Simulation(Settings settings){
        highway = new Highway(2);
        highway.setupHighway();

        switch (settings.getTime()){
            case 0:
                startingNumberOfCars = 200;
                break;
            case 1:
                startingNumberOfCars = 400;
                break;
            case 2:
                startingNumberOfCars = 1000;
                break;
        }

        for(int i = 0; i < startingNumberOfCars; ++i){
            int randomRoads = rand.nextInt(2);
            int randomRoad = rand.nextInt(2);
            int randomLane = rand.nextInt(8353);
            highway.roads[randomRoads].road[randomRoad+1].lane.get(randomLane).vehicle = new Car(settings.getCarMaxVelocity(), rand.nextInt(3)+3, rand.nextInt(3) + 1);
            highway.roads[randomRoads].road[randomRoad+1].lane.get(randomLane).occupied = true;
        }
        for(int i = 0; i < highway.roads.length; ++i){
            for(int j = 0; j < highway.roads[i].road.length; ++j){
                highway.roads[i].moveCarsNeighbourhoods(j);
            }
        }
    }



    public Highway getHighway() {
        return highway;
    }

    public void setHighway(Highway highway) {
        this.highway = highway;
    }

}
