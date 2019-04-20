package Model.Highway;

//Autostrada - sklada się z dwoch jezdni

class Highway {
    protected Road[] highway;

    public Highway(int highwayWidth) {
        highway = new Road[highwayWidth];
    }
}