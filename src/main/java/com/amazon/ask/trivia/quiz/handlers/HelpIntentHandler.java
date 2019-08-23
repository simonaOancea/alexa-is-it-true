package main.java.com.amazon.ask.trivia.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import main.java.com.amazon.ask.trivia.quiz.models.Constants;
import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;

/**
 * HelpIntentHandler handler for handling AMAZON.HelpIntent requests.
 */
public class HelpIntentHandler implements RequestHandler {

    /**
     * Checking Intent matches AMAZON.HelpIntent.
     * @param input
     * @return boolean
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    /**
     * Handles AMAZON.HelpIntent.
     * @param input the user input
     * @return String
     */
    @Override
    public Optional<Response> handle(HandlerInput input){
        return input.getResponseBuilder()
                .withSpeech(Constants.HELP_MESSAGE)
                .withReprompt(Constants.HELP_MESSAGE)
                .withShouldEndSession(false)
                .build();
    }
}
