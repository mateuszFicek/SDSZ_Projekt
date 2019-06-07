package Controller;

import Model.Highway.Highway;
import Model.Highway.Lane;
import Model.Highway.Road;
import Model.Settings;
import Model.Simulation;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationController extends BaseController implements Initializable {
    public Button backButton;

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
                Lane.iterCounter++;
                updateLabelsText();
            }
            i += 1;
        }
    };

    @Override
    protected void initSettings(Settings settings) {
        this.settings = settings;
    }

    void resetSettings() {
        Settings.throughput = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
    }

    void initSimulation() {
        this.simulation = new Simulation(settings);
    }

    void initSimulation(Simulation simulation) {
        this.simulation = simulation;
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
        for (int i = 0; i < labelList.size() / 2; ++i) {
            labelList.get(i).setText(String.valueOf(Highway.carsOnSegment.get(i)));
            labelList.get(i + labelList.size() / 2).setText(String.valueOf(Road.carsPerMinute[i]));
        }
    }


    public void startSimulation(ActionEvent event) {
    }

    public void simulateSegment1(ActionEvent event) throws IOException {
        changeSceneToSegment(1, event);
    }

    public void simulateSegment2(ActionEvent event) throws IOException {
        changeSceneToSegment(2, event);
    }

    public void simulateSegment3(ActionEvent event) throws IOException {
        changeSceneToSegment(3, event);
    }

    public void simulateSegment4(ActionEvent event) throws IOException {
        changeSceneToSegment(4, event);
    }

    public void simulateSegment5(ActionEvent event) throws IOException {
        changeSceneToSegment(5, event);
    }

    public void simulateSegment6(ActionEvent event) throws IOException {
        changeSceneToSegment(6, event);
    }

    public void simulateSegment7(ActionEvent event) throws IOException {
        changeSceneToSegment(7, event);
    }

    public void simulateSegment8(ActionEvent event) throws IOException {
        changeSceneToSegment(8, event);
    }

    public void simulateSegment9(ActionEvent event) throws IOException {
        changeSceneToSegment(9, event);
    }

    public void simulateSegment10(ActionEvent event) throws IOException {
        changeSceneToSegment(10, event);
    }

    public void simulateSegment11(ActionEvent event) throws IOException {
        changeSceneToSegment(11, event);
    }

    public void simulateSegment12(ActionEvent event) throws IOException {
        changeSceneToSegment(12, event);
    }

    public void simulateSegment13(ActionEvent event) throws IOException {
        changeSceneToSegment(13, event);
    }

    public void simulateSegment14(ActionEvent event) throws IOException {
        changeSceneToSegment(14, event);
    }

    public void simulateSegment15(ActionEvent event) throws IOException {
        changeSceneToSegment(15, event);
    }

    public void simulateSegment16(ActionEvent event) throws IOException {
        changeSceneToSegment(16, event);
    }

    public void simulateSegment17(ActionEvent event) throws IOException {
        changeSceneToSegment(17, event);
    }

    private void changeSceneToSegment(int i, ActionEvent event) throws IOException {
        h.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Segment.fxml"));

        Parent segmentParent = loader.load();
        Scene simulationScene = new Scene(segmentParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(simulationScene);


        SegmentController segmentController = loader.getController();
        segmentController.initSettings(super.settings);
        segmentController.initSimulation(simulation);
        segmentController.initSegment(i);
        segmentController.h.start();


        window.show();
    }

    public void changeSceneToConfiguration(ActionEvent event) throws IOException {
        h.stop();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Configuration.fxml"));

        Parent configurationParent = loader.load();
        Scene configurationScene = new Scene(configurationParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(configurationScene);

        ConfigurationController configurationController = loader.getController();
        configurationController.initSettings(super.settings);
        configurationController.initSimulation(simulation);
        configurationController.initializeSpinners();

        window.show();
    }
}
