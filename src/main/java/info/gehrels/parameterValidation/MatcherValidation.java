package info.gehrels.parameterValidation;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

public class MatcherValidation {
 public static <T> void validateThat(T actual, Matcher<? super T> matcher) {
     validateThat("", actual, matcher);
 }

 public static <T> void validateThat(String reason, T actual, Matcher<? super T> matcher) {
     if (!matcher.matches(actual)) {
         Description description = new StringDescription();
         description.appendText(reason)
                    .appendText("\nExpected: ")
                    .appendDescriptionOf(matcher)
                    .appendText("\n     but: ");
         matcher.describeMismatch(actual, description);

         throw new IllegalArgumentException(description.toString());
     }
 }
}
