package ch.supsi.tictactoe.frontend.interaction;

import ch.supsi.tictactoe.backend.controller.GameController;
import ch.supsi.tictactoe.backend.controller.PreferenceController;
import ch.supsi.tictactoe.backend.model.enumList.GameStatus;
import ch.supsi.tictactoe.backend.model.TicTacToe;
import ch.supsi.tictactoe.backend.model.enumList.UserSymbol;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class TicTacToeInteraction implements Initializable {
    public MenuItem miNew, miOpen, miSave, miSaveAs, miQuit, miSymbols, miLanguage, miAbout;
    public Button b00, b01, b02, b10, b11, b12, b20, b21, b22;
    public Pane pane;

    public static boolean isGameStarted = false;
    private Button aiButton;
    public Button[] buttons;
    private static UserSymbol user;
    public static UserSymbol ai;
    AIInteraction aiInteraction;
    FileInteraction fileInteraction;
    GameInteraction gameInteraction;
    LanguagePreferenceInteraction languagePreferenceInteraction;
    StylePreferenceInteraction stylePreferenceInteraction;
    MainInteraction mainInteraction;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //inizializzo gli interaction
        GameController.getInstance().initialize(new TicTacToe());

        this.aiInteraction = new AIInteraction(this, pane);
        this.fileInteraction = new FileInteraction(pane);
        this.gameInteraction = new GameInteraction(pane);
        this.languagePreferenceInteraction = new LanguagePreferenceInteraction();
        this.stylePreferenceInteraction = new StylePreferenceInteraction();
        this.mainInteraction = new MainInteraction(pane);

        Map<String, String> preferences = new PreferenceController().getPrefereces();

        setUser(UserSymbol.getSymbolFromChar(preferences.get("my-symbol").charAt(0)));
        setAi(UserSymbol.getSymbolFromChar(preferences.get("my-symbol").charAt(2)));

        this.stylePreferenceInteraction.setColor(pane, preferences.get("my-color"));

        buttons = new Button[]{
                b00, b01, b02,
                b10, b11, b12,
                b20, b21, b22
        };

        for(Button b : buttons) b.setText("");
    }

    @FXML
    public void newGame(ActionEvent e) {
        //controllo se c'è già stata una partita precedente
        if(GameController.getInstance().getGameStatus() == GameStatus.IN_PROGRESS){
            //se si chiedo conferma se si vuole iniziare una partita nuova
            if(this.gameInteraction.gameInProgressDialog()){
                this.gameInteraction.startGameDialog();
                GameController.getInstance().newGame();
                for(Button b : buttons) b.setText("");

                isGameStarted = true;
            }
            return;
        }

        GameController.getInstance().newGame();
        for(Button b : buttons) b.setText("");
        isGameStarted = true;
    }

    @FXML
    public void openGame(ActionEvent e) {
        //controllo se c'è già stata una partita precedente
        if(GameController.getInstance().getGameStatus() == GameStatus.IN_PROGRESS){
            //se si chiedo conferma se si vuole iniziare una partita nuova
            if(this.gameInteraction.gameInProgressDialog()){
                this.fileInteraction.openGame(buttons);
            }
        }else this.fileInteraction.openGame(buttons);
    }

    @FXML
    public void saveGame(ActionEvent e) {
        this.fileInteraction.saveGameDialog();
    }

    @FXML
    public void saveGameAs(ActionEvent e) {
        this.fileInteraction.saveGameAsDialog();
    }

    @FXML
    public void quit(ActionEvent e) {
        this.mainInteraction.quitDialogDef();
    }

    @FXML
    public void editSymbols(ActionEvent e) {
        this.stylePreferenceInteraction.setPreferences(buttons, pane);
    }

    @FXML
    public void editLanguage(ActionEvent e) {
        this.languagePreferenceInteraction.setLanguage();
    }

    @FXML
    public void showAbout(ActionEvent e) {
        this.mainInteraction.aboutDialog();
    }

    @FXML
    public void playerMove(ActionEvent e) {
        if(isGameStarted){
            Button b = (Button) e.getSource();
            GameStatus gameStatus = this.gameInteraction.play(b);

            if(gameStatus == GameStatus.REPEAT) this.gameInteraction.repeatMoveDialog();
            else if(gameStatus == GameStatus.END || gameStatus == GameStatus.DRAW) {
                this.gameInteraction.endGameDialog(gameStatus);
                isGameStarted = false;
            }else this.aiInteraction.play();

        } else GameInteraction.gameNotStartedDialog();
    }

    public static UserSymbol getUser() {
        return user;
    }

    public static UserSymbol getAi() {
        return ai;
    }

    public static void setUser(UserSymbol user) {
        TicTacToeInteraction.user = user;
    }

    public static void setAi(UserSymbol ai) {
        TicTacToeInteraction.ai = ai;
    }


    public void getRightButton(int x, int y){
        for(Button button : buttons){
            if(button.getId().charAt(1)-48 == x && button.getId().charAt(2)-48 == y){
                this.aiButton = button;
            }
        }
    }

    public Button getAiButton() {
        return aiButton;
    }

}
