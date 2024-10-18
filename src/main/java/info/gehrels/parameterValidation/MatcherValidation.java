package info.gehrels.parameterValidation;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

/**
 * Contains static methods to validate parameters using Hamcrest matchers.
 */
public final class MatcherValidation {
    private MatcherValidation() {
        // To prevent instantiation.
    }

    /**
     * Throws an IllegalArgumentException with a meaningful error message if the given value is not matched by the
     * matcher. Just returns the value otherwise.
     *
     * @param actual  the value to validate
     * @param matcher the matcher used to validate the actual value
     * @param <T> The type of the actual value that is validated
     * @return the given actual value if validation is successful
     * @throws IllegalArgumentException if validation using the matcher fails
     */
    public static <T> T validateThat(T actual, Matcher<? super T> matcher) {
        return validateThat("", actual, matcher);
    }

    /**
     * Throws an IllegalArgumentException with a meaningful error message if the given value is not matched by the
     * matcher. Just returns the value otherwise.
     *
     * @param reason  a description of what is being matched, that is guaranteed to be contained in the message of the IllegalArgumentException
     * @param actual  the value to validate
     * @param matcher the matcher used to validate the actual value
     * @param <T> The type of the actual value that is validated
     * @return the given actual value if validation is successful
     * @throws IllegalArgumentException if validation using the matcher fails
     */
    public static <T> T validateThat(String reason, T actual, Matcher<? super T> matcher) {
        if (!matcher.matches(actual)) {
            Description description = new StringDescription();
            description.appendText(reason)
                    .appendText(System.lineSeparator())
                    .appendText("Expected: ")
                    .appendDescriptionOf(matcher)
                    .appendText(System.lineSeparator())
                    .appendText("     but: ");
            matcher.describeMismatch(actual, description);

            throw new IllegalArgumentException(description.toString());
        }

        return actual;
    }

}
