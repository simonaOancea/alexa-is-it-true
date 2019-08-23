package main.java.com.amazon.ask.trivia.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import main.java.com.amazon.ask.trivia.quiz.models.Attributes;
import main.java.com.amazon.ask.trivia.quiz.models.Constants;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;
/**
 * ExitSkillHandler handler for handling stop requests.
 */
public class ExitSkillHandler implements RequestHandler {

  /**
   * Checking Intent matches AMAZON.StopIntent or AMAZON.PauseIntent or AMAZON.CancelIntent.
   * @param input
   * @return boolean
   */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.StopIntent")
                .or(intentName("AMAZON.PauseIntent")
                .or(intentName("AMAZON.CancelIntent"))));
    }

  /**
   * Handles AMAZON.StopIntent, AMAZON.PauseIntent, AMAZON.CancelIntent.
   * @param input
   * @return String
   */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        return input.getResponseBuilder()
                .withSpeech(Constants.EXIT_SKILL_MESSAGE).build();

    }
}
