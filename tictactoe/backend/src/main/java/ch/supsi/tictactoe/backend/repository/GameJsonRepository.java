package ch.supsi.tictactoe.backend.repository;

import ch.supsi.tictactoe.backend.deserializer.GameModelDeserializer;
import ch.supsi.tictactoe.backend.model.GameModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class GameJsonRepository implements GameRepository{
    private File file = DEFAULT_FILE;

    @Override
    public GameModel loadData(File file) {
        try {
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            SimpleModule module = new SimpleModule();
            module.addDeserializer(GameModel.class, new GameModelDeserializer());
            mapper.registerModule(module);
            String json = new String(Files.readAllBytes(file.toPath()));
            return mapper.readValue(json, GameModel.class);
        } catch (IOException e) {
            System.out.println("Errore durante il caricamento del file JSON: " + e.getMessage());
            return null;
        }
    }

    @Override
    public File getFile() {
        return file;
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void saveData(@JsonProperty("game") GameModel game, File file) {
        setFile(file);
        try {
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            ObjectNode gameNode = mapper.createObjectNode();
            gameNode.putPOJO("playerTurnSymbol", game.getPlayerTurn().getSymbol());
            gameNode.putPOJO("gameStatus", game.getGameStatus());
            gameNode.putPOJO("playerUserSymbol", game.getPlayerUser().getSymbol());
            gameNode.putPOJO("playerAISymbol", game.getPlayerAI().getSymbol());
            gameNode.putPOJO("board", game.getBoard().getBoard());

            gameNode.putPOJO("gameLogicName", game.getGameLogic().getName());

            String json = mapper.writeValueAsString(gameNode);
            Files.write(file.toPath(), json.getBytes());
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio del file JSON: " + e.getMessage());
        }
    }
}
