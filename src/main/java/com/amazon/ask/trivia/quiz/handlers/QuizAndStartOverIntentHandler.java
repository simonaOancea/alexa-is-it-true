package main.java.com.amazon.ask.trivia.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import main.java.com.amazon.ask.trivia.quiz.models.Attributes;
import main.java.com.amazon.ask.trivia.quiz.util.QuestionUtils;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import static com.amazon.ask.request.Predicates.intentName;
import static com.amazon.ask.request.Predicates.sessionAttribute;

/**
 * QuizAndStartOverIntentHandler handler for handling QuizIntent or AMAZON.StartOverIntent.
 */
public class QuizAndStartOverIntentHandler implements RequestHandler {

    /**
     * Checking Intent matches QuizIntent or AMAZON.StartOverIntent.
     * @param input the user input
     * @return boolean
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("QuizIntent")
            .and(sessionAttribute(Attributes.STATE_KEY, Attributes.QUIZ_STATE).negate())
                .and(sessionAttribute(Attributes.RESPONSE_ID, 1)))
                || input.matches(intentName("AMAZON.StartOverIntent"));
    }

    /**
     * Handles QuizIntent, AMAZON.StartOverIntent.
     * @param input the user input
     * @return String
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(Attributes.STATE_KEY, Attributes.QUIZ_STATE);
        sessionAttributes.put(Attributes.RESPONSE_KEY, "");
        sessionAttributes.put(Attributes.COUNTER_KEY, 0);
        sessionAttributes.put(Attributes.TRIVIA_QUIZ_SCORE_KEY, 0);

        return QuestionUtils.generateQuestion(input);
    }
}
