package Model.Vehicles;

import Model.Highway.Cell;

import java.util.Random;

public class Vehicle {
    protected int velocity;
    public Cell[][] neighbourhood;
    protected int maxVelocity;
    protected int distanceToNextCarInFront = 0;

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

    public void calculateDistanceToNextFrontVehicle() {
        for (int i = (neighbourhood[1].length / 2) + 1, j = 1; i < neighbourhood[1].length; i++) {
//            System.out.println(i);
            if (!neighbourhood[1][i].occupied) {
                distanceToNextCarInFront = j;
                j++;
            } else {
                distanceToNextCarInFront = j;
                return;
            }

        }
        distanceToNextCarInFront += 1;
    }

    public void calculateNextVelocity() {
        double probability = new Random().nextDouble();
        calculateDistanceToNextFrontVehicle();
        System.out.println("---" + distanceToNextCarInFront + "---" + probability);
        if (distanceToNextCarInFront <= velocity || (probability < 0.1 && velocity == 5)) {
            SlowDown();
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
