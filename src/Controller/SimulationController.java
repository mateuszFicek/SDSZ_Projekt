package Controller;

import Model.Highway.Highway;
import Model.Highway.Road;
import Model.Settings;
import Model.Simulation;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationController extends BaseController implements Initializable {
    public Button backButton;

    public Label simLabel;
    public Button magicStartButton;

    public Simulation simulation;

    @FXML
    private List<Label> labelList;


    AnimationTimer h = new AnimationTimer() {
        int i = 0;

        @Override
        public void handle(long now) {
            if (i % 60 == 0) {
                Highway.resetNumbersOfCarOnSegments();
                for (Road r : simulation.getHighway().roads) {
                    r.generateNextFrame();

                }
                updateLabelsText();
                Highway.printCarsBySegment();
            }
            i += 1;
        }
    };

    @Override
    protected void initSettings(Settings settings) {
        this.settings = settings;
        System.out.println(this.settings);
    }

    protected void initSimulation(){
        this.simulation = new Simulation(settings);
    }

    @Override
    public void goBackToMenu(ActionEvent event) throws IOException {
        h.stop();
        super.goBackToMenu(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateLabelsText();

    }

    private void updateLabelsText() {
        for(int i = 0; i < labelList.size(); ++i){
            labelList.get(i).setText(String.valueOf(Highway.carsOnSegment.get(i)));
        }
    }

    public void startSimulation(ActionEvent event) {
    }
}