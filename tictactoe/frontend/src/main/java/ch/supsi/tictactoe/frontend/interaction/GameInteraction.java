package ch.supsi.tictactoe.frontend.interaction;

import ch.supsi.tictactoe.backend.controller.GameController;
import ch.supsi.tictactoe.backend.model.enumList.GameStatus;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.StageStyle;

import java.util.Optional;

import static ch.supsi.tictactoe.backend.model.enumList.Resource.BUNDLE;

public class GameInteraction {
    private FileInteraction fileInteraction;
    //private GameController gameController;
    private Pane pane;

    public GameInteraction(Pane pane) {
        //this.gameController = gameController;
        this.pane = pane;
        this.fileInteraction = new FileInteraction(pane);
    }

    public void endGameDialog(GameStatus gameSymbol){
        Dialog<ButtonType> end = new Dialog<ButtonType>();
        end.setTitle(BUNDLE.getBundle().getString("dialog.endGameTitle"));
        ButtonType type = new ButtonType(BUNDLE.getBundle().getString("dialog.ok"), ButtonBar.ButtonData.OK_DONE);
        ButtonType newGame = new ButtonType(BUNDLE.getBundle().getString("ui.menu.new"), ButtonBar.ButtonData.APPLY);

        if(gameSymbol == GameStatus.DRAW) {
            end.setContentText(BUNDLE.getBundle().getString("dialog.endGame"));
        } else {
            switch (GameController.getInstance().getPlayerTurn().getSymbol()) {
                case CROSS -> end.setContentText(BUNDLE.getBundle().getString("dialog.endGameUser") + TicTacToeInteraction.getUser().getSymbol());
                case CIRCLE -> end.setContentText(BUNDLE.getBundle().getString("dialog.endGameAI") + TicTacToeInteraction.getAi().getSymbol());
            }
        }

        end.getDialogPane().getButtonTypes().add(type);
        end.getDialogPane().getButtonTypes().add(newGame);
        Optional<ButtonType> result = end.showAndWait();

        //reset del gioco
        //g.resetGame();
    }

    public static void gameNotStartedDialog() {
        Dialog<String> gameNotStarted = new Dialog<String>();
        gameNotStarted.setTitle(BUNDLE.getBundle().getString("dialog.gameNotStartedTitle"));
        ButtonType type = new ButtonType(BUNDLE.getBundle().getString("dialog.ok"), ButtonBar.ButtonData.OK_DONE);

        gameNotStarted.setContentText(BUNDLE.getBundle().getString("dialog.gameNotStarted"));
        gameNotStarted.getDialogPane().getButtonTypes().add(type);
        gameNotStarted.showAndWait();
    }

    public boolean gameInProgressDialog(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Gioco in corso");
        alert.setHeaderText("");
        alert.setContentText("Vuoi interrompere il gioco per iniziarne uno nuovo?");
        ButtonType ok = new ButtonType(BUNDLE.getBundle().getString("dialog.yes"), ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType(BUNDLE.getBundle().getString("dialog.no"), ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(ok, cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ok) return true;
        return false;
    }

    public void startGameDialog() {
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
        //se ho tempo, posso aggiungere scelta di difficolta dell'AI
        Dialog<ButtonType> startGameDialog = new Dialog<ButtonType>();
        startGameDialog.setTitle(BUNDLE.getBundle().getString("dialog.gameStarted"));
        ButtonType type = new ButtonType(BUNDLE.getBundle().getString("dialog.ok"), ButtonBar.ButtonData.OK_DONE);

        //startGameDialog.setContentText("Giocherai contro un AI implementata da me!");
        startGameDialog.getDialogPane().getButtonTypes().add(type);
        Optional<ButtonType> bt = startGameDialog.showAndWait();
    }

    public void repeatMoveDialog(){
        Dialog<String> repeat = new Dialog<String>();
        repeat.setTitle(BUNDLE.getBundle().getString("dialog.repeatMoveTitle"));
        ButtonType type = new ButtonType(BUNDLE.getBundle().getString("dialog.ok"), ButtonBar.ButtonData.OK_DONE);
        repeat.setContentText(BUNDLE.getBundle().getString("dialog.repeatMove"));
        repeat.getDialogPane().getButtonTypes().add(type);
        repeat.showAndWait();
    }

    public GameStatus play(Button b) {
        String id = b.getId();
        int x = id.charAt(1)-48;
        int y = id.charAt(2)-48;

        GameStatus gameStatus = null;

        if (GameController.getInstance().isFirstPlayerTurn()) {
            gameStatus = GameController.getInstance().play(x, y);

            if (gameStatus != GameStatus.REPEAT) {
                b.setText(String.valueOf(TicTacToeInteraction.getUser().getSymbol()));
            }
        }

        return gameStatus;
    }
}
