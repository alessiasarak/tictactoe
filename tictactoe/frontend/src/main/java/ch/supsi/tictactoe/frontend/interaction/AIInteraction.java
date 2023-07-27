package ch.supsi.tictactoe.frontend.interaction;

import ch.supsi.tictactoe.backend.model.PlayerAI;
import ch.supsi.tictactoe.backend.model.enumList.GameStatus;
import javafx.application.Platform;

import ch.supsi.tictactoe.backend.controller.GameController;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class AIInteraction extends TicTacToeInteraction{
    private final TicTacToeInteraction playerController;
    private PlayerAI playerAi;
    private final GameInteraction gameInteraction;

    public AIInteraction(TicTacToeInteraction playerController, Pane pane) {
        this.playerController = playerController;
        this.playerAi = GameController.getInstance().getPlayerAI();
        this.gameInteraction = new GameInteraction(pane);
    }

    public void play(){
        this.playerAi = GameController.getInstance().getPlayerAI();
        GameStatus symbolGame;
        do{
            symbolGame = GameController.getInstance().play(playerAi.getX(), playerAi.getY());
        } while(symbolGame == GameStatus.REPEAT);

        //System.out.println(playerAi);
        playerController.getRightButton(playerAi.getX(), playerAi.getY());

        Platform.runLater(() -> {
            Button aiButton = playerController.getAiButton();
            aiButton.setText(String.valueOf(ai));
            aiButton.requestLayout();  // Aggiorna il layout dell'interfaccia utente
        });

        if(symbolGame == GameStatus.DRAW || symbolGame == GameStatus.END) {
            GameStatus finalSymbolGame = symbolGame;
            Platform.runLater(() -> {
                this.gameInteraction.endGameDialog(finalSymbolGame);
                isGameStarted = false;
            });
        }
    }
}

