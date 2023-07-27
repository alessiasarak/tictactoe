package ch.supsi.tictactoe.backend.model;

import ch.supsi.tictactoe.backend.model.enumList.GameName;
import ch.supsi.tictactoe.backend.model.enumList.Symbol;

public interface GameLogic {
    BoardModel getBoard();
    boolean checkRules(int x, int y, Player player);
    boolean checkWinningCombination(Player player);
    GameName getName();
    PlayerAI getPlayerAI();
    void setBoard(Symbol[][] boardModel);
}
