package main.java.com.amazon.ask.trivia.quiz.util;

import java.util.LinkedHashMap;
import java.util.Map;

import static main.java.com.amazon.ask.trivia.quiz.models.Constants.*;

public class ConstantsUtils {

    public static Map<String, Integer> responseIds = new LinkedHashMap<String, Integer>() {{
        put(WELCOME_MESSAGE, 1);
        put(START_TRIVIA_QUIZ_MESSAGE, 2);
        put(HELP_MESSAGE, 3);
        put(EXIT_SKILL_MESSAGE, 4);
    }};



}
