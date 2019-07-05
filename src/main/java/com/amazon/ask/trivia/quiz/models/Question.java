package main.java.com.amazon.ask.trivia.quiz.models;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Question {

    private Question(){

    }

    public static  List<String> QUESTIONS = Arrays.asList(
            "Is it true that Nikola Tesla worked for Thomas Edison?",
            "Is it true that Einstein was the inventor of alternative current, also known as AC?",
            "Is it true that Nikola Tesla built the first hydroelectric plant?",
            "Is it true that Nikola Tesla was the most awesome geek that ever lived?",
            "Is it true that unicorns used to live?",
            "Is it true that you can make tea of out unicorn tears?",
            "Is it true that in some countries you are allowed to raise a pony inside an apartment?",
            "Is is true that Socrates never wrote anything?",
            "Is it true that Saturn is the oldest planet from the solar system?",
            "Is it true that you could fit all the people from planet Earth into one building?",
            "Is it true that you love this game?"
    );

    public static Map<String, Answer> CORRECT_ANSWERS = new LinkedHashMap<String, Answer>(){{
        put(QUESTIONS.get(0), Answer.YES);
        put(QUESTIONS.get(1), Answer.NO);
        put(QUESTIONS.get(2), Answer.YES);
        put(QUESTIONS.get(3), Answer.YES);
        put(QUESTIONS.get(4), Answer.YES);
        put(QUESTIONS.get(5), Answer.YES);
        put(QUESTIONS.get(6), Answer.YES);
        put(QUESTIONS.get(7), Answer.YES);
        put(QUESTIONS.get(8), Answer.NO);
        put(QUESTIONS.get(9), Answer.YES);
        put(QUESTIONS.get(10), Answer.YES);

    }};

    public static Map<String, String> ADDITIONAL_INFORMATION = new LinkedHashMap<String, String>() {{
        put(QUESTIONS.get(0), "At 28, Nikola Tesla was hired by Thomas Edison. Edison's motors and generators needed an upgrade, so he offered Tesla the modern equivalent of a million dollars to re-design them. The bad news is that Thomas Edison wasn't such a nice person. When Tesla finished his work, Edison refused to pay him. As a result, Tesla quit and started digging ditches while working on his AC electrical system. ");
        put(QUESTIONS.get(1), "Actually, the inventor of the AC was Nikola Tesla. All homes today use Tesla's AC system. ");
        put(QUESTIONS.get(2), "The first hydroelectric plant was built by Nikola Tesla at Niagara Falls. ");
        put(QUESTIONS.get(3), "Nicola Tesla spoke eight languages: English, French, Serbian, Italian, Latin, German, Hungarian and Czech. He could also memorize entire books and recite them at will. His photographic memory enabled him to visualize complex devices and then build them without ever writing anything down. Being so smart apparently comes with some minor disadvantages. Tesla often hallucinated and heard voices. ");
        put(QUESTIONS.get(4), "It seems that unicorns weren't just mystical fairytale creatures, they actually lived more than 29000 years ago and they were known as the Syberian Unicorns. This creatures resembled more with a rhinoceros than a horse, being much bigger and taller. ");
        put(QUESTIONS.get(5), "It is said that when a unicorn cries, the flowers on the ground grow. There's actually a brand called Par Avion that's selling Unicorn Tears Tea which is a color changing green tea with rose heap. They don't claim it's from unicorn's tears, but the name is super fun to use. ");
        put(QUESTIONS.get(6), "There is no law that's preventing you from raising a pony inside an apartment provided the fact that you are the owner. So go ahead, buy that pony and cuddle every night after work. ");
        put(QUESTIONS.get(7), "Yes, Socrates did not write anything. ");
        put(QUESTIONS.get(8), "Saturn may be cooler, but the oldest planet from the solar system is Jupiter. ");
        put(QUESTIONS.get(9), "Yes, you could fit all the people into one building. ");
        put(QUESTIONS.get(10), "test");

    }};





}
