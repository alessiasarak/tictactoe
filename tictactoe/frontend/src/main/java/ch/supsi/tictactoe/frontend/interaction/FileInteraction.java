package ch.supsi.tictactoe.frontend.interaction;

import ch.supsi.tictactoe.backend.controller.GameController;
import ch.supsi.tictactoe.backend.model.enumList.GameStatus;
import ch.supsi.tictactoe.backend.model.enumList.Symbol;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;

import static ch.supsi.tictactoe.backend.repository.GameRepository.DEFAULT_FILE;
import static ch.supsi.tictactoe.backend.repository.GameRepository.DEFAULT_PATH;

import static ch.supsi.tictactoe.backend.model.enumList.Resource.BUNDLE;

public class FileInteraction {
    private final Pane pane;

    public FileInteraction(Pane pane) {
        this.pane = pane;
    }

    public void saveGameAsDialog() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(BUNDLE.getBundle().getString("dialog.saveGameTitle"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File JSON", "*.json"));
        fileChooser.setInitialDirectory(DEFAULT_PATH);
        File file = fileChooser.showSaveDialog(pane.getScene().getWindow());

        if(file != null) {
            GameController.getInstance().save(file);
        }
    }

    public void saveGameDialog() {
        File file = GameController.getInstance().getFile();
        if(file != null){
            if(file == DEFAULT_FILE) this.saveGameAsDialog();
            GameController.getInstance().save(file);
        }
    }

    public void openGame(Button[] buttons) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(BUNDLE.getBundle().getString("dialog.loadDataTitle"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("File JSON", "*.json"));
        fileChooser.setInitialDirectory(DEFAULT_PATH);
        File file = fileChooser.showOpenDialog(pane.getScene().getWindow());

        if (file != null) {
            GameController.getInstance().loadGame(file);
            Symbol[][] board = GameController.getInstance().getBoard().getBoard();

            for(Button b : buttons){
                int x = b.getId().charAt(1)-48;
                int y = b.getId().charAt(2)-48;
                Symbol c = board[x][y];

                switch (c){
                    case CROSS -> b.setText(String.valueOf(TicTacToeInteraction.getUser()));
                    case CIRCLE -> b.setText(String.valueOf(TicTacToeInteraction.getAi()));
                    case EMPTY -> b.setText("");
                }
            }
            GameStatus gameStatus = GameController.getInstance().getGameStatus();
            //System.out.println(gameStatus);

            if(gameStatus == GameStatus.IN_PROGRESS){
                TicTacToeInteraction.isGameStarted = true;
            }
        }
    }
}
