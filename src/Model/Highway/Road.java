package Model.Highway;

//Jezdnia sklada sie z 3 pasow
//

public class Road {
    public Lane[] road;

    public Road(int roadWidth) {
        road = new Lane[roadWidth];
        for(int i = 0; i < roadWidth; i++) road[i] = new Lane();
    }
}