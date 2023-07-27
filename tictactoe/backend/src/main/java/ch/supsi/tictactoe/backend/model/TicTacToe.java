package ch.supsi.tictactoe.backend.model;

import ch.supsi.tictactoe.backend.model.enumList.GameName;
import ch.supsi.tictactoe.backend.model.enumList.Symbol;

public class TicTacToe implements GameLogic {
    private final PlayerAI playerAI = new PlayerAITicTacToe();
    private BoardModel boardModel = new BoardModel(3, 3, this);
    private final GameName gameName = GameName.TICTACTOE;

    @Override
    public boolean checkWinningCombination(Player player){
        //check rows and cols
        for (int i = 0; i < this.boardModel.getWidthBoard(); i++) {
            if (checkRowWin(i, player.getSymbol())) return true;
        }
        for (int i = 0; i < this.boardModel.getHeightBoard(); i++) {
            if(checkColWin(i, player.getSymbol())) return true;
        }
        //check diagonals
        if(checkRightDiagonalWin(player.getSymbol())) return true;
        return checkLeftDiagonalWin(player.getSymbol());
    }

    @Override
    public void setBoard(Symbol[][] board) {
        this.boardModel.setBoard(board);
    }

    @Override
    public GameName getName() {
        return this.gameName;
    }

    @Override
    public boolean checkRules(int x, int y, Player player) {
        //non avendo limitazioni, torna sempre true
        this.boardModel.setPosition(x, y, player);
        return true;
    }

    @Override
    public PlayerAI getPlayerAI() {
        return this.playerAI;
    }

    private boolean checkRowWin(int row, Symbol symbol){
        for(int j = 0; j < this.boardModel.getHeightBoard(); j++){
            if(getBoard().getBoard()[row][j] != symbol) return false;
        }
        return true;
    }

    private boolean checkColWin(int col, Symbol symbol){
        for(int i = 0; i < this.boardModel.getWidthBoard(); i++){
            if(getBoard().getBoard()[i][col] != symbol) return false;
        }
        return true;
    }

    private boolean checkRightDiagonalWin(Symbol symbol){
        for(int i = 0; i < this.boardModel.getHeightBoard(); i++){
            for(int j = 0; j < this.boardModel.getWidthBoard(); j++) {
                if (getBoard().getBoard()[i][j] != symbol) return false;
            }
        }
        return true;
    }

    private boolean checkLeftDiagonalWin(Symbol symbol){
        return getBoard().getBoard()[2][0] == symbol &&
                getBoard().getBoard()[1][1] == symbol &&
                getBoard().getBoard()[0][2] == symbol;
    }

    @Override
    public BoardModel getBoard() {
        return boardModel;
    }
}
