package main.java.com.amazon.ask.trivia.quiz.util;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import main.java.com.amazon.ask.trivia.quiz.models.Attributes;

import java.util.*;

import static main.java.com.amazon.ask.trivia.quiz.models.Constants.START_TRIVIA_QUIZ_MESSAGE;
import static main.java.com.amazon.ask.trivia.quiz.models.Question.CORRECT_ANSWERS;
import static main.java.com.amazon.ask.trivia.quiz.models.Question.QUESTIONS;

public class QuestionUtils {

    public static Optional<Response> generateQuestion(HandlerInput input) {

        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        int counter = (int) sessionAttributes.get(Attributes.COUNTER_KEY);

        if(counter == 0) {
            sessionAttributes.put(Attributes.RESPONSE_KEY, START_TRIVIA_QUIZ_MESSAGE + " ");
        }
        counter++;
        String generatedQuestion = getRandomQuestion();
        String question = getQuestionText(counter) + generatedQuestion;

        sessionAttributes.put(Attributes.COUNTER_KEY, counter);
        sessionAttributes.put(Attributes.TRIVIA_QUIZ_ITEM_KEY, generatedQuestion);
        sessionAttributes.put(Attributes.TRIVIA_QUIZ_PROPERTY_KEY, getCorrectAnswer(generatedQuestion));

        String speech = sessionAttributes.get(Attributes.RESPONSE_KEY) + question;

        return input.getResponseBuilder()
                .withSpeech(speech)
                .withReprompt(question)
                .withShouldEndSession(false)
                .build();

    }


    public static String getQuestionText(int counter) {
        return "Here is your " + getTheNumberOfQuestion(counter) + " question. ";
    }

    //this method is for RepeatIntentHandler
    public static String getQuestionText(int counter, String question) {
        return "Here is your " + getTheNumberOfQuestion(counter) + " question. " + question;
    }

   /* private static String getRandomQuestion() {
        Random random = new Random();
        String randomQuestion = QUESTIONS.get(random.nextInt(QUESTIONS.size()));

        return randomQuestion;

    }*/

    private static String getRandomQuestion() {
        Random random = new Random();
        List<Integer> interval = new ArrayList<>();

        for(int i = 0; i < 10; i++) {
            interval.add(i);
        }
        Collections.shuffle(interval);
        String randomQuestion = QUESTIONS.get(random.nextInt(interval.size()));
        return randomQuestion;
    }

    private static String getCorrectAnswer(String question) {
        String correctAnswer = CORRECT_ANSWERS.get(question).toString();
        return correctAnswer;
    }

    private static String getTheNumberOfQuestion(int counter) {
        String numberOfQuestion = null;
        switch(counter) {
            case 1: numberOfQuestion = "first";
            break;
            case 2: numberOfQuestion = "second";
            break;
            case 3: numberOfQuestion = "third";
            break;
            case 4: numberOfQuestion = "fourth";
            break;
            case 5: numberOfQuestion = "fifth";
            break;
        }
        return numberOfQuestion;
    }

}



