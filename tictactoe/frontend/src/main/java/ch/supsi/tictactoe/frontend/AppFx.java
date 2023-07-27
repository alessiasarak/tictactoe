package ch.supsi.tictactoe.frontend;


import ch.supsi.tictactoe.frontend.interaction.MainInteraction;
import ch.supsi.tictactoe.frontend.interaction.LanguagePreferenceInteraction;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;


public class AppFx extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        URL fxml = getClass().getResource("/ch/supsi/tictactoe/frontend/tictactoe.fxml");
        if (fxml == null) return;

        FXMLLoader fxmlLoader = new FXMLLoader(fxml);

        fxmlLoader.setResources(LanguagePreferenceInteraction.getLanguage());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Tic Tac Toe");
        stage.setResizable(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent event) {
                MainInteraction.quitDialog();
            }
        });
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
