package ch.supsi.tictactoe.backend.model;

import ch.supsi.tictactoe.backend.model.enumList.Symbol;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Random;

public class PlayerAITicTacToe implements PlayerAI {
    private int x;
    private int y;
    private final Symbol symbol = Symbol.CIRCLE;
    @JsonIgnore
    private Player nextPlayer;
    private GameLogic gameLogic;

    public PlayerAITicTacToe() {}

    @Override
    public boolean playMove(int x, int y){
        medium(gameLogic.getBoard());
        return gameLogic.getBoard().move(this.x, this.y, this, gameLogic);
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
    public Symbol getSymbol() {
        return this.symbol;
    }

    @Override
    public void easy(){
        Random random = new Random();
        x = random.nextInt(0, this.gameLogic.getBoard().getHeightBoard());
        y = random.nextInt(0, this.gameLogic.getBoard().getWidthBoard());
    }

    //controllo se vince
    //controllo se può stoppare l'avversario
    public void medium(BoardModel boardModel) {
        Symbol[][] b = boardModel.getBoard();

        //controllo righe, se vince
        if(checkRowCombination(b, Symbol.CIRCLE)) return;
        //controllo righe, se deve stoppare l'altro
        if(checkRowCombination(b, Symbol.CROSS)) return;

        //controllo colonne
        if(checkColCombination(b, Symbol.CIRCLE)) return;
        if(checkColCombination(b, Symbol.CROSS)) return;

        //controllo diagonali
        if(checkDiagonalCombination(b, Symbol.CIRCLE)) return;
        if(checkDiagonalCombination(b, Symbol.CROSS)) return;

        easy();
    }

    private boolean checkColCombination(Symbol[][] board, Symbol winningPlayer) {
        for(int col = 0; col < this.gameLogic.getBoard().getWidthBoard(); col++) {
            if (board[0][col] == winningPlayer && board[1][col] == winningPlayer && board[2][col] == Symbol.EMPTY) {
                x = 2;
                y = col;
                return true;
            }

            if (board[1][col] == winningPlayer && board[2][col] == winningPlayer && board[0][col] == Symbol.EMPTY) {
                x = 0;
                y = col;
                return true;
            }

            if (board[0][col] == winningPlayer && board[2][col] == winningPlayer && board[1][col] == Symbol.EMPTY) {
                x = 1;
                y = col;
                return true;
            }
        }
        return false;
    }

    private boolean checkRowCombination(Symbol[][] board, Symbol winningPlayer){
        for(int row = 0; row < this.gameLogic.getBoard().getHeightBoard(); row++) {
            if (board[row][0] == winningPlayer && board[row][1] == winningPlayer && board[row][2] == Symbol.EMPTY) {
                x = row;
                y = 2;
                return true;
            }

            if (board[row][1] == winningPlayer && board[row][2] == winningPlayer && board[row][0] == Symbol.EMPTY) {
                x = row;
                y = 0;
                return true;
            }

            if (board[row][0] == winningPlayer && board[row][2] == winningPlayer && board[row][1] == Symbol.EMPTY) {
                x = row;
                y = 1;
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalCombination(Symbol[][] board, Symbol winningPlayer){
        if(((board[0][0] == winningPlayer && board[2][2] == winningPlayer) ||
                (board[0][2] == winningPlayer && board[2][0] == winningPlayer)) &&
                board[1][1] == Symbol.EMPTY){
            x = 1;
            y = 1;
            return true;
        }

        if(board[0][0] == winningPlayer && board[1][1] == winningPlayer && board[2][2] == Symbol.EMPTY){
            x = 2;
            y = 2;
            return true;
        }
        if(board[1][1] == winningPlayer && board[2][2] == winningPlayer && board[0][0] == Symbol.EMPTY){
            x = 0;
            y = 0;
            return true;
        }

        if(board[0][2] == winningPlayer && board[1][1] == winningPlayer && board[2][0] == Symbol.EMPTY){
            x = 2;
            y = 0;
            return true;
        }

        if(board[1][1] == winningPlayer && board[2][0] == winningPlayer && board[0][2] == Symbol.EMPTY){
            x = 0;
            y = 2;
            return true;
        }
        return false;
    }

    @Override
    public void hard(){}

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
}
