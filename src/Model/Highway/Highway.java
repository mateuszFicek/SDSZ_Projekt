package Model.Highway;

//Autostrada - sklada się z dwoch jezdni

public class Highway {
    public Road[] roads;

    public Highway(int highwayWidth) {
        roads = new Road[highwayWidth];
        for(int i = 0; i < highwayWidth; i++) roads[i] = new Road(3);
        }

        public void setupHighway(){
        //Ustawianie wjazdow i zjazdow
            roads[0].road[0].setupEntryOneWay();
            roads[0].road[0].setupExitOneWay();
            roads[1].road[0].setupEntryOtherWay();
            roads[1].road[0].setupExitOtherWay();
        //Ustawianie wylaczonych z ruchu kratek pomiedzy zjazdami
            roads[0].road[0].setupDisabled();
            roads[1].road[0].setupDisabled();
        //Ustawianie normalnych kratek na wewnetrznych pasach
            roads[0].road[1].setupNormal();
            roads[0].road[2].setupNormal();
            roads[1].road[1].setupNormal();
            roads[1].road[2].setupNormal();
        }
}