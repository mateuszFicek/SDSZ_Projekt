package Model.Highway;

//Autostrada - sklada się z dwoch jezdni

public class Highway {
    public Road[] highway;

    public Highway(int highwayWidth) {
        highway = new Road[highwayWidth];
        for(int i = 0; i < highwayWidth; i++) highway[i] = new Road(3);
        }
}