package ch.supsi.tictactoe.backend.model;

import ch.supsi.tictactoe.backend.controller.GameController;
import ch.supsi.tictactoe.backend.model.enumList.GameName;
import ch.supsi.tictactoe.backend.model.enumList.GameStatus;
import ch.supsi.tictactoe.backend.model.enumList.Symbol;
import ch.supsi.tictactoe.backend.repository.GameJsonRepository;
import ch.supsi.tictactoe.backend.repository.GameRepository;

import java.io.File;

import static ch.supsi.tictactoe.backend.model.enumList.GameName.TICTACTOE;

public class GameModel {
    private Player playerTurn;
    private GameStatus gameStatus;
    private final Player playerUser;
    private Player playerAI;
    private final GameLogic gameLogic;
    private final GameRepository gameRepository = new GameJsonRepository();

    public GameModel(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
        this.playerUser = new PlayerUser(this);
        this.playerAI = gameLogic.getPlayerAI();
        this.playerAI.setGameLogic(gameLogic);
        this.playerTurn = this.playerUser;

        this.playerUser.setNextPlayer(this.playerAI);
        this.playerAI.setNextPlayer(this.playerUser);
    }

    public GameModel(Symbol playerTurn, GameName gameLogic, GameStatus gameStatus, Symbol[][] board) {
        this.gameStatus = gameStatus;

        switch (gameLogic){
            case TICTACTOE :
                this.gameLogic = new TicTacToe();

                this.playerAI = this.gameLogic.getPlayerAI();
                this.playerAI.setGameLogic(this.gameLogic);
                this.gameLogic.setBoard(board);
                break;
            default :
                this.gameLogic = null;
        }

        this.playerUser = new PlayerUser(this);

        switch (playerTurn){
            case CROSS -> this.playerTurn = this.playerUser;
            case CIRCLE -> this.playerTurn = this.playerAI;
        }

        this.playerUser.setNextPlayer(this.playerAI);
        this.playerAI.setNextPlayer(this.playerUser);
    }

    public void save(File file) {
        gameRepository.saveData(this, file);
    }
    public boolean checkWinningCombination(Player playerTurn) {
        return this.gameLogic.checkWinningCombination(playerTurn);
    }
    public void nextPlayerTurn() {
        this.playerTurn = playerTurn.getNextPlayer();
    }
    public boolean playMove(int x, int y) {
        return this.playerTurn.playMove(x, y);
    }

    public File getFile() {
        return gameRepository.getFile();
    }

    public GameModel loadGame(File file) {
        return gameRepository.loadData(file);
    }

    public BoardModel getBoard(){
        return this.gameLogic.getBoard();
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getPlayerUser() {
        return playerUser;
    }

    public PlayerAI getPlayerAI() {
        return (PlayerAI) playerAI;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setGameSatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void newGame() {
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.playerTurn = this.playerUser;
    }
}