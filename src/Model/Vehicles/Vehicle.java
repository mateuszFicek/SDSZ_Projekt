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

    public  LaneToChange laneToChange = LaneToChange.NONE;

    public Vehicle() {
        neighbourhood = new Cell[3][11];
        for (int i = 0; i < neighbourhood.length; ++i) {
            for (int j = 0; j < neighbourhood[i].length; ++j) {
                neighbourhood[i][j] = new Cell();
            }
        }

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
        int neighbourhoodToCheck=roadIndex;
        switch (laneToChange) {
            case LEFT:
                if (roadIndex==2)
                    return;
                else
                    neighbourhoodToCheck=roadIndex+1;
                break;
            case RIGHT:
                if(roadIndex == 0)
                    return;
                else
                    neighbourhoodToCheck=roadIndex-1;
                break;
        }

        for (int i = (neighbourhood[neighbourhoodToCheck].length / 2) + 1, j = 1; i < neighbourhood[neighbourhoodToCheck].length; i++) {
//            System.out.println(i);
            if (!neighbourhood[neighbourhoodToCheck][i].occupied) {
                distanceToNextCarInFront = j;
                j++;
            } else {
                distanceToNextCarInFront = j;
                return;
            }

        }
        distanceToNextCarInFront += 1;
    }

    public void decideAboutLaneChange(LaneToChange directionToChange, int roadIndex) {
        calculateDistanceToNextFrontVehicle(roadIndex);
        if(distanceToNextCarInFront >velocity){
            laneToChange = LaneToChange.NONE;
            return;
        }
        Vehicle vehicleBehind = null;
        int whereToChange =0;
        switch (directionToChange) {
            case LEFT:
                whereToChange=0;
                break;
            case RIGHT:
                whereToChange=2;
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

            if(distanceToNextCarInFront<velocity) {
                if (vehicleBehind != null) {
                    if (vehicleBehind.getVelocity() < distanceToNextCarInBack && probability <= 0.2) {
                        laneToChange = directionToChange;
                        return;
                    } else if (vehicleBehind.getVelocity() >= distanceToNextCarInBack && probability <= 0.9) {
                        laneToChange = directionToChange;
                        return;
                    }
                } else
                {
                    laneToChange = directionToChange;
                    return;
                }
            }

        }
        laneToChange = LaneToChange.NONE;
    }


    public void calculateNextVelocity(int roadIndex) {
        double probability = new Random().nextDouble();
        calculateDistanceToNextFrontVehicle(roadIndex);
//        System.out.println("---" + distanceToNextCarInFront + "---" + probability);
        if (distanceToNextCarInFront <= velocity || (probability < 0.1 && velocity == 5)) {
            if (neighbourhood[roadIndex][maxVelocity] != null) {
                decideAboutLaneChange(LaneToChange.LEFT, roadIndex);
            }
            if (laneToChange == LaneToChange.NONE) {
                SlowDown();
            }


        } else if (probability >= 0.7 || velocity <= 3) {
            SpeedUp();
        }
    }

    public void changeLane()
    {
        if(laneToChange == LaneToChange.LEFT)
        {
            neighbourhood[0][5].occupyCell(this);
            neighbourhood[1][5].freeCell();
            laneToChange = LaneToChange.NONE;


        }
        else if(laneToChange == LaneToChange.RIGHT)
        {
            neighbourhood[1][5].freeCell();
            neighbourhood[2][5].occupyCell(this);
            laneToChange = LaneToChange.NONE;
        }
    }

    private void SpeedUp() {
        velocity = Math.min(velocity + 1, maxVelocity);
    }

    private void SlowDown() {
        velocity = Math.max(Math.min(velocity - 1, distanceToNextCarInFront - 1), 0);
    }


}
