package main.java.com.amazon.ask.trivia.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import main.java.com.amazon.ask.trivia.quiz.models.Constants;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;

import java.util.Optional;
import static com.amazon.ask.request.Predicates.intentName;

public class FallbackIntentHandler implements IntentRequestHandler {

    @Override
    public boolean canHandle(HandlerInput input, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("AMAZON.FallbackIntent");
    }

    @Override
    public Optional<Response> handle(HandlerInput input, IntentRequest request) {
        return input.getResponseBuilder()
                .withSpeech(Constants.FALLBACK_MESSAGE_ONE)
                .withReprompt(Constants.FALLBACK_MESSAGE_TWO)
                .withReprompt(Constants.FALLBACK_MESSAGE_THREE)
                .build();
    }
}
