package main.java.com.amazon.ask.trivia.quiz.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.SessionEndedRequest;
import org.slf4j.Logger;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * SessionEndedHandler handler for handling SessionEndedRequest class.
 */
public class SessionEndedHandler implements RequestHandler {

    private static Logger LOG = getLogger(SessionEndedHandler.class);

    /**
     * Checking Intent matches SessionEndedRequest.
     * @param input the user input
     * @return boolean
     */
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(SessionEndedRequest.class));
    }

    /**
     * Handles SessionEndedRequest
     * @param input the user input
     * @return String
     */
    @Override
    public Optional<Response> handle(HandlerInput input) {
        SessionEndedRequest sessionEndedRequest = (SessionEndedRequest) input.getRequestEnvelope().getRequest();
        LOG.debug("Session ended with reason: " + sessionEndedRequest.getReason().toString());
        return Optional.empty();
    }
}
