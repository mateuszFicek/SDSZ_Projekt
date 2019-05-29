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
        highway.roads[0].road[1].lane.get(52).vehicle = car3;
        highway.roads[0].road[1].lane.get(52).occupied = true;
        highway.roads[0].moveCarsNeighbourhoods(1);
    }

    public Simulation(Settings settings) {
        highway = new Highway(2);
        highway.setupHighway();
        highway.roads[0].roadThroughput = settings.getThroughput().clone();
        highway.roads[1].roadThroughput = settings.getThroughput().clone();
        highway.roads[0].road[0].optionsThroughput = settings.getThroughput();
        highway.roads[1].road[0].optionsThroughput = settings.getThroughput();
        int numberOfCars = 4000;
        int maxVelocity;
        switch (settings.getTime()) {
            case 0:
                startingNumberOfCars = (int) (0.10 * numberOfCars);
                break;
            case 1:
                startingNumberOfCars = (int) (0.30 * numberOfCars);
                break;
            case 2:
                startingNumberOfCars = (int) (0.6 * numberOfCars);
                break;
        }

        for (int i = 0; i < startingNumberOfCars; ++i) {
            int randomRoads = rand.nextInt(2);
            int randomRoad = rand.nextInt(2);
            int randomLane = rand.nextInt(8353);

            //max velocity definition
            //tirs to cars are about 1:5
            int randomNumber = rand.nextInt(6);
            if (randomNumber == 0) {
                maxVelocity = settings.getCarMaxVelocity();
            } else {
                maxVelocity = rand.nextInt(settings.getCarMaxUpperVelocity() - settings.getCarMaxVelocity()) + settings.getCarMaxVelocity() + 1;
            }
            highway.roads[randomRoads].road[randomRoad + 1].lane.get(randomLane).vehicle = new Car(maxVelocity, rand.nextInt(maxVelocity - 2) + 2, rand.nextInt(6) + 1);
            highway.roads[randomRoads].road[randomRoad + 1].lane.get(randomLane).occupied = true;
        }
        for (int i = 0; i < highway.roads.length; ++i) {
            for (int j = 0; j < highway.roads[i].road.length; ++j) {
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
