package Model.Highway;

//Jezdnia sklada sie z 3 pasow

public class Road {
    public Lane[] road;

    public Road(int roadWidth) {
        road = new Lane[roadWidth];
        for(int i = 0; i < roadWidth; i++) road[i] = new Lane();

        road[0].setupEntryOneWay(); // ustawienie wjazdow w jednym kierunku
        road[0].setupExitOneWay(); // ustawienie zjazdow w jednym kierunku

        road[1].setupExitOtherWay(); // ustawienie WJAZDOW w drugim kierunku
        road[1].setupEntryOtherWay(); // ustawienie ZJAZDOW w drugim kierunku

    }
}