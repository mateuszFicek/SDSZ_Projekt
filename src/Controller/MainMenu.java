package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu extends BaseController{
    public Button startButton;
    public Button settingsButton;

    public void changeSceneToSimulation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Simulation.fxml"));

        Parent simulationParent = loader.load();
        Scene simulationScene = new Scene(simulationParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(simulationScene);


        Simulation simulationController = loader.getController();

        window.show();
    }

    public void changeSceneToSettings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Options.fxml"));

        Parent optionsParent = loader.load();
        Scene simulationScene = new Scene(optionsParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(simulationScene);


        Options optionsController = loader.getController();

        window.show();
    }
}
