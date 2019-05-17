package Model.Vehicles;

import Model.Highway.Cell;
import Model.Highway.CircularArrayList;

import java.util.Random;


public class Vehicle {
    protected int velocity;
    public Cell[][] neighbourhood;
    public int maxVelocity;
    protected int distanceToNextCarInFront = 0;
    protected int distanceToNextCarInBack = 0;
    public  boolean hasChangedLane = false;
    public int numberOfExits;
    public int numberOfCellsToPass = 40;

    public  LaneToChange laneToChange = LaneToChange.NONE;

    public Vehicle() {
        neighbourhood = new Cell[3][11];
//        for (int i = 0; i < neighbourhood.length; ++i) {
//            for (int j = 0; j < neighbourhood[i].length; ++j) {
//                neighbourhood[i][j] = new Cell();
//            }
//        }

    }

    public void decreaseVelocity(int velocityChange) {
        velocity -= velocityChange;
    }

    public void increaseVelocity(int velocityChange) {
        velocity += velocityChange;
    }

    public int getVelocity() {
        return velocity;
    }
    public int getDistanceToNextCarInFront() {return distanceToNextCarInFront;}

    public void calculateDistanceToNextFrontVehicle(int roadIndex) {
        for (int i = (neighbourhood[roadIndex].length / 2) + 1, j = 1; i < neighbourhood[roadIndex].length; i++) {
//            System.out.println(i);
            if (!neighbourhood[roadIndex][i].occupied) {
                distanceToNextCarInFront = j;
                j++;
            } else {
                distanceToNextCarInFront = j;
                return;
            }
           // distanceToNextCarInFront-=1;
        }
        distanceToNextCarInFront += 1;
    }

    public void decideAboutLaneChange(LaneToChange directionToChange, int roadIndex) {
        calculateDistanceToNextFrontVehicle(roadIndex);
        if(roadIndex == 2  && directionToChange == LaneToChange.LEFT) {
            laneToChange = LaneToChange.NONE;
            return;
        } else if(roadIndex == 0 && directionToChange == LaneToChange.RIGHT)
        {
            laneToChange = LaneToChange.NONE;
            return;
        }
        if(distanceToNextCarInFront >=velocity){
                    laneToChange = LaneToChange.NONE;
                    return;
                }
        Vehicle vehicleBehind = null;
        int whereToChange =0;
        switch (directionToChange) {
            case LEFT:
                whereToChange=roadIndex+1;
                break;
                case RIGHT:
                    whereToChange=roadIndex-1;
                break;
    }
        if(neighbourhood[whereToChange] != null) {
        for (int i = (neighbourhood[whereToChange].length / 2) + 1, j = 1; i>=0; i--) {
            if (!neighbourhood[whereToChange][i].occupied) {
                distanceToNextCarInBack = j;
                j++;
            } else {
                vehicleBehind = neighbourhood[0][i].vehicle;
                distanceToNextCarInBack = j;
                break;
            }
        }

        double probability = new Random().nextDouble();

            if (vehicleBehind != null) {
                if (vehicleBehind.getVelocity() < distanceToNextCarInBack && probability <= 0.9) {
                    laneToChange = directionToChange;
                    return;
                } else if (vehicleBehind.getVelocity() >= distanceToNextCarInBack && probability <= 0.2) {
                    laneToChange = directionToChange;
                    return;
                }
            }
            else
            {
                laneToChange = directionToChange;
                return;
            }

    }
    laneToChange = LaneToChange.NONE;
}

    public void checkExits(int index, int laneIndex){
        if (numberOfExits > 0 && neighbourhood[0][maxVelocity].cellType == Cell.CellType.EXIT) {
            if (numberOfCellsToPass == 40) {
                numberOfExits--;
                System.out.println(index + " ------- Jeszcze nie gotowy do zjazdu, ale powinno być 40: " + numberOfCellsToPass + ". Ilosc zjazdow: " + numberOfExits );
                numberOfCellsToPass -= velocity;
            }
            else {
                numberOfCellsToPass -= velocity;
                System.out.println(index + " ------- Jeszcze nie gotowy do zjazdu, ale powinno być mniej: " + numberOfCellsToPass + ". Ilosc zjazdow: " + numberOfExits );
            }
        }
        else if(numberOfExits == 0 && neighbourhood[0][maxVelocity].cellType == Cell.CellType.EXIT ){
            System.out.println(index + " ------- Gotowy do zjazdu: " + numberOfCellsToPass + ". Ilosc zjazdow: " + numberOfExits );
            //neighbourhood[0][maxVelocity].occupyCell(this);
            //neighbourhood[laneIndex][maxVelocity].freeCell();
            if(laneIndex == 0)
                laneToChange = LaneToChange.NONE;
            else
                laneToChange = LaneToChange.RIGHT;
            //decideAboutLaneChange(laneToChange,laneIndex);
            changeLane(laneIndex);
        }
        else {
            numberOfCellsToPass = 40;
        }
    }

    public void calculateNextVelocity(int roadIndex) {
        double probability = new Random().nextDouble();
        if(hasChangedLane) {
            calculateDistanceToNextFrontVehicle(roadIndex);
        }
        hasChangedLane = false;
//        System.out.println("---" + distanceToNextCarInFront + "---" + probability);
        if (distanceToNextCarInFront <= velocity || (probability > 0.6 && velocity == 5)) {
                SlowDown();
        } else {
            SpeedUp();
        }
    }

    public int changeLane(int laneIndex)
    {
        if(laneToChange == LaneToChange.LEFT)
        {
            neighbourhood[laneIndex+1][5].occupyCell(this);
            neighbourhood[laneIndex][5].freeCell();
            laneToChange = LaneToChange.NONE;
            hasChangedLane = true;
            return laneIndex+1;
        }
        else if(laneToChange == LaneToChange.RIGHT)
        {
            neighbourhood[laneIndex][5].freeCell();
            neighbourhood[laneIndex-1][5].occupyCell(this);
            laneToChange = LaneToChange.NONE;
            hasChangedLane = true;
            return laneIndex-1;
        }

        return laneIndex;
    }

    private void SpeedUp() {
        velocity = Math.min(velocity + 1, maxVelocity);
    }

    private void SlowDown() {
        velocity = Math.max(Math.min(velocity - 1, distanceToNextCarInFront - 1), 0);
    }


}
