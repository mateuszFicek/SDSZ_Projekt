package Model.Vehicles;
import Model.Highway.Cell;

public class Vehicle {
    protected double Velocity;
    protected double Fraction;
    protected Cell[][] Neighbourhood = new Cell[3][5];
    protected double MaxVelocity;
    protected double DistanceToNextCarInFront;

    public void decreaseVelocity(double VelocityChange)
    {
        Velocity-=VelocityChange;
    }

    public void increaseVelocity(double VelocityChange)
    {
        Velocity+=VelocityChange;
    }

    public void calculateDistanceToNextFrontVehicle()
    {
        if(!Neighbourhood[1][3].getOccupied())
        {
            DistanceToNextCarInFront = Cell.Measure;
        }
        else if(Neighbourhood[1][4].getOccupied())
        {
            DistanceToNextCarInFront = Cell.Measure*2;
        }
    }



}
