package ch.supsi.tictactoe.backend.controller;

import ch.supsi.tictactoe.backend.service.PreferenceService;

import java.util.Map;

public class PreferenceController {
    private final PreferenceService preferenceService = new PreferenceService();
    public void saveSymbol(String symbol) {
        preferenceService.saveSymbol(symbol);
    }
    public void saveColor(String color) {
        preferenceService.saveColor(color);
    }
    public void saveLanguage(String language) {
        preferenceService.saveLanguage(language);
    }
    public String getLanguage() {
        return preferenceService.getLanguage();
    }
    public Map<String, String> getPrefereces() {
        return preferenceService.getPrefereces();
    }
}
