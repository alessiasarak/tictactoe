package ch.supsi.tictactoe.backend.repository;

import java.util.Locale;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class PreferenceRepository {
    private final Preferences preferences;
    private final String keyColor = "my-color";
    private final String keySymbol = "my-symbol";
    private final String keyLanguage = "my-language";

    public PreferenceRepository() {
        preferences = Preferences.userRoot();
    }

    public String getKeyColor() {
        return keyColor;
    }

    public String getKeySymbol() {
        return keySymbol;
    }

    public String getKeyLanguage() {
        return keyLanguage;
    }

    public void putColor(String color){
        preferences.put(keyColor, color);
    }

    public void putLanguage(String language){
        preferences.put(keyLanguage, language);
    }

    public void putSymbol(String symbol) {
        preferences.put(keySymbol, symbol);
    }

    public String getColor() {
        return preferences.get(keyColor, null);
    }

    public String getSymbol() {
        return preferences.get(keySymbol, null);
    }

    public String getLanguage() {
        return preferences.get(keyLanguage, null);
    }

    public boolean existsPreferences() {
        try {
            String[] keys = preferences.keys();
            if(keys.length == 4) return true;
        } catch (BackingStoreException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void saveDefaultPreferences() {
        preferences.put(keyColor, "default");
        preferences.put(keySymbol, "X,O");
        preferences.put(keyLanguage, Locale.getDefault().toString());
    }
}
