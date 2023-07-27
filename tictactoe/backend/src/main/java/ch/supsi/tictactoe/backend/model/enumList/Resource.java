package ch.supsi.tictactoe.backend.model.enumList;

import ch.supsi.tictactoe.backend.controller.PreferenceController;

import java.util.ResourceBundle;

public enum Resource {
    BUNDLE(ResourceBundle.getBundle("ch/supsi/tictactoe/frontend/language_" +
            new PreferenceController().getLanguage()));

    private final ResourceBundle bundle;
    Resource(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }
}
