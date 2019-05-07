package Model.Vehicles;

import Model.Highway.Cell;

import java.util.Random;


public class Vehicle {
    protected int velocity;
    public Cell[][] neighbourhood;
    protected int maxVelocity;
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

    public void calculateDistanceToNextFrontVehicle() {
        int neighbourhoodToCheck=1;
        switch (laneToChange) {
            case LEFT:
                neighbourhoodToCheck=0;
                break;
            case RIGHT:
                neighbourhoodToCheck=2;
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

    public void decideAboutLaneChange(LaneToChange directionToChange) {
        calculateDistanceToNextFrontVehicle();
        if (distanceToNextCarInFront > velocity) {
            laneToChange = LaneToChange.NONE;
            return;
        }
        Vehicle vehicleBehind = null;
        int whereToChange = 0;
        switch (directionToChange) {
            case LEFT:
                whereToChange = 0;
                break;
            case RIGHT:
                whereToChange = 2;
                break;
        }
        if (neighbourhood[whereToChange] != null) {
            for (int i = (neighbourhood[whereToChange].length / 2) , j = 1; i >= 0; i--) {
                if (!neighbourhood[whereToChange][i].occupied) {
                    distanceToNextCarInBack = j;
                    j++;
                } else {
                    vehicleBehind = neighbourhood[0][i].vehicle;
                    distanceToNextCarInBack = j;
                    break;
                }
            }

            distanceToNextCarInBack += 1;
            if (vehicleBehind != null) {
                double probability = new Random().nextDouble();
                if (vehicleBehind.getVelocity() < distanceToNextCarInBack && probability <= 0.2) {
                    laneToChange = directionToChange;
                    return;
                } else if (vehicleBehind.getVelocity() >= distanceToNextCarInBack && probability <= 0.9) {
                    laneToChange = directionToChange;
                    return;
                }

            }
        }
        laneToChange = LaneToChange.NONE;
    }

    public void calculateNextVelocity() {
        double probability = new Random().nextDouble();
        calculateDistanceToNextFrontVehicle();
//        System.out.println("---" + distanceToNextCarInFront + "---" + probability);
        if (distanceToNextCarInFront <= velocity || (probability < 0.1 && velocity == 5)) {
            if (neighbourhood[0][5] != null) {
                decideAboutLaneChange(LaneToChange.LEFT);
            }
            if (laneToChange == LaneToChange.NONE) {
                SlowDown();
            }


        } else if (probability >= 0.7 || velocity <= 3) {
            SpeedUp();
        }
    }

    private void SpeedUp() {
        velocity = Math.min(velocity + 1, maxVelocity);
    }

    private void SlowDown() {
        velocity = Math.max(Math.min(velocity - 1, distanceToNextCarInFront - 1), 0);
    }


}
