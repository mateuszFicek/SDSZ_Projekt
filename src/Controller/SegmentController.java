package Controller;

import Model.Highway.Cell;
import Model.Highway.Highway;
import Model.Highway.Road;
import Model.Simulation;
import Model.Vehicles.Vehicle;
import View.HighwayGrid;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SegmentController extends BaseController {

    public Label segmentLabel;
    public Label carId;
    public Label carSpeed;
    public Label carDistanceToNext;
    public Label carExitsRemaining;
    public Label carMaxSpeed;


    private int numberOfLanes;
    private int numberOfCells;
    private int segment;
    private int pickedCarHash;

    private HighwayGrid highwayGridOne;
    private HighwayGrid highwayGridTwo;
    public ScrollPane scrollPane;
    public ScrollPane scrollPane2;
    private Simulation simulation;

    AnimationTimer h = new AnimationTimer() {
        int i = 0;

        @Override
        public void handle(long now) {
            if (i % 60 == 0) {
                Highway.resetNumbersOfCarOnSegments();
                for (Road r : simulation.getHighway().roads) {
                    r.generateNextFrame();
                }
                updateGridNumberOne();
                updateGridNumberTwo();
            }
            i += 1;
        }
    };
    private GridPane gridOne;
    private GridPane gridTwo;

    private void updateGridNumberOne() {

        updateGrid(0);
        this.gridOne = (GridPane) highwayGridOne.getGrid();
        this.scrollPane.setContent(gridOne);
    }

    private void updateGridNumberTwo() {

        updateGrid(1);
        this.gridTwo = (GridPane) highwayGridTwo.getGrid();
        this.scrollPane2.setContent(gridTwo);
    }

    private void updateGrid(int i) {
        HighwayGrid highwayGrid;
        if (i == 0) {
            highwayGrid = highwayGridOne;
        } else {
            highwayGrid = highwayGridTwo;
        }
        for (int j = 0; j < numberOfLanes; ++j) {
            for (int k = 0; k < numberOfCells; ++k) {
                Cell cell = simulation.getHighway().roads[i].road[j].lane.get(k + Highway.startOfSegments.get(segment - 1));
                int x;
                int y;
                if (i == 0) {
                    x = (j * (1 - i) + ((2 - j) * i));
                    y = k;
                } else {
                    x = (j * (1 - i) + ((2 - j) * i));
                    y = numberOfCells - k - 1;
                }
                if (cell.occupied) {
                    Vehicle vehicle = cell.vehicle;
                    if (cell.vehicle.hashCode() == pickedCarHash) {

                        highwayGrid.setCellMatrixColor(x, y, Color.YELLOW);
                        highwayGrid.getCellMatrix()[x][y].setVehicle(vehicle);
                        if (vehicle.maxVelocity == settings.getCarMaxVelocity()) {
                            try {
                                highwayGrid.setCellMatrixColor(x, y + 1 - (2 * i), Color.YELLOW);
                                k++;
                            } catch (ArrayIndexOutOfBoundsException ignored) {

                            }
                        }
                        carDistanceToNext.setText("Odległość do następnego samochodu" + String.valueOf(vehicle.getDistanceToNextCarInFront()));
                        carSpeed.setText("Prędkość " + String.valueOf(vehicle.getVelocity()));
                        carExitsRemaining.setText("Pozostałe zjazdy " + String.valueOf(vehicle.numberOfExits));
                    } else if (cell.vehicle.maxVelocity == settings.getCarMaxVelocity()) {
                        highwayGrid.setCellMatrixColor(x, y, Color.ORANGE);
                        try {
                            highwayGrid.setCellMatrixColor(x, y + 1 - (2 * i), Color.ORANGE);
                            k++;
                        } catch (ArrayIndexOutOfBoundsException ignored) {
                        }
                        highwayGrid.getCellMatrix()[x][y].setVehicle(vehicle);
                    } else {
                        highwayGrid.setCellMatrixColor(x, y, Color.RED);
                        highwayGrid.getCellMatrix()[x][y].setVehicle(vehicle);
                    }

                    highwayGrid.getCellMatrix()[x][y].setOnMouseClicked(event -> {
                        Vehicle vehicle1 = highwayGrid.getCellMatrix()[x][y].getVehicle();
                        pickedCarHash = vehicle1.hashCode();
                        carDistanceToNext.setText("Odległość do następnego samochodu " + vehicle1.getDistanceToNextCarInFront());
                        carSpeed.setText("Prędkość " + String.valueOf(vehicle1.getVelocity()));
                        carExitsRemaining.setText("Pozostałe zjazdy " + String.valueOf(vehicle1.numberOfExits));
                        carMaxSpeed.setText("Maksymalna prędkość samochodu " + String.valueOf(vehicle1.maxVelocity));
                    });

                } else if (cell.cellType == Cell.CellType.DISABLED) {
                    highwayGrid.setCellMatrixColor(x, y, Color.BLACK);
                } else if (cell.cellType == Cell.CellType.ENTRY) {
                    highwayGrid.setCellMatrixColor(x, y, Color.GREEN);
                } else if (cell.cellType == Cell.CellType.EXIT) {
                    highwayGrid.setCellMatrixColor(x, y, Color.BLUE);
                } else {
                    highwayGrid.setCellMatrixColor(x, y, Color.WHITE);
                }
            }
        }
        if (i == 0) {
            highwayGridOne = highwayGrid;
        } else {
            highwayGridTwo = highwayGrid;
        }
    }

    public void goBackToSimulation(ActionEvent event) throws IOException {
        h.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Simulation.fxml"));

        Parent simulationParent = loader.load();
        Scene simulationScene = new Scene(simulationParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(simulationScene);


        SimulationController simulationController = loader.getController();
        simulationController.initSettings(super.settings);
        simulationController.initSimulation(simulation);
        simulationController.h.start();
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.scrollPane.hvalueProperty().addListener((observable, oldValue, newValue) -> scrollPane2.setHvalue((Double) newValue));
        this.scrollPane2.hvalueProperty().addListener((observable, oldValue, newValue) -> scrollPane.setHvalue((Double) newValue));

    }

    public void initSegment(int i) {
        this.segment = i;
        this.numberOfLanes = this.simulation.getHighway().roads[0].road.length;
        this.numberOfCells = Highway.segmentsLen.get(i - 1);
        this.highwayGridOne = new HighwayGrid(numberOfLanes, numberOfCells);
        this.highwayGridTwo = new HighwayGrid(numberOfLanes, numberOfCells);
        this.gridTwo = (GridPane) highwayGridTwo.getGrid();
        this.gridOne = (GridPane) highwayGridOne.getGrid();
        this.scrollPane.setContent(this.gridOne);
        this.scrollPane2.setContent(this.gridTwo);
        this.segmentLabel.setText(Highway.segmentsNames[i - 1] + " -  " + Highway.segmentsNames[i % 17]);
    }

    public void initSimulation(Simulation simulation) {
        this.simulation = simulation;
    }


}
