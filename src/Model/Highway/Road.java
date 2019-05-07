package Model.Highway;

//Jezdnia sklada sie z 3 pasow
//

import Model.Vehicles.LaneToChange;
import Model.Vehicles.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class Road {
    public Lane[] road;
    int numberOfCarsOnLane;
    final int cellNumber = 8353;
    int laneNumber;

    public Road(int roadWidth) {
        road = new Lane[roadWidth];
        for (int i = 0; i < roadWidth; i++) road[i] = new Lane();
    }

    public void generateNextFrame() {
        for (int index = 0; index < road.length; index++) {
            calculateNextFrame(index);
            //moveCarsLanes(index);
            //road[index].calculateNextFrame();
            moveVehiclesForward(index);
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

    public void moveCarsLanes(int LaneIndex){
        for (Map.Entry<Integer, Cell> entry : road[LaneIndex].cellsToMoveRight.entrySet())
        {
            Vehicle currentCellVehicle = entry.getValue().vehicle;
            entry.getValue().freeCell();
            road[LaneIndex+1].lane[entry.getKey()].occupyCell(currentCellVehicle);
        }
        for (Map.Entry<Integer, Cell> entry : road[LaneIndex].cellsToMoveLeft.entrySet())
        {
            Vehicle currentCellVehicle = entry.getValue().vehicle;
            entry.getValue().freeCell();
            road[LaneIndex-1].lane[entry.getKey()].occupyCell(currentCellVehicle);
        }
    }

    public void calculateNextFrame(int index)
    {
        //cellsToMoveLeft = new HashMap<>();
        //cellsToMoveRight = new HashMap<>();
        for (int i=0; i<road[index].lane.length; i++) {
            if(road[index].lane[i].occupied)
            {
                if(road[index].lane[i].vehicle.laneToChange == LaneToChange.LEFT)
                {
                    Vehicle currentCellVehicle = road[index].lane[i].vehicle;
                    road[index].lane[i].freeCell();
                    road[index-1].lane[i].occupyCell(currentCellVehicle);
                    //cellsToMoveLeft.put(i,lane[i]);

                }
                else if(road[index].lane[i].vehicle.laneToChange == LaneToChange.RIGHT)
                {
                    Vehicle currentCellVehicle = road[index].lane[i].vehicle;
                    road[index].lane[i].freeCell();
                    road[index+1].lane[i].occupyCell(currentCellVehicle);
                }
                road[index].lane[i].vehicle.calculateNextVelocity();
            }
        }
    }

    public void moveVehiclesForward(int index)
    {
        numberOfCarsOnLane = 0;
        int segment;
        Cell[] nextFrameLane = new Cell[cellNumber];
        for(int i = 0; i < cellNumber; i++) nextFrameLane[i] = new Cell();

        for(int i=0; i<road[index].lane.length; i++)
        {
            if(road[index].lane[i].occupied)
            {
                numberOfCarsOnLane +=1;
                segment = Highway.segmentsByCell.get(i);
                Highway.carsOnSegment.set(segment, (Highway.carsOnSegment.get(segment)+1));
                Vehicle currentCellVehicle = road[index].lane[i].vehicle;
                System.out.println("Linia:" + i + " Prędkość:" + currentCellVehicle.getVelocity() + " Numer pasa: "+laneNumber);
                if(currentCellVehicle.getVelocity() + i >= road[index].lane.length)
                {
                    nextFrameLane[(currentCellVehicle.getVelocity() +i)- road[index].lane.length].occupyCell(currentCellVehicle);
                }
                else{
                    nextFrameLane[i+currentCellVehicle.getVelocity()].occupyCell(currentCellVehicle);
                }
            }
        }
        road[index].lane = nextFrameLane;
        System.out.println(numberOfCarsOnLane);
    }

}