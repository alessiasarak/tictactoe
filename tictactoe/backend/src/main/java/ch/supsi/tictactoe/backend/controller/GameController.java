package ch.supsi.tictactoe.backend.controller;

import ch.supsi.tictactoe.backend.model.*;
import ch.supsi.tictactoe.backend.model.GameLogic;
import ch.supsi.tictactoe.backend.model.GameModel;
import ch.supsi.tictactoe.backend.model.enumList.GameStatus;
import ch.supsi.tictactoe.backend.model.Player;
import ch.supsi.tictactoe.backend.model.enumList.Symbol;
import ch.supsi.tictactoe.backend.service.GameService;

import java.io.File;

public class GameController {
    private static final GameController gameController = new GameController();
    private final GameService gameService = new GameService();

    public void newGame(){
        gameService.resetGame();
        gameService.newGame();
    }

    public GameStatus play(int x, int y){
        return gameService.play(x, y);
    }

    public static GameController getInstance() {
        return gameController;
    }

    public void save(File file){
        gameService.save(file);
    }

    public boolean isFirstPlayerTurn(){
        return gameService.getPlayerTurn().getSymbol() == Symbol.CROSS;
    }

    public Player getPlayerTurn() {
        return gameService.getPlayerTurn();
    }

    public File getFile() {
        return gameService.getFile();
    }

    public void loadGame(File file) {
        gameService.loadGame(file);
    }

    public BoardModel getBoard() {
        return gameService.getBoard();
    }

    public PlayerAI getPlayerAI() {
        return gameService.getPlayerAI();
    }

    public GameStatus getGameStatus() {
        return this.gameService.getGameStatus();
    }

    public void initialize(GameLogic gameLogic) {
        gameService.initialize(gameLogic);
    }
}
