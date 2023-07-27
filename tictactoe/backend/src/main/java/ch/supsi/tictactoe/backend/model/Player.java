package ch.supsi.tictactoe.backend.model;

import ch.supsi.tictactoe.backend.model.enumList.Symbol;

public interface Player {
    boolean playMove(int x, int y);
    Player getNextPlayer();
    Symbol getSymbol();
    void setNextPlayer(Player nextPlayer);

    void setGameLogic(GameLogic gameLogic);
}
