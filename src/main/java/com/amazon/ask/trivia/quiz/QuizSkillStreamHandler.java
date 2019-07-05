package main.java.com.amazon.ask.trivia.quiz;

import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import main.java.com.amazon.ask.trivia.quiz.handlers.*;

public class QuizSkillStreamHandler extends SkillStreamHandler {

    public QuizSkillStreamHandler() {
        super(Skills.standard()
                .addRequestHandlers(new LaunchRequestHandler(), new QuizAndStartOverIntentHandler(),
                        new AnswerIntentHandler(), new RepeatIntentHandler(), new HelpIntentHandler(),
                        new ExitSkillHandler(), new SessionEndedHandler(), new FallbackIntentHandler())
                .build());
    }

}
