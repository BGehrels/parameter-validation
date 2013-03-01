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
	 * Throws an IllegalArgumentException with a meaningfull error message if the given value is not matched by the
	 * given value. Has no side effects otherwise.
	 * @param actual the value to validate
	 * @param matcher the matcher used to validate the actual value
	 */
	public static <T> void validateThat(T actual, Matcher<? super T> matcher) {
		validateThat("", actual, matcher);
	}

	/**
	 * Throws an IllegalArgumentException with a meaningfull error message if the given value is not matched by the
	 * given value. Has no side effects otherwise.
	 * @param reason a String that is guarenteed to be contained in the message of the IllegalArgumentException
	 * @param actual the value to validate
	 * @param matcher the matcher used to validate the actual value
	 */
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
