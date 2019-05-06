package Controller;

import Model.Settings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController extends BaseController implements Initializable {
    public Button backButton;

    public ChoiceBox<String> timeBox = new ChoiceBox();
    public Spinner carMaxVelocity;
    public Spinner tirMaxVelocity;

    @FXML
    public Label poraDniaLabel;


    @Override
    protected void initSettings(Settings settings) {
        this.settings = settings;
        System.out.println(this.settings);
    }

    protected void initTime(){
        timeBox.setItems(FXCollections.observableArrayList("Noc", "Poranek", "Popołudnie"));
        timeBox.setValue(settings.times[settings.getTime()]);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            settings.setTime(newValue.intValue());

        });
    }
}
