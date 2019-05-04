package Model.Vehicles;
import Model.Highway.Cell;

public class Vehicle {
    protected int Velocity;
    public Cell[][] Neighbourhood = new Cell[3][11];
    protected int MaxVelocity;
    protected int DistanceToNextCarInFront=0;

    public void decreaseVelocity(int VelocityChange)
    {
        Velocity-=VelocityChange;
    }

    public void increaseVelocity(int VelocityChange)
    {
        Velocity+=VelocityChange;
    }

    public int getVelocity() {return Velocity;}

    public void calculateDistanceToNextFrontVehicle()
    {
        for(int i = (Neighbourhood[1].length/2)+1, j= 1; i<Neighbourhood[1].length;i++)
        {
            if(!Neighbourhood[1][i].occupied)
            {
                DistanceToNextCarInFront = j;
                j++;
            }
            else{
                break;
            }
        }
    }

    public void calculateNextVelocity()
    {
        double probability = Math.random();
        calculateDistanceToNextFrontVehicle();
        if(DistanceToNextCarInFront<= 1 || probability < 0.1){
            SlowDown();
        } else{
            SpeedUp();
        }
    }

    private void SpeedUp()
    {
            Velocity = Math.min(Velocity+1, MaxVelocity);
    }

    private void SlowDown()
    {
            Velocity = Math.min(Velocity, DistanceToNextCarInFront-1);
    }


}
