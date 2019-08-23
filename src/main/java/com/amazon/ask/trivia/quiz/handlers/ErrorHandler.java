package main.java.com.amazon.ask.trivia.quiz.handlers;

import com.amazon.ask.dispatcher.exception.ExceptionHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import main.java.com.amazon.ask.trivia.quiz.models.Constants;
import java.util.Optional;

/**
 * ErrorHandler for handling exceptions.
 */
public class ErrorHandler implements ExceptionHandler {

    /**
     * Checking Intent can be handled.
     * @param handlerInput
     * @param throwable
     * @return boolean
     */
    @Override
    public boolean canHandle(HandlerInput handlerInput, Throwable throwable) {
        return false;
    }

    /**
     * Handles ErrorHandler.
     * @param handlerInput
     * @param throwable
     * @return String
     */
    @Override
    public Optional<Response> handle(HandlerInput handlerInput, Throwable throwable) {
        return handlerInput.getResponseBuilder()
                .withSpeech(Constants.ERROR_MESSAGE)
                .withReprompt(Constants.ERROR_MESSAGE)
                .build();
    }
}
