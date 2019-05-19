package Model.Vehicles;

public class Car extends Vehicle {
    public Car() {
        super();
        maxVelocity = 5;
        velocity = 2;
        numberOfExits = 2;
    }

    public Car(int maxVelocity, int velocity, int numberOfExits){
        super(maxVelocity);
        this.maxVelocity = maxVelocity;
        this.velocity = velocity;
        this.numberOfExits = numberOfExits;
    }

    public Car(int maxVelocity){
        super(maxVelocity);
        this.maxVelocity = maxVelocity;
        this.velocity = 1;
        this.numberOfExits = 2;
    }
    public int getMaxVelocity(){
        return maxVelocity;
    }
}
