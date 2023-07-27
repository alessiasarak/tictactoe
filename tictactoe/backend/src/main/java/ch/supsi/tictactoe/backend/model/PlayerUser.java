package ch.supsi.tictactoe.backend.model;

import ch.supsi.tictactoe.backend.model.enumList.Symbol;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PlayerUser implements Player {
    private final Symbol symbol;
    @JsonIgnore
    private Player nextPlayer;
    private final GameLogic gameLogic;

    public PlayerUser(GameModel gameModel) {
        this.symbol = Symbol.CROSS;
        this.gameLogic = gameModel.getGameLogic();
    }

    @Override
    public boolean playMove(int x, int y) {
        return gameLogic.getBoard().move(x, y, this, gameLogic);
    }

    @Override
    public Player getNextPlayer() {
        return this.nextPlayer;
    }

    @Override
    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    @Override
    public void setGameLogic(GameLogic gameLogic) {
        return;
    }

    @Override
    public Symbol getSymbol() {
        return this.symbol;
    }
}
