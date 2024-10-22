package main.java.com.amazon.ask.trivia.quiz.models;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final String SKILL_NAME = "Is it true";

    public static final String WELCOME_MESSAGE = "Welcome to Is It True! You will be asked five questions at which you should answer with yes or no. Let's see how good you are at this! But first, tell me human, what's your name?";

    public static final String START_GAME_MESSAGE = "Great! Your name is awesome. Now please say start if you are ready to begin, or stop if you want to exit the game.";

    public static final String START_GAME_MESSAGE_WITH_NAME = "Great! I bet {0} comes from awesome in another language. Now please say start if you are ready to begin, or stop if you want to exit the game.";


    public static final String LAUNCH_GAME_HELP_MESSAGE = "You can say start if you want to play or exit the game by saying stop.";

    //This is the message a user will hear when they ask Alexa for help in your skill
    public static final String HELP_MESSAGE = "Please say repeat question if you would like me to repeat the question.";

    //public static String ADDITIONAL_INFORMATION_MESSAGE = "Would you like to find out more?";

    public static final String START_TRIVIA_QUIZ_MESSAGE = "Cool! I like you already! Let's bring light to this party!";

    public static final String FALLBACK_MESSAGE = "I'm sorry, I'm having trouble understanding you. You can tell me your name or say stop if you would like to end the game.";

    public static final String FALLBACK_MESSAGE_GAME_STARTED = "I'm sorry, I didn't understand. Try answering with yes or no.";

    public static final String FALLBACK_MESSAGE_TWO = "You can answer by saying yes or no.";

    public static final String FALLBACK_MESSAGE_THREE = "Sorry, I'm still having trouble understanding you. You could check your history section in the Alexa app to see what your device is sending me. ";

    public static final String ERROR_MESSAGE = "Sorry, I'm having trouble understanding you . Please say repeat question if you would like to hear the question again or stop to end the game.";

    public static final String EXIT_SKILL_MESSAGE = "Thank you for playing Is It True, you were great at this! Come again soon, we're constantly adding new fun questions. Let's fool around again soon!";

    public static List<String> CORRECT_RESPONSES = Arrays.asList("Booya", "All righty", "Bam", "Bazinga", "Bingo", "Boom", "Bravo", "Cha Ching", "Cheers", "Dynomite",
            "Hip hip hooray", "Hurrah", "Hurray", "Huzzah", "Oh dear.  Just kidding.  Hurray", "Kaboom", "Kaching", "Oh snap", "Phew",
            "Righto", "Way to go", "Well done", "Whee", "Woo hoo", "Yay", "Wowza", "Yowsa");

    public static List<String> INCORRECT_RESPONSES = Arrays.asList("Argh", "Aw man", "Blarg", "Blast", "Boo", "Bummer", "Darn", "D'oh", "Dun dun dun", "Eek", "Honk", "Le sigh",
            "Mamma mia", "Oh boy", "Oh dear", "Oof", "Ouch", "Ruh roh", "Shucks", "Uh oh", "Wah wah", "Whoops a daisy", "Yikes");




}
