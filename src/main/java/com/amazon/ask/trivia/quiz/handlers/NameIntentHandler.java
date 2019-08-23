package main.java.com.amazon.ask.trivia.quiz.handlers;

import static main.java.com.amazon.ask.trivia.quiz.models.Constants.START_GAME_MESSAGE;
import static main.java.com.amazon.ask.trivia.quiz.models.Constants.START_GAME_MESSAGE_WITH_NAME;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;
import main.java.com.amazon.ask.trivia.quiz.models.Attributes;
import main.java.com.amazon.ask.trivia.quiz.util.Format;

/**
 * NameIntentHandler handler for handling NameIntent requests.
 */
public class NameIntentHandler implements IntentRequestHandler {

  /**
   * Checking Intent matches NameIntent.
   * @param input the user input
   * @param intentRequest the intent request
   * @return boolean
   */
  @Override
  public boolean canHandle(HandlerInput input, IntentRequest intentRequest) {
    return intentRequest.getIntent().getName().equals("NameIntent");
  }

  /**
   * Handles NameIntent.
   * @param input the user input
   * @param intentRequest the intent request
   * @return String
   */
  @Override
  public Optional<Response> handle(HandlerInput input, IntentRequest intentRequest) {
    String gameMessage = START_GAME_MESSAGE;
    Map<String, Object> persistenceAttributes = input.getAttributesManager().getPersistentAttributes();
    final String playerName = intentRequest.getIntent().getSlots().get("Name").getValue();
    final MessageFormat formatMessage = new MessageFormat(START_GAME_MESSAGE_WITH_NAME);

    if(playerName != null || !playerName.isEmpty()) {
      persistenceAttributes.put(Attributes.PLAYER_NAME, playerName);
      input.getAttributesManager().setPersistentAttributes(persistenceAttributes);
      input.getAttributesManager().savePersistentAttributes();
      gameMessage = Format.formatMessage(formatMessage, playerName);
    }

    return input.getResponseBuilder()
        .withSpeech(gameMessage)
        .build();
  }

}
