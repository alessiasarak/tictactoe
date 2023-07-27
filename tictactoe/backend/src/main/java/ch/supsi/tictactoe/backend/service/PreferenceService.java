package ch.supsi.tictactoe.backend.service;

import ch.supsi.tictactoe.backend.model.PreferenceModel;

import java.util.HashMap;
import java.util.Map;

public class PreferenceService {
    private final PreferenceModel preferenceModel = new PreferenceModel();

    public Map<String, String> getPrefereces(){
        if(!preferenceModel.existsPreferences()) preferenceModel.saveDefaultPreferences();

        Map<String, String> map = new HashMap<>();
        map.put(preferenceModel.getKeyColor(), preferenceModel.getColor());
        map.put(preferenceModel.getKeySymbol(), preferenceModel.getSymbol());
        map.put(preferenceModel.getKeyLanguage(), preferenceModel.getLanguage());

        return map;
    }

    public void saveColor(String color) {
        preferenceModel.saveColor(color);
    }

    public void saveSymbol(String symbol) {
        preferenceModel.saveSymbol(symbol);
    }

    public void saveLanguage(String language) {
        preferenceModel.saveLanguage(language);
    }

    public String getLanguage() {
        return preferenceModel.getLanguage();
    }
}
