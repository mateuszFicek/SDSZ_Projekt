package Model.Highway;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Model.Highway.Cell.CellType;

//Pas sklada sie z 8353 kratek

class Lane {
    protected Cell[] lane;
    int exitLength = 40;
    int spaceBetweenExitAndEntry = 20;

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

    List<Integer> Entries = concatenate(BaliceWjazd, Balice2Wjazd, BielanyWjazd, TyniecWjazd, SkawinaWjazd,
            PoludnieWjazd, LagiewnikiWjazd, WieliczkaWjazd, BiezaznowWjazd, PrzewozWjazd, NowaHutaWjazd, GrebalowWjazd,
            MistrzejowiceWjazd, WegrzceWjazd, ZielonkiWjazd, ModlnicaWjazd, ModlniczkaWjazd);

    List<Integer> Exits = concatenate(BaliceWyjazd, Balice2Wyjazd, BielanyWyjazd, TyniecWyjazd, SkawinaWyjazd,
            PoludnieWyjazd, LagiewnikiWyjazd, WieliczkaWyjazd, BiezaznowWyjazd, PrzewozWyjazd, NowaHutaWyjazd,
            GrebalowWyjazd, MistrzejowiceWyjazd, WegrzceWyjazd, ZielonkiWyjazd, ModlnicaWyjazd, ModlniczkaWyjazd);

    public Lane(int laneLength) {
        lane = new Cell[laneLength];
    }

    // Ustawia typ kratki na wjazd;
    // setupEntry dla jednej jezdni = setupExit dla drugiej jezdni
    public void setupEntry() {
        for (Integer point : Entries) {
            lane[point].cellType = CellType.ENTRY;
        }
    }

    // Ustawia typ kratki na zjazd
    public void setupExit() {
        for (Integer point : Exits) {
            lane[point].cellType = CellType.EXIT;
        }
    }

    public static <T> List<T> concatenate(List<T>... lists) {
        List<T> result = new ArrayList<>();

        for (List<T> l : lists)
            result.addAll(l);

        return result;
    }

}