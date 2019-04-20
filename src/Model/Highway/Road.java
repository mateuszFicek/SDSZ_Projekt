package Model.Highway;

//Jezdnia sklada sie z 3 pasow

class Road {
    protected Lane[] road;

    public Road(int roadWidth) {
        road = new Lane[roadWidth];
        road[0].setupEntry(); // ustawienie wjazdow w jednym kierunku
        road[0].setupExit(); // ustawienie zjazdow w jednym kierunku

        road[1].setupExit(); // ustawienie WJAZDOW w drugim kierunku
        road[1].setupEntry(); // ustawienie ZJAZDOW w drugim kierunku

    }

}