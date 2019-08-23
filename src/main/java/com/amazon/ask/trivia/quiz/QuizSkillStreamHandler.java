package main.java.com.amazon.ask.trivia.quiz;

import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import main.java.com.amazon.ask.trivia.quiz.handlers.*;
import main.java.com.amazon.ask.trivia.quiz.persistence.DynamoDbPersistenceAdapter;

public class QuizSkillStreamHandler extends SkillStreamHandler {

  public QuizSkillStreamHandler() {
    super(Skills.standard()
        .addRequestHandlers(new LaunchRequestHandler(), new QuizAndStartOverIntentHandler(),
            new NameIntentHandler(), new AnswerIntentHandler(), new RepeatIntentHandler(),
            new HelpIntentHandler(), new ExitSkillHandler(), new SessionEndedHandler(), new FallbackIntentHandler())
        //.withPersistenceAdapter(DynamoDbPersistenceAdapter.builder().withAutoCreateTable(true).withTableName("USERS").build())
        .withTableName("USERS")
        .addExceptionHandler(new ErrorHandler())
        .build());
  }

}
