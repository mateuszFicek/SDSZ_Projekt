package Model.Highway;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Model.Highway.Cell.CellType;
import Model.Vehicles.Vehicle;

//Pas sklada sie z 8353 kratek

public class Lane {
    public Cell[] lane;
    int exitLength = 40;
    int spaceBetweenExitAndEntry = 20;
    final int cellNumber = 8353;

    List<Integer> BaliceWjazd = IntStream.rangeClosed(0, 40).boxed().collect(Collectors.toList());
    List<Integer> Balice2Wjazd = IntStream.rangeClosed(192, 232).boxed().collect(Collectors.toList());
    List<Integer> BielanyWjazd = IntStream.rangeClosed(626, 666).boxed().collect(Collectors.toList());
    List<Integer> TyniecWjazd = IntStream.rangeClosed(997, 1037).boxed().collect(Collectors.toList());
    List<Integer> SkawinaWjazd = IntStream.rangeClosed(1478, 1518).boxed().collect(Collectors.toList());
    List<Integer> PoludnieWjazd = IntStream.rangeClosed(2089, 2129).boxed().collect(Collectors.toList());
    List<Integer> LagiewnikiWjazd = IntStream.rangeClosed(2360, 2400).boxed().collect(Collectors.toList());
    List<Integer> WieliczkaWjazd = IntStream.rangeClosed(3136, 3176).boxed().collect(Collectors.toList());
    List<Integer> BiezaznowWjazd = IntStream.rangeClosed(3580, 3620).boxed().collect(Collectors.toList());
    List<Integer> PrzewozWjazd = IntStream.rangeClosed(3926, 3966).boxed().collect(Collectors.toList());
    List<Integer> NowaHutaWjazd = IntStream.rangeClosed(4340, 4380).boxed().collect(Collectors.toList());
    List<Integer> GrebalowWjazd = IntStream.rangeClosed(4873, 4913).boxed().collect(Collectors.toList());
    List<Integer> MistrzejowiceWjazd = IntStream.rangeClosed(5486, 5526).boxed().collect(Collectors.toList());
    List<Integer> WegrzceWjazd = IntStream.rangeClosed(6153, 6193).boxed().collect(Collectors.toList());
    List<Integer> ZielonkiWjazd = IntStream.rangeClosed(6780, 6820).boxed().collect(Collectors.toList());
    List<Integer> ModlnicaWjazd = IntStream.rangeClosed(7446, 7486).boxed().collect(Collectors.toList());
    List<Integer> ModlniczkaWjazd = IntStream.rangeClosed(7753, 7793).boxed().collect(Collectors.toList());

    List<Integer> BaliceWyjazd = IntStream.rangeClosed(60, 100).boxed().collect(Collectors.toList());
    List<Integer> Balice2Wyjazd = IntStream.rangeClosed(252, 292).boxed().collect(Collectors.toList());
    List<Integer> BielanyWyjazd = IntStream.rangeClosed(686, 726).boxed().collect(Collectors.toList());
    List<Integer> TyniecWyjazd = IntStream.rangeClosed(1057, 1097).boxed().collect(Collectors.toList());
    List<Integer> SkawinaWyjazd = IntStream.rangeClosed(1538, 1578).boxed().collect(Collectors.toList());
    List<Integer> PoludnieWyjazd = IntStream.rangeClosed(2149, 2189).boxed().collect(Collectors.toList());
    List<Integer> LagiewnikiWyjazd = IntStream.rangeClosed(2420, 2460).boxed().collect(Collectors.toList());
    List<Integer> WieliczkaWyjazd = IntStream.rangeClosed(3196, 3236).boxed().collect(Collectors.toList());
    List<Integer> BiezaznowWyjazd = IntStream.rangeClosed(3640, 3680).boxed().collect(Collectors.toList());
    List<Integer> PrzewozWyjazd = IntStream.rangeClosed(3986, 4026).boxed().collect(Collectors.toList());
    List<Integer> NowaHutaWyjazd = IntStream.rangeClosed(4400, 4440).boxed().collect(Collectors.toList());
    List<Integer> GrebalowWyjazd = IntStream.rangeClosed(4933, 4973).boxed().collect(Collectors.toList());
    List<Integer> MistrzejowiceWyjazd = IntStream.rangeClosed(5546, 5586).boxed().collect(Collectors.toList());
    List<Integer> WegrzceWyjazd = IntStream.rangeClosed(6213, 6253).boxed().collect(Collectors.toList());
    List<Integer> ZielonkiWyjazd = IntStream.rangeClosed(6840, 6880).boxed().collect(Collectors.toList());
    List<Integer> ModlnicaWyjazd = IntStream.rangeClosed(7506, 7546).boxed().collect(Collectors.toList());
    List<Integer> ModlniczkaWyjazd = IntStream.rangeClosed(7813, 7853).boxed().collect(Collectors.toList());

    List<Integer> Exits = concatenate(BaliceWjazd, Balice2Wjazd, BielanyWjazd, TyniecWjazd, SkawinaWjazd,
            PoludnieWjazd, LagiewnikiWjazd, WieliczkaWjazd, BiezaznowWjazd, PrzewozWjazd, NowaHutaWjazd, GrebalowWjazd,
            MistrzejowiceWjazd, WegrzceWjazd, ZielonkiWjazd, ModlnicaWjazd, ModlniczkaWjazd);

    List<Integer> Entries = concatenate(BaliceWyjazd, Balice2Wyjazd, BielanyWyjazd, TyniecWyjazd, SkawinaWyjazd,
            PoludnieWyjazd, LagiewnikiWyjazd, WieliczkaWyjazd, BiezaznowWyjazd, PrzewozWyjazd, NowaHutaWyjazd,
            GrebalowWyjazd, MistrzejowiceWyjazd, WegrzceWyjazd, ZielonkiWyjazd, ModlnicaWyjazd, ModlniczkaWyjazd);

    public Lane() {
        lane = new Cell[cellNumber];
        for(int i = 0; i < cellNumber; i++) lane[i] = new Cell();
    }

    // Ustawia typ kratki na wjazd;
    public void setupEntryOneWay() {
        for (Integer point : Entries) {
            lane[point].cellType = CellType.ENTRY;
        }
    }

    // Ustawia typ kratki na zjazd
    public void setupExitOneWay() {
        for (Integer point : Exits) {
            lane[point].cellType = CellType.EXIT;
        }
    }

    public void setupEntryOtherWay() {
        for (Integer point : Exits) {
            lane[point].cellType = CellType.ENTRY;
        }
    }

    // Ustawia typ kratki na zjazd
    public void setupExitOtherWay() {
        for (Integer point : Entries) {
            lane[point].cellType = CellType.EXIT;
        }
    }

    public void setupDisabled(){
        for(int i = 0; i < cellNumber; i++){
            if(Entries.contains(i)){
                continue;
            }
            else if(Exits.contains(i)){
                continue;
            }
            else{
                lane[i].cellType = CellType.DISABLED;
            }
        }
    }

    public void setupNormal(){
        for(int i = 0; i < cellNumber; i++){
            lane[i].cellType = CellType.NORMAL;
        }
    }

    public static <T> List<T> concatenate(List<T>... lists) {
        List<T> result = new ArrayList<>();

        for (List<T> l : lists)
            result.addAll(l);

        return result;
    }

    public void CalculateNextFrame()
    {
        for (Cell cell: lane) {
            if(cell.occupied)
            {
                cell.vehicle.calculateNextVelocity();
            }
        }
    }

    public void MoveVehiclesForward()
    {
        Cell[] nextFrameLane = new Cell[cellNumber];
        for(int i=0; i<lane.length; i++)
        {
            if(lane[i].occupied)
            {
                Vehicle currentCellVehicle = lane[i].vehicle;
                if(currentCellVehicle.getVelocity() + i >= lane.length)
                {
                    nextFrameLane[(currentCellVehicle.getVelocity() +i)- lane.length].OccupyCell(currentCellVehicle);
                }
                else{
                    nextFrameLane[i+currentCellVehicle.getVelocity()].OccupyCell(currentCellVehicle);
                }
            }
        }
        lane = nextFrameLane;
    }

}