package Model.Highway;

//Jezdnia sklada sie z 3 pasow

class Road {
    protected Lane[] road;

    public Road(int roadWidth) {
        road = new Lane[roadWidth];
    }
}