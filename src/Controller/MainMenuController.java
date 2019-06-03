package Controller;

import Model.Settings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends BaseController implements Initializable {
    public Button startButton;
    public Button settingsButton;

    public void changeSceneToSimulation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Simulation.fxml"));

        Parent simulationParent = loader.load();
        Scene simulationScene = new Scene(simulationParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(simulationScene);


        SimulationController simulationController = loader.getController();
        simulationController.initSettings(super.settings);
        simulationController.resetSettings();
        simulationController.initSimulation();

        window.show();
        simulationController.h.start();
    }

    public void changeSceneToSettings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Options.fxml"));

        Parent optionsParent = loader.load();
        Scene simulationScene = new Scene(optionsParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(simulationScene);


        OptionsController optionsController = loader.getController();
        optionsController.initSettings(super.settings);
        optionsController.initTime();
        optionsController.initSpinners();
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!Settings.initialized) {
            settings = new Settings();
            Settings.initialized = true;
        }

    }

    protected void initSettings(Settings settings) {
        this.settings = settings;
    }
}
