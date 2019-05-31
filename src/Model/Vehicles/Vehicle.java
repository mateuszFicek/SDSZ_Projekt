package Model.Vehicles;

import Model.Highway.Cell;

import java.util.Random;

public class Vehicle {
    protected int velocity;
    public Cell[][] neighbourhood;
    public int maxVelocity;
    protected int distanceToNextCarInFront = 0;
    protected int distanceToNextCarInBack = 0;
    public boolean hasChangedLane = false;
    public boolean hasEntered = false;
    public boolean disabled = false;
    public int numberOfExits;
    public int numberOfCellsToPass = 40;
    public int numberOfCellsToOvertake = 0;

    public LaneToChange laneToChange = LaneToChange.NONE;

    public Vehicle() {
        neighbourhood = new Cell[3][11];
    }

    public Vehicle(int maxVelocity) {
        neighbourhood = new Cell[3][2 * maxVelocity + 1];
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

    public int getDistanceToNextCarInFront() {
        return distanceToNextCarInFront;
    }

    public Vehicle calculateDistanceToNextFrontVehicle(int roadIndex) {
        for (int i = (neighbourhood[roadIndex].length / 2) + 1, j = 1; i < neighbourhood[roadIndex].length; i++) {
            if (!neighbourhood[roadIndex][i].occupied) {
                distanceToNextCarInFront = j;
                j++;
            } else {
                distanceToNextCarInFront = j;
                return neighbourhood[roadIndex][i].vehicle;
            }
        }
        distanceToNextCarInFront += 1;
        return null;
    }

    public Vehicle calculateDistanceToNextBackVehicle(int roadIndex) {
        for (int i = (neighbourhood[roadIndex].length / 2) - 1, j = 1; i >= 0; i--) {
            if (!neighbourhood[roadIndex][i].occupied) {
                distanceToNextCarInBack = j;
                j++;
            } else {
                distanceToNextCarInBack = j;
                return neighbourhood[roadIndex][i].vehicle;
            }
        }
        distanceToNextCarInBack += 1;
        return null;
    }

    public void decideAboutChangeLaneToRight(int roadIndex) {
        if (roadIndex == 2) {
            laneToChange = LaneToChange.NONE;
            return;
        }
        Vehicle vehicleInBackOnRight = calculateDistanceToNextBackVehicle(roadIndex + 1);
        if (vehicleInBackOnRight != null) {
            if (velocity > vehicleInBackOnRight.velocity) {
                laneToChange = LaneToChange.RIGHT;
                return;
            } else {
                laneToChange = LaneToChange.NONE;
                return;
            }
        } else {
            laneToChange = LaneToChange.RIGHT;
        }
    }


    public void decideAboutChangeLaneToLeft(int roadIndex) {
        if (roadIndex == 0) {
            laneToChange = LaneToChange.NONE;
            return;
        }
        Vehicle vehicleInBackOnLeft = calculateDistanceToNextBackVehicle(roadIndex - 1);
        Vehicle vehicleInFront = calculateDistanceToNextFrontVehicle(roadIndex);
        if (vehicleInFront == null) {
            laneToChange = LaneToChange.NONE;
            return;
        } else {
            if (vehicleInBackOnLeft != null) {
                if (vehicleInFront.velocity < velocity && vehicleInBackOnLeft.velocity < velocity) {
                    laneToChange = LaneToChange.RIGHT;
                    return;
                }
            } else if (vehicleInFront.velocity < velocity) {
                laneToChange = LaneToChange.LEFT;
                return;
            }
        }
    }


    public void checkExits(int laneIndex) {
        if (numberOfExits > 0 && neighbourhood[2][maxVelocity].cellType == Cell.CellType.EXIT) {
            if (numberOfCellsToPass == 40) {
                numberOfExits--;
                numberOfCellsToPass -= velocity;
            } else {
                numberOfCellsToPass -= velocity;
            }
        } else if (numberOfExits == 0 && neighbourhood[2][maxVelocity].cellType == Cell.CellType.EXIT) {
            if (laneIndex == 2)
                laneToChange = LaneToChange.NONE;
            else
                laneToChange = LaneToChange.RIGHT;
            decideAboutChangeLaneToRight(laneIndex);
        } else {
            numberOfCellsToPass = 40;
        }
    }

    public void enterHighway()
    {
        int cellsOfEnterWayLeft;
        if(laneToChange == LaneToChange.NONE)
        {
            cellsOfEnterWayLeft=0;
            for(int i = (neighbourhood[2].length / 2) + 1; i<neighbourhood[2].length; i++)
            {
                if(neighbourhood[2][i].cellType == Cell.CellType.DISABLED)
                {
                    break;
                }
                cellsOfEnterWayLeft++;
            }
            if(cellsOfEnterWayLeft< velocity)
            {
                AvoidCollision(cellsOfEnterWayLeft);
                disabled = true;
            }

        }
        if(laneToChange == LaneToChange.LEFT)
        {
            disabled = false;
        }
    }

    public void decideAboutEnter()
    {
        Vehicle vehicleInTheBack= calculateDistanceToNextBackVehicle(1);
        if(vehicleInTheBack == null || vehicleInTheBack.velocity < velocity)
        {
            laneToChange =LaneToChange.LEFT;
        }
        else
        {
            laneToChange = LaneToChange.NONE;
        }


    }

    public void calculateNextVelocity(int roadIndex) {
        double probability = new Random().nextDouble();
        if (hasChangedLane) {
            calculateDistanceToNextFrontVehicle(roadIndex);
        }
        hasChangedLane = false;
        if (distanceToNextCarInFront <= velocity || (probability > 0.9 && velocity == maxVelocity)) {
            SlowDown();
        } else if ((probability < 0.3 || velocity <= maxVelocity - 2) && velocity+1 != distanceToNextCarInFront) {
            SpeedUp();
        }
    }

    public int changeLane(int laneIndex) {
        if (laneToChange == LaneToChange.LEFT) {
            neighbourhood[laneIndex - 1][maxVelocity].occupyCell(this);
            neighbourhood[laneIndex][maxVelocity].freeCell();
            laneToChange = LaneToChange.NONE;
            hasChangedLane = true;
            if(hasEntered) {
                hasEntered = false;
                disabled = false;
            }
            numberOfCellsToOvertake = 40;
            return laneIndex - 1;
        } else if (laneToChange == LaneToChange.RIGHT) {
            neighbourhood[laneIndex][maxVelocity].freeCell();
            neighbourhood[laneIndex + 1][maxVelocity].occupyCell(this);
            laneToChange = LaneToChange.NONE;
            hasChangedLane = true;
            return laneIndex + 1;
        }

        return laneIndex;
    }

    private void SpeedUp() {
        velocity = Math.min(velocity + 1, maxVelocity);
    }

    private void SlowDown() {
        velocity = Math.max(Math.min(velocity - 1, distanceToNextCarInFront - 1), 0);
    }

    private void AvoidCollision(int distanceToCollision)
    {
        velocity = Math.max(Math.min(velocity-1, distanceToCollision-1), 0);
    }
}
