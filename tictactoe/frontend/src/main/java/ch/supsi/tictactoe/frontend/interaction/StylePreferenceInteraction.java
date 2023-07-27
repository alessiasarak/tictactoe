package ch.supsi.tictactoe.frontend.interaction;

import ch.supsi.tictactoe.backend.controller.PreferenceController;
import ch.supsi.tictactoe.backend.model.enumList.UserSymbol;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.*;

import static ch.supsi.tictactoe.backend.model.enumList.Resource.BUNDLE;

public class StylePreferenceInteraction {
    public void setPreferences(Button[] buttons, Pane pane){
        //dialog personalizzato
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle(BUNDLE.getBundle().getString("dialog.preferencesTitle"));
        ButtonType okButton = new ButtonType(BUNDLE.getBundle().getString("dialog.ok"), ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, ButtonType.CANCEL);

        ComboBox<String> colorComboBox = new ComboBox<>();

        //lista dei nomi dei file presenti sono style
        ArrayList<String> colors = getColors();

        colorComboBox.getItems().addAll(colors);
        //System.out.println(colors.size());
        colorComboBox.setValue(colors.get(0));
        HBox colorBox = new HBox(new Label(BUNDLE.getBundle().getString("dialog.color")), colorComboBox);

        // creazione dei componenti per la selezione del carattere
        ComboBox<String> symbolComboBox = new ComboBox<>();

        UserSymbol[] userSymbols = UserSymbol.getSymbols();
        for(int i = 0; i < UserSymbol.values().length; i += 2){
            symbolComboBox.getItems().add(userSymbols[i] + "," + userSymbols[i+1]);
        }

        symbolComboBox.setValue(userSymbols[0] + "," + userSymbols[1]);

        HBox charBox = new HBox(new Label(BUNDLE.getBundle().getString("dialog.symbols")), symbolComboBox);

        // aggiunta componenti
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(colorBox, 0, 0);
        gridPane.add(charBox, 0, 1);
        dialog.getDialogPane().setContent(gridPane);

        // risultato
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                return new Pair<>(colorComboBox.getValue(), symbolComboBox.getValue());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        // fai qualcosa con i valori selezionati
        if (result.isPresent()) {
            Pair<String, String> selectedValues = result.get();
            String color = selectedValues.getKey();
            String symbol = selectedValues.getValue();

            setColor(pane, color);
            setSymbols(buttons, symbol);

            //salvo le preferenze
            new PreferenceController().saveSymbol(symbol);
        }
    }

    private ArrayList<String> getColors() {
        Set<String> keys = BUNDLE.getBundle().keySet();

        ArrayList<String> styleValues = new ArrayList<>();
        String prefix = "style.";
        for (String key : keys) {
            if (key.startsWith(prefix)) {
                String value = BUNDLE.getBundle().getString(key);
                styleValues.add(value);
            }
        }

        return styleValues;
    }

    public void setColor(Pane pane, String result){
        Set<String> keys = BUNDLE.getBundle().keySet();
        String prefix = "style.";
        List<String> propertyNames = new ArrayList<>();
        String res = "";
        for (String key : keys) {
            if (key.startsWith(prefix) && BUNDLE.getBundle().getString(key).equals(result)) {
                String propertyNameWithoutPrefix = key.substring(prefix.length());
                propertyNames.add(propertyNameWithoutPrefix);
                res = key.substring(prefix.length());

                String newStyle = "/ch/supsi/tictactoe/frontend/style/" + res + ".css";
                pane.getStylesheets().clear();
                pane.getStylesheets().add(newStyle);

                new PreferenceController().saveColor(res);
                return;
            }
        }

        String newStyle = "/ch/supsi/tictactoe/frontend/style/" + result + ".css";
        pane.getStylesheets().clear();
        pane.getStylesheets().add(newStyle);
    }

    private void setSymbols(Button[] buttons, String result){
        UserSymbol userSymbol = UserSymbol.getSymbolFromChar(result.charAt(0));
        UserSymbol aiSymbol = UserSymbol.getSymbolFromChar(result.charAt(2));

        for(int i = 0; i < 9; i++){
            if(buttons[i].getText().length() != 0){
                if (buttons[i].getText().charAt(0) == TicTacToeInteraction.getUser().getSymbol()) {
                    buttons[i].setText(String.valueOf(userSymbol));
                }
                if (buttons[i].getText().charAt(0) == TicTacToeInteraction.getAi().getSymbol()){
                    buttons[i].setText(String.valueOf(aiSymbol));
                }
            }
        }

        TicTacToeInteraction.setUser(userSymbol);
        TicTacToeInteraction.setAi(aiSymbol);
    }
}
