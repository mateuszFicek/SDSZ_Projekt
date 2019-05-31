package Model.Highway;

import Model.Highway.Cell.CellType;
import Model.Settings;
import Model.Vehicles.Car;
import Model.Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//Pas sklada sie z 8353 kratek

public class Lane {
    private int laneNumber;
    private int numberOfCarsOnLane;
    public CircularArrayList<Cell> lane;
    int exitLength = 40;
    int spaceBetweenExitAndEntry = 20;
    public int[] optionsThroughput;
    private int entryCounter = 0;
    private final int cellNumber = 8353;


    private List<Integer> BaliceWjazd = IntStream.rangeClosed(0, 40).boxed().collect(Collectors.toList());
    private List<Integer> Balice2Wjazd = IntStream.rangeClosed(192, 232).boxed().collect(Collectors.toList());
    private List<Integer> BielanyWjazd = IntStream.rangeClosed(626, 666).boxed().collect(Collectors.toList());
    private List<Integer> TyniecWjazd = IntStream.rangeClosed(997, 1037).boxed().collect(Collectors.toList());
    private List<Integer> SkawinaWjazd = IntStream.rangeClosed(1478, 1518).boxed().collect(Collectors.toList());
    private List<Integer> PoludnieWjazd = IntStream.rangeClosed(2089, 2129).boxed().collect(Collectors.toList());
    private List<Integer> LagiewnikiWjazd = IntStream.rangeClosed(2360, 2400).boxed().collect(Collectors.toList());
    private List<Integer> WieliczkaWjazd = IntStream.rangeClosed(3136, 3176).boxed().collect(Collectors.toList());
    private List<Integer> BiezaznowWjazd = IntStream.rangeClosed(3580, 3620).boxed().collect(Collectors.toList());
    private List<Integer> PrzewozWjazd = IntStream.rangeClosed(3926, 3966).boxed().collect(Collectors.toList());
    private List<Integer> NowaHutaWjazd = IntStream.rangeClosed(4340, 4380).boxed().collect(Collectors.toList());
    private List<Integer> GrebalowWjazd = IntStream.rangeClosed(4873, 4913).boxed().collect(Collectors.toList());
    private List<Integer> MistrzejowiceWjazd = IntStream.rangeClosed(5486, 5526).boxed().collect(Collectors.toList());
    private List<Integer> WegrzceWjazd = IntStream.rangeClosed(6153, 6193).boxed().collect(Collectors.toList());
    private List<Integer> ZielonkiWjazd = IntStream.rangeClosed(6780, 6820).boxed().collect(Collectors.toList());
    private List<Integer> ModlnicaWjazd = IntStream.rangeClosed(7446, 7486).boxed().collect(Collectors.toList());
    private List<Integer> ModlniczkaWjazd = IntStream.rangeClosed(7753, 7793).boxed().collect(Collectors.toList());

    private List<Integer> BaliceWyjazd = IntStream.rangeClosed(60, 100).boxed().collect(Collectors.toList());
    private List<Integer> Balice2Wyjazd = IntStream.rangeClosed(252, 292).boxed().collect(Collectors.toList());
    private List<Integer> BielanyWyjazd = IntStream.rangeClosed(686, 726).boxed().collect(Collectors.toList());
    private List<Integer> TyniecWyjazd = IntStream.rangeClosed(1057, 1097).boxed().collect(Collectors.toList());
    private List<Integer> SkawinaWyjazd = IntStream.rangeClosed(1538, 1578).boxed().collect(Collectors.toList());
    private List<Integer> PoludnieWyjazd = IntStream.rangeClosed(2149, 2189).boxed().collect(Collectors.toList());
    private List<Integer> LagiewnikiWyjazd = IntStream.rangeClosed(2420, 2460).boxed().collect(Collectors.toList());
    private List<Integer> WieliczkaWyjazd = IntStream.rangeClosed(3196, 3236).boxed().collect(Collectors.toList());
    private List<Integer> BiezaznowWyjazd = IntStream.rangeClosed(3640, 3680).boxed().collect(Collectors.toList());
    private List<Integer> PrzewozWyjazd = IntStream.rangeClosed(3986, 4026).boxed().collect(Collectors.toList());
    private List<Integer> NowaHutaWyjazd = IntStream.rangeClosed(4400, 4440).boxed().collect(Collectors.toList());
    private List<Integer> GrebalowWyjazd = IntStream.rangeClosed(4933, 4973).boxed().collect(Collectors.toList());
    private List<Integer> MistrzejowiceWyjazd = IntStream.rangeClosed(5546, 5586).boxed().collect(Collectors.toList());
    private List<Integer> WegrzceWyjazd = IntStream.rangeClosed(6213, 6253).boxed().collect(Collectors.toList());
    private List<Integer> ZielonkiWyjazd = IntStream.rangeClosed(6840, 6880).boxed().collect(Collectors.toList());
    private List<Integer> ModlnicaWyjazd = IntStream.rangeClosed(7506, 7546).boxed().collect(Collectors.toList());
    private List<Integer> ModlniczkaWyjazd = IntStream.rangeClosed(7813, 7853).boxed().collect(Collectors.toList());

    List<Integer> Exits = concatenate(BaliceWjazd, Balice2Wjazd, BielanyWjazd, TyniecWjazd, SkawinaWjazd,
            PoludnieWjazd, LagiewnikiWjazd, WieliczkaWjazd, BiezaznowWjazd, PrzewozWjazd, NowaHutaWjazd, GrebalowWjazd,
            MistrzejowiceWjazd, WegrzceWjazd, ZielonkiWjazd, ModlnicaWjazd, ModlniczkaWjazd);

    List<Integer> Entries = concatenate(BaliceWyjazd, Balice2Wyjazd, BielanyWyjazd, TyniecWyjazd, SkawinaWyjazd,
            PoludnieWyjazd, LagiewnikiWyjazd, WieliczkaWyjazd, BiezaznowWyjazd, PrzewozWyjazd, NowaHutaWyjazd,
            GrebalowWyjazd, MistrzejowiceWyjazd, WegrzceWyjazd, ZielonkiWyjazd, ModlnicaWyjazd, ModlniczkaWyjazd);

    Lane() {
        lane = new CircularArrayList<>();
        for (int i = 0; i < cellNumber; i++) lane.add(new Cell());
    }


    // Setup cell to entrance;
    void setupEntryOneWay() {
        for (Integer point : Entries) {
            lane.get(point).cellType = CellType.ENTRY;
        }
    }

    // Setup cell to exit
    void setupExitOneWay() {
        for (Integer point : Exits) {
            lane.get(point).cellType = CellType.EXIT;
        }
    }

    void setupEntryOtherWay() {
        for (Integer point : Exits) {
            lane.get(point).cellType = CellType.ENTRY;
        }
    }

    void setupExitOtherWay() {
        for (Integer point : Entries) {
            lane.get(point).cellType = CellType.EXIT;
        }
    }

    void setupDisabled() {
        for (int i = 0; i < cellNumber; i++) {
            if (Entries.contains(i)) {
                continue;
            } else if (Exits.contains(i)) {
                continue;
            } else {
                lane.get(i).cellType = CellType.DISABLED;
            }
        }
    }

    void setupNormal() {
        for (int i = 0; i < cellNumber; i++) {
            lane.get(i).cellType = CellType.NORMAL;
        }
    }

    public static <T> List<T> concatenate(List<T>... lists) {
        List<T> result = new ArrayList<>();

        for (List<T> l : lists)
            result.addAll(l);

        return result;
    }


    void calculateNextFrame(int laneIndex) {
        for (int i = 0; i < lane.size(); i++) {
            if (lane.get(i).occupied) {
                Vehicle currentCar = lane.get(i).vehicle;
                currentCar.calculateDistanceToNextFrontVehicle(laneIndex);

                if (laneIndex == 1) {
                    currentCar.decideAboutChangeLaneToLeft(laneIndex);
                    currentCar.numberOfCellsToOvertake -= currentCar.getVelocity();
                }
                if (laneIndex == 0 && currentCar.numberOfCellsToOvertake <= 0 || laneIndex == 0 && currentCar.numberOfExits == 0) {
                    currentCar.decideAboutChangeLaneToRight(laneIndex);
                }
                if (laneIndex == 0)
                    currentCar.numberOfCellsToOvertake -= currentCar.getVelocity();
                if (laneIndex == 2 && currentCar.hasEntered) {
                    currentCar.decideAboutEnter();
                }


            }
        }
        for (int i = 0; i < lane.size(); i++) {
            if (lane.get(i).occupied) {
                Vehicle currentCar = lane.get(i).vehicle;
                if (laneIndex == 2 && currentCar.hasEntered) {
                    currentCar.enterHighway();
                }
                currentCar.checkExits(laneIndex);
                int newIndex = currentCar.changeLane(laneIndex);
                if (!currentCar.disabled)
                    currentCar.calculateNextVelocity(newIndex);
            }
        }
    }


    int moveVehiclesForward() {
        numberOfCarsOnLane = 0;
        int segment;
        CircularArrayList<Cell> nextFrameLane = new CircularArrayList<>(cellNumber);
        for (int i = 0; i < cellNumber; i++) nextFrameLane.add(new Cell(lane.get(i).cellType));

        for (int i = 0; i < lane.size(); i++) {
            if (lane.get(i).occupied) {
                numberOfCarsOnLane += 1;
                segment = Highway.segmentsByCell.get(i);
                Highway.carsOnSegment.set(segment, (Highway.carsOnSegment.get(segment) + 1));
                Vehicle currentCellVehicle = lane.get(i).vehicle;
                if (currentCellVehicle.getVelocity() + i >= lane.size()) {
                    nextFrameLane.get((currentCellVehicle.getVelocity() + i) - lane.size()).occupyCell(currentCellVehicle);
                } else {
                    nextFrameLane.get(i + currentCellVehicle.getVelocity()).occupyCell(currentCellVehicle);
                    if (nextFrameLane.get(i + currentCellVehicle.getVelocity()).cellType == CellType.DISABLED) {
                        nextFrameLane.get(i + currentCellVehicle.getVelocity()).freeCell();
                    }
                }
            }
        }
        lane = nextFrameLane;
        return numberOfCarsOnLane;
    }

    void enterCars() {
        int[] roadThroughput = Settings.throughput.clone();
        Random probability = new Random();
        for (int i = 0; i < cellNumber; i++) {

            if (lane.get(i).cellType == CellType.ENTRY) {
                if (!lane.get(i).occupied) {

                    if (probability.nextInt(61) - roadThroughput[entryCounter] < 0 && !lane.get(i).occupied) {
                        int maxVelocity;
                        int randomNumber = probability.nextInt(6);
                        if (randomNumber == 0) {
                            maxVelocity = Settings.carMaxVelocity;
                        } else {
                            maxVelocity = probability.nextInt(Settings.carMaxUpperVelocity - Settings.carMaxVelocity) + Settings.carMaxVelocity + 1;
                        }

                        Car toAdd = new Car(maxVelocity, probability.nextInt(maxVelocity - 2) + 2, probability.nextInt(6) + 1);
                        toAdd.hasEntered = true;
                        toAdd.numberOfCellsToOvertake = 40;
                        lane.get(i).occupyCell(toAdd);
                    }

                }
                i += 40;
                entryCounter++;
            }
        }
        entryCounter = 0;
    }

}