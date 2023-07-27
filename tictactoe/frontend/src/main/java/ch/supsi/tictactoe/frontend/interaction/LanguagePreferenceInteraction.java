package ch.supsi.tictactoe.frontend.interaction;

import ch.supsi.tictactoe.backend.controller.PreferenceController;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceDialog;

import java.util.Optional;
import java.util.ResourceBundle;

import static ch.supsi.tictactoe.backend.model.enumList.Resource.BUNDLE;

public class LanguagePreferenceInteraction {
    public void setLanguage() {
        ChoiceDialog<String> choiceDialog = new ChoiceDialog<String>();
        ObservableList<String> list = choiceDialog.getItems();
        choiceDialog.setHeaderText(BUNDLE.getBundle().getString("dialog.languageChoiceTitle"));

        list.add("it_CH");
        list.add("en_US");
        list.add("de_DE");

        Optional<String> result = choiceDialog.showAndWait();
        result.ifPresent(s -> new PreferenceController().saveLanguage(s));
    }

    public static ResourceBundle getLanguage(){
        String language = new PreferenceController().getLanguage();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("ch/supsi/tictactoe/frontend/language_"+language);
        return resourceBundle;
    }
}
