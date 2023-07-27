package ch.supsi.tictactoe.backend.service;

import ch.supsi.tictactoe.backend.model.*;
import ch.supsi.tictactoe.backend.model.enumList.GameStatus;
import ch.supsi.tictactoe.backend.model.enumList.Symbol;

import java.io.File;

public class GameService {
    private GameModel gameModel;
    private boolean validMove = false;
    private boolean finishGame = false;

    public void initialize(GameLogic gameLogic) {
        gameModel = new GameModel(gameLogic);
    }

    public void newGame() {
        gameModel.newGame();
        finishGame = false;
    }
    public void save(File file) {
        gameModel.save(file);
    }

    public GameStatus play(int x, int y){
        validMove = gameModel.playMove(x, y);

        if(!validMove) return GameStatus.REPEAT;

        //controlla se qualcuno ha vinto
        if(gameModel.checkWinningCombination(gameModel.getPlayerTurn())) finishGame = true;

        //se il gioco è finito con un vincitore
        if(finishGame) {
            return GameStatus.END;
        }

        //se il gioco è finito alla pari
        if(gameModel.getBoard().isFull()) {
            finishGame = true;
            return GameStatus.DRAW;
        }

        if(validMove) gameModel.nextPlayerTurn();

        return GameStatus.IN_PROGRESS;
    }

    public File getFile() {
        return gameModel.getFile();
    }

    public void loadGame(File file) {
        gameModel = gameModel.loadGame(file);
        if(gameModel.getGameStatus() == GameStatus.IN_PROGRESS) finishGame = false;
    }

    public BoardModel getBoard() {
        return gameModel.getBoard();
    }

    public PlayerAI getPlayerAI() {
        return gameModel.getPlayerAI();
    }

    public void resetGame() {
        finishGame = false;
        gameModel.setPlayerTurn(gameModel.getPlayerUser());
        validMove = false;
        gameModel.getBoard().reset();
        gameModel.setGameSatus(GameStatus.END);
    }

    public GameStatus getGameStatus() {
        return this.gameModel.getGameStatus();
    }

    public Player getPlayerTurn() {
        return gameModel.getPlayerTurn();
    }
}
