package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

public class BaseController {

    public void goBackToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../MainMenu.fxml"));

        Parent mainMenuParent = loader.load();
        Scene mainMenuScene = new Scene(mainMenuParent);

        Stage window = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        window.setScene(mainMenuScene);


        MainMenuController mainMenuController = loader.getController();

        window.show();
    }
}
