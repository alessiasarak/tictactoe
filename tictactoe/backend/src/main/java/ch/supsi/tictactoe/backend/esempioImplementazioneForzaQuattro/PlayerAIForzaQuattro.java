package ch.supsi.tictactoe.backend.esempioImplementazioneForzaQuattro;

import ch.supsi.tictactoe.backend.model.*;
import ch.supsi.tictactoe.backend.model.enumList.Symbol;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Random;

public class PlayerAIForzaQuattro implements PlayerAI {
    private int x;
    private int y;
    private final Symbol symbol = Symbol.CIRCLE;
    @JsonIgnore
    private Player nextPlayer;
    private GameLogic gameLogic;

    public PlayerAIForzaQuattro() {}

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public boolean playMove(int x, int y){
        easy();
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
    public void easy() {
        Random random = new Random();
        y = random.nextInt(0, this.gameLogic.getBoard().getWidthBoard());
    }

    @Override
    public void medium(BoardModel boardModel) {

    }

    @Override
    public void hard() {

    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return y;
    }
}
