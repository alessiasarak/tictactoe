package ch.supsi.tictactoe.backend.model;

public interface PlayerAI extends Player{
    void easy();
    void medium(BoardModel boardModel);
    void hard();
    int getX();
    int getY();
}
