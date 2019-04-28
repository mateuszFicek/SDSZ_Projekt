package Model.Highway;

//Autostrada - sklada się z dwoch jezdni

public class Highway {
    public Road[] highway;

    public Highway(int highwayWidth) {
        highway = new Road[highwayWidth];
        for(int i = 0; i < highwayWidth; i++) highway[i] = new Road(3);
        }

        public void setupHighway(){
        //Ustawianie wjazdow i zjazdow
            highway[0].road[0].setupEntryOneWay();
            highway[0].road[0].setupExitOneWay();
            highway[1].road[0].setupEntryOtherWay();
            highway[1].road[0].setupExitOtherWay();
        //Ustawianie wylaczonych z ruchu kratek pomiedzy zjazdami
            highway[0].road[0].setupDisabled();
            highway[1].road[0].setupDisabled();
        //Ustawianie normalnych kratek na wewnetrznych pasach
            highway[0].road[1].setupNormal();
            highway[0].road[2].setupNormal();
            highway[1].road[1].setupNormal();
            highway[1].road[2].setupNormal();
        }
}