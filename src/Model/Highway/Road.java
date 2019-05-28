package Model.Highway;

//Jezdnia sklada sie z 3 pasow

import Model.Vehicles.Vehicle;

import java.util.Random;

public class Road {
    public Lane[] road;
    public int[] roadThroughput;
    private Random probability = new Random();

    public Road(int roadWidth) {
        road = new Lane[roadWidth];
        for (int i = 0; i < roadWidth; i++) road[i] = new Lane();
    }

    public void generateNextFrame() {
        int suma = 0;

        for (int index = 0; index < road.length; index++) {
            road[index].calculateNextFrame(index);
        }

        for (int index = 0; index < road.length; index++) {
            int l = road[index].moveVehiclesForward();
            suma += l;
        }

        if(probability.nextDouble() < 0.3) {
            road[0].enterCars(roadThroughput);
        }

        for (int index = 0; index < road.length; index++) {
            moveCarsNeighbourhoods(index);
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
