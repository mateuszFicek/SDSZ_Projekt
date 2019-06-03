package Controller;

import Model.Settings;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

public abstract class BaseController implements Initializable {

    Settings settings;

    public void goBackToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainMenu.fxml"));

        Parent mainMenuParent = loader.load();
        Scene mainMenuScene = new Scene(mainMenuParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);

        MainMenuController mainMenuController = loader.getController();
        mainMenuController.initSettings(settings);
        window.show();
    }

    protected void initSettings(Settings settings) {
        this.settings = settings;
    }
}
