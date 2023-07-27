package ch.supsi.tictactoe.backend.deserializer;

import ch.supsi.tictactoe.backend.model.*;
import ch.supsi.tictactoe.backend.model.enumList.GameName;
import ch.supsi.tictactoe.backend.model.enumList.GameStatus;
import ch.supsi.tictactoe.backend.model.enumList.Symbol;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class GameModelDeserializer extends JsonDeserializer<GameModel> {
    @Override
    public GameModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        Symbol playerTurn = codec.treeToValue(node.get("playerTurnSymbol"), Symbol.class);
        GameName gameLogic = codec.treeToValue(node.get("gameLogicName"), GameName.class);
        GameStatus gameStatus = codec.treeToValue(node.get("gameStatus"), GameStatus.class);
        Symbol[][] board = codec.treeToValue(node.get("board"), Symbol[][].class);

        return new GameModel(playerTurn, gameLogic, gameStatus, board);
    }
}