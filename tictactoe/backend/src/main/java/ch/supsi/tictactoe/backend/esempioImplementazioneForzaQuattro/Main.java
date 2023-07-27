package ch.supsi.tictactoe.backend.esempioImplementazioneForzaQuattro;

import ch.supsi.tictactoe.backend.controller.GameController;
import ch.supsi.tictactoe.backend.model.enumList.GameStatus;
import ch.supsi.tictactoe.backend.model.enumList.Symbol;
import ch.supsi.tictactoe.backend.model.enumList.UserSymbol;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameController.getInstance().initialize(new ForzaQuattro());
        GameController.getInstance().newGame();
        GameStatus gameStatus;

       do{
           if(GameController.getInstance().isFirstPlayerTurn()){
               Scanner scanner = new Scanner(System.in);
               System.out.print("colonna in cui inserire: ");
               int y = scanner.nextInt();
               int x = 0;
               gameStatus = GameController.getInstance().play(x, y);
           }else gameStatus = GameController.getInstance().play(0, 0);
           for(int i = 0; i < GameController.getInstance().getBoard().getHeightBoard(); i++){
               for(int j = 0; j < GameController.getInstance().getBoard().getWidthBoard(); j++){
                   switch (GameController.getInstance().getBoard().getBoard()[i][j]){
                       case CIRCLE:
                           System.out.print("O");
                           break;
                       case CROSS:
                           System.out.print("X");
                           break;
                       case EMPTY:
                           System.out.print(" ");
                           break;
                   }
                   System.out.print("|");
               }
               System.out.println();
           }
           System.out.println("------------------------");
        } while(gameStatus == GameStatus.IN_PROGRESS);

       if(gameStatus == GameStatus.END) System.out.println("Ha vinto: " + GameController.getInstance().getPlayerTurn().getSymbol());
    }
}
