package ch.supsi.tictactoe.backend.repository;

import ch.supsi.tictactoe.backend.model.GameModel;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public interface GameRepository {
    File DEFAULT_PATH = GameRepository.getDefaultSaveFolder();
    File DEFAULT_FILE = new File(DEFAULT_PATH + System.getProperty("file.separator") + "default.json");

    void saveData(GameModel gameModel, File file);
    GameModel loadData(File file);
    File getFile();
    void setFile(File file);
    private static File getDefaultSaveFolder() {
        FileSystem fileSystem = FileSystems.getDefault();
        Path documentsPath = fileSystem.getPath(System.getProperty("user.home"), "Documents");
        File saveFolder = new File(documentsPath.toFile(), "TicTacToe");
        if (!saveFolder.exists()) {
            try {
                Files.createDirectory(saveFolder.toPath());
            } catch (Exception e) {
                System.out.println("Errore durante la creazione della cartella di salvataggio: " + e.getMessage());
            }
        }
        return saveFolder;
    }
}
