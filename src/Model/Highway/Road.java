package Model.Highway;

//Jezdnia sklada sie z 3 pasow
//

import Model.Vehicles.Vehicle;

public class Road {
    public Lane[] road;

    public Road(int roadWidth) {
        road = new Lane[roadWidth];
        for (int i = 0; i < roadWidth; i++) road[i] = new Lane();
    }

    public void generateNextFrame() {
        for (int index = 0; index < road.length; index++) {
            road[index].calculateNextFrame();
            road[index].moveVehiclesForward();
            moveCarsNeighbourhoods(index);
        }
    }

    public void moveCarsNeighbourhoods(int laneIndex) {
        int laneLength = road[laneIndex].lane.length;
        for (int i = 0; i < laneLength; i++) {
            if (road[laneIndex].lane[i].occupied) {
                Vehicle currentCellVehicle = road[laneIndex].lane[i].vehicle;
                if (laneIndex - 1 >= 0) {
                    currentCellVehicle.neighbourhood[0][5] = road[laneIndex - 1].lane[i];
                    for (int j = 1; j < 5; j++) {
                        if (i - j >= 0) {
                            currentCellVehicle.neighbourhood[0][5 - j] = road[laneIndex - 1].lane[i - j];
                        } else {
                            currentCellVehicle.neighbourhood[0][5 - j] = road[laneIndex - 1].lane[laneLength - j];
                        }
                        if (i + j < laneLength) {
                            currentCellVehicle.neighbourhood[0][5 + j] = road[laneIndex - 1].lane[i + 1];
                        } else {
                            currentCellVehicle.neighbourhood[0][5 + j] = road[laneIndex - 1].lane[(i + j) - laneLength];
                        }
                    }
                }
                if (laneIndex + 1 < road.length) {
                    currentCellVehicle.neighbourhood[2][5] = road[laneIndex + 1].lane[i];
                    for (int j = 1; j < 5; j++) {
                        if (i - j >= 0) {
                            currentCellVehicle.neighbourhood[2][5 - j] = road[laneIndex + 1].lane[i - j];
                        } else {
                            currentCellVehicle.neighbourhood[2][5 - j] = road[laneIndex + 1].lane[laneLength - j];
                        }
                        if (i + j < laneLength) {
                            currentCellVehicle.neighbourhood[2][5 + j] = road[laneIndex + 1].lane[i + 1];
                        } else {
                            currentCellVehicle.neighbourhood[2][5 + j] = road[laneIndex + 1].lane[(i + j) - laneLength];
                        }
                    }
                }

                currentCellVehicle.neighbourhood[1][5] = road[laneIndex].lane[i];
                for (int j = 1; j < 5; j++) {
                    if (i - j >= 0) {
                        currentCellVehicle.neighbourhood[1][5 - j] = road[laneIndex].lane[i - j];
                    } else {
                        currentCellVehicle.neighbourhood[1][5 - j] = road[laneIndex].lane[laneLength - j];
                    }
                    if (i + j < laneLength) {
                        currentCellVehicle.neighbourhood[1][5 + j] = road[laneIndex].lane[i + j];
                    } else {
                        currentCellVehicle.neighbourhood[1][5 + j] = road[laneIndex].lane[(i + j) - laneLength];
                    }
                }
                currentCellVehicle.calculateDistanceToNextFrontVehicle();
            }
        }
    }


}