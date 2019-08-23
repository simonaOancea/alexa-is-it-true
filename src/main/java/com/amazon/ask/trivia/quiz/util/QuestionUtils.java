package main.java.com.amazon.ask.trivia.quiz.util;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import java.util.logging.Level;
import main.java.com.amazon.ask.trivia.quiz.models.Attributes;

import java.util.*;

import static main.java.com.amazon.ask.trivia.quiz.models.Attributes.QUESTIONS_PLAYED;
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
        String generatedQuestion = getRandomQuestion(input);
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


    private static String getRandomQuestion(HandlerInput handlerInput) {
      Random random = new Random();
      List<Integer> interval = new ArrayList<>();
      List<String> allQuestions = new ArrayList(Arrays.asList(QUESTIONS));

      Map<String, Object> sessionAttributes = handlerInput.getAttributesManager().getSessionAttributes();
      int counter = (int) sessionAttributes.get(Attributes.COUNTER_KEY);

     /* for(int i = 0; i < 13; i++) {
        interval.add(i);
      }*/
     // Collections.shuffle(interval);
      Collections.shuffle(QUESTIONS);

      if(counter != 0) {
        Map<String, Object> persistenceAttributes = handlerInput.getAttributesManager().getPersistentAttributes();
        List<String> questionsPlayed = (ArrayList) persistenceAttributes.get(QUESTIONS_PLAYED);

        java.util.logging.Logger.getLogger(QuestionUtils.class.getCanonicalName())
            .log(Level.INFO, "Question retrieved from db " + questionsPlayed);

        if(questionsPlayed != null || !questionsPlayed.isEmpty()){
          for(String question : questionsPlayed) {
           // interval.remove(QUESTIONS.indexOf(question));
           // QUESTIONS.remove(question);
            allQuestions.remove(question);

            java.util.logging.Logger.getLogger(QuestionUtils.class.getCanonicalName())
                .log(Level.INFO, "Questions removed is: " + QUESTIONS.indexOf(question));
            java.util.logging.Logger.getLogger(QuestionUtils.class.getCanonicalName())
                .log(Level.INFO, "Interval size is: " + interval.size());
          }
        }
      }
     // int questionNumber = random.nextInt(interval.size());
      int questionNumber = random.nextInt(allQuestions.size());
      java.util.logging.Logger.getLogger(QuestionUtils.class.getCanonicalName())
          .log(Level.INFO, "Interval size out of is statement is: " + interval.size());
      String randomQuestion = allQuestions.get(questionNumber);


      //List<Integer> extractedNumbers = new ArrayList<>();
      //Set<Integer> uniqueExtractedNumbers = new HashSet<>();

      /*extractedNumbers.add(questionNumber);
      uniqueExtractedNumbers.add(questionNumber);
      interval.remove(randomQuestion);*/

        /*if(uniqueExtractedNumbers.size() == extractedNumbers.size()) {
            return randomQuestion;
        }else {
            Collections.shuffle(interval);
            questionNumber = random.nextInt(interval.size());
            randomQuestion = QUESTIONS.get(questionNumber);
        }*/

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



