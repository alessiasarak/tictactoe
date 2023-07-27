package ch.supsi.tictactoe.backend.model;

import ch.supsi.tictactoe.backend.model.enumList.Symbol;

public class BoardModel {
    private final int widthBoard;//larghezza, x
    private final int heightBoard;//altezza, y
    private Symbol[][] board;
    private GameLogic gameLogic;

    public BoardModel(int widthBoard, int heightBoard, GameLogic gameLogic){
        this.widthBoard = widthBoard;
        this.heightBoard = heightBoard;
        this.gameLogic = gameLogic;
        this.board = new Symbol[heightBoard][widthBoard];

        for(int i = 0; i < heightBoard; i++){
            for(int j = 0; j < widthBoard; j++){
                this.board[i][j] = Symbol.EMPTY;
            }
        }
    }

    public boolean isFull(){
        for(int i = 0; i < heightBoard; i++){
            for(int j = 0; j < widthBoard; j++){
                if(board[i][j] == Symbol.EMPTY) return false;
            }
        }
        return true;
    }

    public boolean move(int x, int y, Player player, GameLogic gameLogic){
        if(checkMove(x, y)) {
            return gameLogic.checkRules(x, y, player);
        } else return false;
    }

    public void reset() {
        for(int i = 0; i < heightBoard; i++){
            for(int j = 0; j < widthBoard; j++){
                board[i][j] = Symbol.EMPTY;
            }
        }
    }

    public Symbol[][] getBoard() {
        return board;
    }

    public void setBoard(Symbol[][] board) {
        this.board = board;
    }

    public int getWidthBoard() {
        return this.widthBoard;
    }

    public int getHeightBoard() {
        return this.heightBoard;
    }
    private boolean checkMove(int x, int y) {
        return board[x][y] == Symbol.EMPTY;
    }

    public void setPosition(int x, int y, Player player){
        board[x][y] = player.getSymbol();
    }
}
