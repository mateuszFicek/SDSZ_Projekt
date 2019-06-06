package Model.Highway;

//Jezdnia sklada sie z 3 pasow

import Model.Vehicles.Vehicle;

import java.util.Arrays;
import java.util.Random;

public class Road {
    public Lane[] road;
    public int[] carsPerIteration =new int[17];
    private Random probability = new Random();

    Road(int roadWidth) {
        road = new Lane[roadWidth];
        for (int i = 0; i < roadWidth; i++) road[i] = new Lane();
    }

    public void generateNextFrame() {
        Arrays.fill(carsPerIteration,0);
        int suma = 0;

        for (int index = 0; index < road.length; index++) {
            road[index].calculateNextFrame(index);
        }

        for (int index = 0; index < road.length; index++) {
            int l = road[index].moveVehiclesForward();
            suma += l;

            if(index<2)
                for(int j=0; j< carsPerIteration.length; j++)
                {
                    carsPerIteration[j]+= road[index].carsPerIteration[j];
                }
        }


        road[2].enterCars();
        System.out.println("Flow of cars on segment 0: " + carsPerIteration[0]);

        for (int index = 0; index < road.length; index++) {
            moveCarsNeighbourhoods(index);
            Arrays.fill(road[index].carsPerIteration,0);
        }
    }

    public void moveCarsNeighbourhoods(int laneIndex) {
        int laneLength = road[laneIndex].lane.size();
        for (int i = 0; i < laneLength; i++) {
            if (road[laneIndex].lane.get(i).occupied) {
                Vehicle currentCellVehicle = road[laneIndex].lane.get(i).vehicle;
                for (int k = 0; k < 3; k++) {
                    for (int j = 0; j <= currentCellVehicle.maxVelocity; j++) {
                        if (i - j >= 0) {
                            currentCellVehicle.neighbourhood[k][currentCellVehicle.maxVelocity - j] = road[k].lane.get(i - j);
                        } else {
                            currentCellVehicle.neighbourhood[k][currentCellVehicle.maxVelocity - j] = road[k].lane.get(laneLength - j);
                        }
                        if (i + j < laneLength) {
                            currentCellVehicle.neighbourhood[k][currentCellVehicle.maxVelocity + j] = road[k].lane.get(i + j);
                        } else {
                            currentCellVehicle.neighbourhood[k][currentCellVehicle.maxVelocity + j] = road[k].lane.get((i + j) - laneLength);
                        }
                    }
                    currentCellVehicle.neighbourhood[laneIndex][currentCellVehicle.maxVelocity] = road[laneIndex].lane.get(i);
                }
            }
        }
    }
}
