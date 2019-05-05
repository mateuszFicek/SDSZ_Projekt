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

    public void GenerateNextFrame() {
        for (int index = 0; index < road.length; index++) {
            road[index].CalculateNextFrame();
            road[index].MoveVehiclesForward();
            MoveCarsNeighbourhoods(index);
        }
    }

    public void MoveCarsNeighbourhoods(int laneIndex) {
        int LaneLength = road[laneIndex].lane.length;
        for (int i = 0; i < LaneLength; i++) {
            if (road[laneIndex].lane[i].occupied) {
                Vehicle currentCellVehicle = road[laneIndex].lane[i].vehicle;
                if (laneIndex - 1 >= 0) {
                    currentCellVehicle.neighbourhood[0][5] = road[laneIndex - 1].lane[i];
                    for (int j = 1; j < 5; j++) {
                        if (i - j >= 0) {
                            currentCellVehicle.neighbourhood[0][5 - j] = road[laneIndex - 1].lane[i - j];
                        } else {
                            currentCellVehicle.neighbourhood[0][5 - j] = road[laneIndex - 1].lane[LaneLength - j];
                        }
                        if (i + j < LaneLength) {
                            currentCellVehicle.neighbourhood[0][5 + j] = road[laneIndex - 1].lane[i + 1];
                        } else {
                            currentCellVehicle.neighbourhood[0][5 + j] = road[laneIndex - 1].lane[(i + j) - LaneLength];
                        }
                    }
                }
                if (laneIndex + 1 < road.length) {
                    currentCellVehicle.neighbourhood[2][5] = road[laneIndex + 1].lane[i];
                    for (int j = 1; j < 5; j++) {
                        if (i - j >= 0) {
                            currentCellVehicle.neighbourhood[2][5 - j] = road[laneIndex + 1].lane[i - j];
                        } else {
                            currentCellVehicle.neighbourhood[2][5 - j] = road[laneIndex + 1].lane[LaneLength - j];
                        }
                        if (i + j < LaneLength) {
                            currentCellVehicle.neighbourhood[2][5 + j] = road[laneIndex + 1].lane[i + 1];
                        } else {
                            currentCellVehicle.neighbourhood[2][5 + j] = road[laneIndex + 1].lane[(i + j) - LaneLength];
                        }
                    }
                }

                currentCellVehicle.neighbourhood[1][5] = road[laneIndex].lane[i];
                for (int j = 1; j < 5; j++) {
                    if (i - j >= 0) {
                        currentCellVehicle.neighbourhood[1][5 - j] = road[laneIndex].lane[i - j];
                    } else {
                        currentCellVehicle.neighbourhood[1][5 - j] = road[laneIndex].lane[LaneLength - j];
                    }
                    if (i + j < LaneLength) {
                        currentCellVehicle.neighbourhood[1][5 + j] = road[laneIndex].lane[i + 1];
                    } else {
                        currentCellVehicle.neighbourhood[1][5 + j] = road[laneIndex].lane[(i + j) - LaneLength];
                    }
                }
                currentCellVehicle.calculateDistanceToNextFrontVehicle();
            }
        }
    }


}