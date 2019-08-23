package main.java.com.amazon.ask.trivia.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import java.util.Map;
import main.java.com.amazon.ask.trivia.quiz.models.Constants;

import java.util.Optional;
import java.util.logging.Level;

import static com.amazon.ask.request.Predicates.intentName;
import static main.java.com.amazon.ask.trivia.quiz.models.Attributes.START_STATE;
import static main.java.com.amazon.ask.trivia.quiz.models.Attributes.STATE_KEY;
import static main.java.com.amazon.ask.trivia.quiz.models.Constants.FALLBACK_MESSAGE;

/**
 * FallBackIntent handler for handling unknown requests.
 */
public class FallbackIntentHandler implements RequestHandler {

    /**
     * Checking Intent matches FallBackIntent.
     * @param input the user input
     * @return boolean
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        final boolean isIntentRequest = input.getRequestEnvelope().getRequest().getType().equalsIgnoreCase("IntentRequest");
        java.util.logging.Logger.getLogger(FallbackIntentHandler.class.getCanonicalName())
                .log(Level.INFO, "Entered in FallbackIntentHandler");
        return isIntentRequest && input.matches(intentName("AMAZON.FallbackIntent"));
    }

    /**
     * Handles FallBackIntent.
     * @param input the user input
     * @return String
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();

        if(sessionAttributes.get(STATE_KEY).equals(START_STATE)) {
            return input.getResponseBuilder()
                .withSpeech(FALLBACK_MESSAGE)
                .build();
        }else {
            return input.getResponseBuilder()
                .withSpeech(Constants.FALLBACK_MESSAGE_GAME_STARTED)
                .withReprompt(Constants.FALLBACK_MESSAGE_TWO)
                .withReprompt(Constants.FALLBACK_MESSAGE_THREE)
                .build();
        }
    }
}
