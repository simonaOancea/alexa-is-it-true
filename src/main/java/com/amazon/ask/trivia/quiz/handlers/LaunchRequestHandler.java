package main.java.com.amazon.ask.trivia.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import main.java.com.amazon.ask.trivia.quiz.models.Attributes;
import main.java.com.amazon.ask.trivia.quiz.models.Constants;
import main.java.com.amazon.ask.trivia.quiz.util.ConstantsUtils;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;
import static main.java.com.amazon.ask.trivia.quiz.models.Constants.WELCOME_MESSAGE;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input){
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input){
        //gets session attributes from user input
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        // put start state on session attributes
        sessionAttributes.put(Attributes.STATE_KEY, Attributes.START_STATE);
        sessionAttributes.put(Attributes.RESPONSE_ID, ConstantsUtils.responseIds.get(WELCOME_MESSAGE));
        return input.getResponseBuilder()
                .withSpeech(WELCOME_MESSAGE)
                .withReprompt(Constants.LAUNCH_GAME_HELP_MESSAGE)
                .withShouldEndSession(false)
                .build();

    }
}
