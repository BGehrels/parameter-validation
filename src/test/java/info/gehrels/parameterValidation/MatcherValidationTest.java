package info.gehrels.parameterValidation;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import static info.gehrels.parameterValidation.MatcherValidation.validateThat;
import static junit.framework.Assert.fail;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MatcherValidationTest {
	@Test
	public void throwsIllegalArgumentExceptionWithoutCustomMessageWhenMatcherDoesNotMatch() {
		try {
			validateThat("arbitraryString", matcherStub(false));
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("\nExpected: full description"
			                              + "\n     but: mismatch description"));
			return;
		}

		fail("IllegalArgumentException has not been thrown");
	}

	@Test
	public void throwsIllegalArgumentExceptionWithCustomMessageWhenMatcherDoesNotMatch() {
		try {
			validateThat("arbitrary message", "arbitrary object", matcherStub(false));
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("arbitrary message"
			                              + "\nExpected: full description"
			                              + "\n     but: mismatch description"));
			return;
		}

		fail("IllegalArgumentException has not been thrown");
	}

	@Test
	public void doesNotThrowIllegalArgumentExceptionIfMatcherMatches() {
	    validateThat("arbitrary object", matcherStub(true));
	}


	private Matcher<? super String> matcherStub(final boolean matches) {
		return new Matcher<Object>() {

			@Override
			public boolean matches(Object o) {
				return matches;
			}

			@Override
			public void describeMismatch(Object o, Description description) {
				description.appendText("mismatch description");
			}

			@Override
			public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
				throw new UnsupportedOperationException();
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("full description");
			}
		};
	}


}
