package Model.Vehicles;

public class Vehicle {
    protected double Velocity;
    protected double Fraction;

    public void decreaseVelocity(double VelocityChange)
    {
        Velocity-=VelocityChange;
    }

    public void increaseVelocity(double VelocityChange)
    {
        Velocity+=VelocityChange;
    }

    public void calculateDistanceToNextCar()
    {

    }


}
