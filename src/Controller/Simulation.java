package Controller;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Simulation extends BaseController implements Initializable {
    public Button backButton;

    public Label simLabel;
    public Button magicStartButton;

    AnimationTimer h = new AnimationTimer() {
        int i = 0;

        @Override
        public void handle(long now) {
            if (i % 20 == 0) {
                simLabel.setText(String.valueOf(i));
            }
            i += 1;
        }
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void startSimulation(ActionEvent event) {
    }
}
