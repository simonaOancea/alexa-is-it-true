package main.java.com.amazon.ask.trivia.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import main.java.com.amazon.ask.trivia.quiz.models.Attributes;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;
import static main.java.com.amazon.ask.trivia.quiz.util.QuestionUtils.getQuestionText;

/**
 * RepeatIntentHandler handler for handling AMAZON.RepeatIntent requests.
 */
public class RepeatIntentHandler implements RequestHandler {

    /**
     * Checking Intent matches AMAZON.RepeatIntent.
     * @param input the user input
     * @return
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.RepeatIntent")
                .and(sessionAttribute(Attributes.STATE_KEY, Attributes.QUIZ_STATE)));
    }

    /**
     * Handles AMAZON.RepeatIntent.
     * @param input the user input
     * @return
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        int counter = (int) sessionAttributes.get(Attributes.COUNTER_KEY);
        String cachedQuestion = (String) sessionAttributes.get(Attributes.TRIVIA_QUIZ_ITEM_KEY);

        String question = getQuestionText(counter, cachedQuestion);
        return input.getResponseBuilder()
                .withSpeech(question)
                .withReprompt(question)
                .withShouldEndSession(false)
                .build();
    }
}
