package ch.supsi.tictactoe.backend.esempioImplementazioneForzaQuattro;

import ch.supsi.tictactoe.backend.model.*;
import ch.supsi.tictactoe.backend.model.enumList.GameName;
import ch.supsi.tictactoe.backend.model.enumList.Symbol;

public class ForzaQuattro implements GameLogic {
    private final PlayerAI playerAI = new PlayerAIForzaQuattro();
    private BoardModel boardModel = new BoardModel(6, 7, this);
    @Override
    public BoardModel getBoard() {
        return boardModel;
    }

    @Override
    public boolean checkWinningCombination(Player player) {
        //per prova, solo controllo vincita delle righe e colonne
        if(checkColCombination(player)) return true;
        return checkRowCombination(player);
    }

    boolean checkRowCombination(Player player){
        int n = 0;
        for(int i = 0; i < this.getBoard().getHeightBoard(); i++){
            for(int j = 0; j < this.getBoard().getWidthBoard(); j++){
                if(player.getSymbol() == boardModel.getBoard()[i][j]) n++;
                if(n == 4) return true;
            }
            n = 0;
        }
        return false;
    }

    boolean checkColCombination(Player player){
        int n = 0;
        for(int i = 0; i < this.getBoard().getWidthBoard(); i++){
            for(int j = 0; j < this.getBoard().getHeightBoard(); j++){
                if(player.getSymbol() == boardModel.getBoard()[j][i]) n++;
                if(n == 4) return true;
            }
            n = 0;
        }
        return false;
    }

    @Override
    public void setBoard(Symbol[][] boardModel) {
        this.boardModel.setBoard(boardModel);
    }

    @Override
    public GameName getName() {
        return GameName.FORZA_QUATTRO;
    }

    @Override
    public boolean checkRules(int x, int y, Player player) {
        //avendo limitazioni, controlla se la colonna data, è libera
        for(int i = getBoard().getHeightBoard()-1; i >= 0; i--){
            if(getBoard().getBoard()[i][y] == Symbol.EMPTY) {
                this.getBoard().setPosition(i, y, player);
                return true;
            }
        }
        return false;
    }

    @Override
    public PlayerAI getPlayerAI() {
        return this.playerAI;
    }
}
