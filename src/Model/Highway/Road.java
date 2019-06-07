package Model.Highway;

//Jezdnia sklada sie z 3 pasow

import Model.Vehicles.Vehicle;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Road {
    public Lane[] road;
    public int[][] carsPerMinute = new int[17][60];
    private Random probability = new Random();
    int iterCounter = 0;

    Road(int roadWidth) {
        road = new Lane[roadWidth];
        for (int i = 0; i < roadWidth; i++) road[i] = new Lane();
        for (int i = 0; i<17;i++){
            for(int j = 0;j<60;j++){
                carsPerMinute[i][j] = 0;
            }
        }
    }

    public void generateNextFrame() {
        int suma = 0;

        for (int index = 0; index < road.length; index++) {
            road[index].calculateNextFrame(index);
        }

        for (int index = 0; index < road.length; index++) {
            int l = road[index].moveVehiclesForward();
            suma += l;

            if(index<2) {
                for (int j = 0; j < 17; j++) {
                    carsPerMinute[j][0] += road[index].carsPerIteration[j];
                    if (iterCounter == 10) {
                        road[index].carsPerIteration[j] = 0;
                        System.out.println("Flow of cars on segment 0: " + carsPerMinute[0][0]);
                        iterCounter = 0;
                        //Arrays.fill(carsPerMinute, 0);
                        for (int i = 59; i >= 0; i--) {
                            if (i == 0) {
                                carsPerMinute[j][0] = 0;
                                System.out.println("s: " + i + "cars: " + carsPerMinute[j][i]);
                            } else {
                                carsPerMinute[j][i] = carsPerMinute[j][i - 1];
                                System.out.println("s: " + i + "cars: " + carsPerMinute[j][i]);
                            }
                        }
                    }
                }
            }
        }


        road[2].enterCars();

        for (int index = 0; index < road.length; index++) {
            moveCarsNeighbourhoods(index);
            //Arrays.fill(road[index].carsPerIteration,0);
        }
        iterCounter++;
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
