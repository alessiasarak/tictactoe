package ch.supsi.tictactoe.frontend.interaction;

import ch.supsi.tictactoe.backend.controller.GameController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

import java.util.Optional;

import static ch.supsi.tictactoe.frontend.interaction.TicTacToeInteraction.isGameStarted;
import static ch.supsi.tictactoe.backend.model.enumList.Resource.BUNDLE;

public class MainInteraction {
    private Pane pane;
    //private GameController gameController;
    private FileInteraction fileInteraction;

    public MainInteraction(Pane pane) {
        this.pane = pane;
        //this.gameController = gameController;
        this.fileInteraction = new FileInteraction(pane);
    }

    public void quitDialogDef(){
        if(GameController.getInstance() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle(BUNDLE.getBundle().getString("dialog.saveGameTitle"));
            alert.setHeaderText("");
            alert.setContentText(BUNDLE.getBundle().getString("dialog.saveGame"));
            ButtonType ok = new ButtonType(BUNDLE.getBundle().getString("dialog.yes"), ButtonBar.ButtonData.OK_DONE);
            ButtonType cancel = new ButtonType(BUNDLE.getBundle().getString("dialog.no"), ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(ok, cancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ok){
                this.fileInteraction.saveGameDialog();
            }
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(BUNDLE.getBundle().getString("dialog.quitTitle"));
        alert.setHeaderText("");
        alert.setContentText( BUNDLE.getBundle().getString("dialog.quit"));
        ButtonType exit = new ButtonType(BUNDLE.getBundle().getString("dialog.yes"), ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(BUNDLE.getBundle().getString("dialog.no"), ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(exit, cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == exit){
            isGameStarted = false;
            System.exit(0);
        }
    }
    public static void quitDialog(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(BUNDLE.getBundle().getString("dialog.quitTitle"));
        alert.setHeaderText("");
        alert.setContentText( BUNDLE.getBundle().getString("dialog.quit"));
        ButtonType exit = new ButtonType(BUNDLE.getBundle().getString("dialog.yes"), ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(BUNDLE.getBundle().getString("dialog.no"), ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(exit, cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == exit){
            isGameStarted = false;
            System.exit(0);
        }
    }

    public void aboutDialog(){
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("About");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.setContentText("Alessia Sarak, V - 2.0");
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
