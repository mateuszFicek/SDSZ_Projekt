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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionsController extends BaseController implements Initializable {
    public Button backButton;

    public ChoiceBox<String> timeBox = new ChoiceBox();
    public Spinner<Integer> carMaxVelocity;
    public Spinner<Integer> tirMaxVelocity;

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
    public void initSpinners(){
        tirMaxVelocity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,3, settings.getCarMaxVelocity()));
        carMaxVelocity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(4, 7, settings.getCarMaxUpperVelocity()));
    }

    @Override
    public void goBackToMenu(ActionEvent event) throws IOException {
        settings.setCarMaxVelocity(tirMaxVelocity.getValue());
        settings.setCarMaxUpperVelocity(carMaxVelocity.getValue());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainMenu.fxml"));

        Parent mainMenuParent = loader.load();
        Scene mainMenuScene = new Scene(mainMenuParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);

        MainMenuController mainMenuController = loader.getController();
        mainMenuController.initSettings(settings);
        window.show();
    }
}
