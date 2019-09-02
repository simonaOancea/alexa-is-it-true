package main.java.com.amazon.ask.trivia.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.slu.entityresolution.Resolution;
import com.amazon.ask.model.slu.entityresolution.Resolutions;
import com.amazon.ask.model.slu.entityresolution.Value;
import com.amazon.ask.model.slu.entityresolution.ValueWrapper;
import main.java.com.amazon.ask.trivia.quiz.models.Attributes;
import main.java.com.amazon.ask.trivia.quiz.models.Constants;
import main.java.com.amazon.ask.trivia.quiz.models.Question;
import main.java.com.amazon.ask.trivia.quiz.util.QuestionUtils;

import static com.amazon.ask.request.Predicates.*;
import static main.java.com.amazon.ask.trivia.quiz.models.Attributes.QUESTIONS_PLAYED;
import static main.java.com.amazon.ask.trivia.quiz.models.Attributes.TRIVIA_QUIZ_ITEM_KEY;

import java.util.*;
import java.util.logging.Level;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AnswerIntentHandler handler for handling answers requests.
 */
public class AnswerIntentHandler implements RequestHandler {

    /**
     * The questions played list.
     */
    private List<String> questionsPlayed = new ArrayList<>();

    /**
     * The random.
     */
    private static final Random RANDOM = new Random();


    /**
     * Checking Intent matches AnswerIntent.
     * @param input
     * @return boolean
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AnswerIntent")
                .and(sessionAttribute(Attributes.STATE_KEY, Attributes.QUIZ_STATE)));
    }
    /**
     * Handles AnswerIntentHandler.
     * @param input the user input
     * @return String
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        Map<String, Object> persistenceAttributes = input.getAttributesManager().getPersistentAttributes();

        int counter = (int) sessionAttributes.get(Attributes.COUNTER_KEY);
        int quizScore = (int) sessionAttributes.get(Attributes.TRIVIA_QUIZ_SCORE_KEY);

        String responseText;
        String speechOutput;

        String correctAnswer = sessionAttributes.get(Attributes.TRIVIA_QUIZ_PROPERTY_KEY).toString();
        String previousQuestion = sessionAttributes.get(TRIVIA_QUIZ_ITEM_KEY).toString();
        questionsPlayed.add(previousQuestion);
        String additionalInformation = Question.ADDITIONAL_INFORMATION.get(previousQuestion);

        persistenceAttributes.put(QUESTIONS_PLAYED, questionsPlayed);
        input.getAttributesManager().setPersistentAttributes(persistenceAttributes);
        input.getAttributesManager().savePersistentAttributes();

        //gets the users input -> RequestEnvelope (it should be yes or no)
        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        //logging for debugging purposes
        java.util.logging.Logger.getLogger(AnswerIntentHandler.class.getCanonicalName()).log(Level.INFO, intentRequest.getIntent().getSlots().values().toString());

        boolean isCorrectAnswer = compareSlots(intentRequest.getIntent().getSlots(), correctAnswer);

        if (isCorrectAnswer) {
            quizScore++;
            responseText = getSpeechCon(true);
            sessionAttributes.put(Attributes.TRIVIA_QUIZ_SCORE_KEY, quizScore);
        } else {
            responseText = getSpeechCon(false);
        }

        if (counter < 5) {
            responseText += "Your current score is " + quizScore + " out of " + counter + ". " + additionalInformation;
            sessionAttributes.put(Attributes.RESPONSE_KEY, responseText);
            return QuestionUtils.generateQuestion(input);
        } else {
            responseText += "Your final score is " + quizScore + " out of " + counter + ". " + additionalInformation;
            speechOutput = responseText + " " + Constants.EXIT_SKILL_MESSAGE;
            return input.getResponseBuilder()
                    .withSpeech(speechOutput)
                    .withShouldEndSession(true)
                    .build();
        }
    }

    /**
     * Gets the appropriate interjection.
     * @param correct the flag for correct answer
     * @return
     */
    private String getSpeechCon(boolean correct) {
        if (correct) {
            return "<say-as interpret-as='interjection'>" + getRandomItem(Constants.CORRECT_RESPONSES) + "! </say-as><break strength='strong'/>";
        } else {
            return "<say-as interpret-as='interjection'>" + getRandomItem(Constants.INCORRECT_RESPONSES) + " </say-as><break strength='strong'/>";
        }
    }

    /**
     * Gets random item from list.
     * @param list the list
     * @return int
     */
    private <T> T getRandomItem(List<T> list) {
        return list.get(RANDOM.nextInt(list.size()));
    }

    /**
     * Compares the users answer to actual correct answer.
     * @param slots
     * @param correctAnswer
     * @return boolean
     */
    private boolean compareSlots(Map<String, Slot> slots, String correctAnswer) {
        for (Slot slot : slots.values()) {

            if (slot.getValue() != null) {

                Resolutions resolutions = slot.getResolutions();
                List<Resolution> resolutionsList = resolutions.getResolutionsPerAuthority();

                for (Resolution resolution : resolutionsList) {
                    List<ValueWrapper> valueWrappperList = resolution.getValues();

                    for (ValueWrapper valueWrapper : valueWrappperList) {
                        Value value = valueWrapper.getValue();
                        String id = value.getId();

                        if (id.toLowerCase().equals(correctAnswer.toLowerCase())) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

}
