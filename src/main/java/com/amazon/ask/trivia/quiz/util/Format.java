package main.java.com.amazon.ask.trivia.quiz.util;

import java.text.MessageFormat;

public class Format {

  /**
   * Format message.
   * @param format the format
   * @param objects the objects
   * @return the formatted string
   */
  public static String formatMessage(final MessageFormat format, final Object... objects) {
    return format.format(objects);
  }

}
