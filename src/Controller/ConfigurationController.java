package Controller;

import Model.Simulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConfigurationController extends BaseController {

    public Spinner<Integer> entranceThroughtput1;
    public Spinner<Integer> entranceThroughtput2;
    public Spinner<Integer> entranceThroughtput3;
    public Spinner<Integer> entranceThroughtput4;
    public Spinner<Integer> entranceThroughtput5;
    public Spinner<Integer> entranceThroughtput6;
    public Spinner<Integer> entranceThroughtput7;
    public Spinner<Integer> entranceThroughtput8;
    public Spinner<Integer> entranceThroughtput9;
    public Spinner<Integer> entranceThroughtput10;
    public Spinner<Integer> entranceThroughtput11;
    public Spinner<Integer> entranceThroughtput12;
    public Spinner<Integer> entranceThroughtput13;
    public Spinner<Integer> entranceThroughtput14;
    public Spinner<Integer> entranceThroughtput15;
    public Spinner<Integer> entranceThroughtput16;
    public Spinner<Integer> entranceThroughtput17;
    private Simulation simulation;

    private int[] throughput = new int[17];
    @FXML
    private List<Spinner<Integer>> spinnerList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void goBackToSimulation(ActionEvent event) throws IOException {

        getThroughputValues();
        settings.setThroughput(throughput);

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

    private void getThroughputValues() {
        for (int i = 0; i < spinnerList.size(); ++i) {
            throughput[i] = spinnerList.get(i).getValue();
        }
    }

    public void initSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public void initializeSpinners() {
        for (int i = 0; i < spinnerList.size(); ++i) {
            spinnerList.get(i).setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, settings.getThroughput()[i]));
            spinnerList.get(i).setTooltip(new Tooltip("Ilość samochodów na minutę"));
        }
    }
}
