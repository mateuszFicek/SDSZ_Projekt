package Model.Vehicles;

public class Car extends Vehicle {

    public Car(int maxVelocity, int velocity, int numberOfExits) {
        super(maxVelocity);
        this.maxVelocity = maxVelocity;
        this.velocity = velocity;
        this.numberOfExits = numberOfExits;
    }

}
