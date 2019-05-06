package Model.Vehicles;

public class Car extends Vehicle {
    public Car() {
        super();
        maxVelocity = 5;
        velocity = 2;
    }

    public Car(int maxVelocity, int velocity){
        super();
        this.maxVelocity = maxVelocity;
        this.velocity = velocity;
    }

    public Car(int maxVelocity){
        super();
        this.maxVelocity = maxVelocity;
        this.velocity = 1;
    }
}
