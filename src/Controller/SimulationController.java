package Controller;

import Model.Highway.Road;
import Model.Simulation;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulationController extends BaseController implements Initializable {
    public Button backButton;

    public Label simLabel;
    public Button magicStartButton;

    public Simulation simulation = new Simulation();

    AnimationTimer h = new AnimationTimer() {
        int i = 0;

        @Override
        public void handle(long now) {
            if (i % 60 == 0) {
                for (Road r : simulation.getHighway().roads) {
                    r.generateNextFrame();
                }
            }
            i += 1;
        }
    };

    @Override
    public void goBackToMenu(ActionEvent event) throws IOException {
        h.stop();
        super.goBackToMenu(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startSimulation(ActionEvent event) {
    }
}
