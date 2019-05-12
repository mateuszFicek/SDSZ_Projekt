package Controller;

import Model.Highway.Cell;
import Model.Highway.Highway;
import Model.Highway.Road;
import Model.Simulation;
import View.HighwayGrid;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SegmentController extends BaseController{

    public Label segmentLabel;
    private int numberOfLanes;
    private int numberOfCells;
    private int numberOfRoads;
    private int segment;

    public HighwayGrid highwayGrid;
    public ScrollPane scrollPane;
    Simulation simulation;

    AnimationTimer h = new AnimationTimer() {
        int i = 0;

        @Override
        public void handle(long now) {
            if (i % 60 == 0) {
                Highway.resetNumbersOfCarOnSegments();
                for (Road r : simulation.getHighway().roads) {
                    r.generateNextFrame();
                }
                updateGrid();
                Highway.printCarsBySegment();
            }
            i += 1;
        }
    };
    private GridPane grid;

    private void updateGrid() {
        for(int i = 0; i < numberOfRoads; ++i){
            for(int j = 0; j < numberOfLanes; ++j){
                for (int k = 0; k < numberOfCells; ++k){
                    if (simulation.getHighway().roads[i].road[j].lane[k+ Highway.startOfSegments.get(segment - 1)].occupied){
                        highwayGrid.setCellMatrix((i*(numberOfLanes))+(j*(1-i)+ ((2-j)*i)),k, Color.RED);
                    }
                    else if(simulation.getHighway().roads[i].road[j].lane[k + Highway.startOfSegments.get(segment - 1)].cellType == Cell.CellType.DISABLED){
                        highwayGrid.setCellMatrix((i*(numberOfLanes))+(j*(1-i)+ ((2-j)*i)),k, Color.BLACK);
                    }
                    else if(simulation.getHighway().roads[i].road[j].lane[k + Highway.startOfSegments.get(segment - 1)].cellType == Cell.CellType.ENTRY){
                        highwayGrid.setCellMatrix((i*(numberOfLanes))+(j*(1-i)+ ((2-j)*i)),k, Color.GREEN);
                    }
                    else if(simulation.getHighway().roads[i].road[j].lane[k + Highway.startOfSegments.get(segment - 1)].cellType == Cell.CellType.EXIT){
                        highwayGrid.setCellMatrix((i*(numberOfLanes))+(j*(1-i)+ ((2-j)*i)),k, Color.BLUE);
                    }
                    else {
                        highwayGrid.setCellMatrix((i*(numberOfLanes))+(j*(1-i)+ ((2-j)*i)),k, Color.WHITE);
                    }
                }
            }
        }
        this.grid = (GridPane) highwayGrid.getGrid();
        this.scrollPane.setContent(grid);
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

    }

    public void initSegment(int i) {
        this.segment = i;
        this.numberOfRoads = this.simulation.getHighway().roads.length;
        this.numberOfLanes = this.simulation.getHighway().roads[0].road.length;
        this.numberOfCells = Highway.segmentsLen.get(i-1);
        this.highwayGrid = new HighwayGrid(numberOfRoads*numberOfLanes, numberOfCells);
        this.grid = (GridPane) highwayGrid.getGrid();
        this.scrollPane.setContent(this.grid);
        this.segmentLabel.setText(Highway.segmentsNames[i-1]);
    }

    public void initSimulation(Simulation simulation) {
        this.simulation = simulation;
    }


}
