package Model.Highway;

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

    public Lane(int laneLength) {
        lane = new Cell[laneLength];
    }

    public void setupEntry() {
        int i;
        for (i = 0; i <= 40; i++) {
            lane[i].cellType = CellType.ENTRY;
        }
    }

    public void setupExit() {

    }

}