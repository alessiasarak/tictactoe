package ch.supsi.tictactoe.backend.model;

import ch.supsi.tictactoe.backend.repository.PreferenceRepository;

public class PreferenceModel {
    private final PreferenceRepository preferenceRepository = new PreferenceRepository();

    public void saveLanguage(String language){
        preferenceRepository.putLanguage(language);
    }

    public void saveSymbol(String symbol){
        preferenceRepository.putSymbol(symbol);
    }

    public void saveColor(String color) {
        preferenceRepository.putColor(color);
    }

    public String getKeyColor() {
        return preferenceRepository.getKeyColor();
    }

    public String getKeySymbol() {
        return preferenceRepository.getKeySymbol();
    }

    public String getKeyLanguage() {
        return preferenceRepository.getKeyLanguage();
    }

    public String getColor() {
        return preferenceRepository.getColor();
    }

    public String getSymbol() {
        return preferenceRepository.getSymbol();
    }

    public String getLanguage() {
        return preferenceRepository.getLanguage();
    }

    public boolean existsPreferences() {
        return preferenceRepository.existsPreferences();
    }

    public void saveDefaultPreferences() {
        preferenceRepository.saveDefaultPreferences();
    }
}
