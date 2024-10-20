package info.gehrels.parameterValidation;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

import static info.gehrels.parameterValidation.MatcherValidation.validateThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class MatcherValidationTest {
    @Test
    public void throwsIllegalArgumentExceptionWithoutCustomMessageWhenMatcherDoesNotMatch() {
        try {
            validateThat("arbitraryString", matcherStub(false));
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is(System.lineSeparator() + "Expected: full description"
                    + System.lineSeparator() + "     but: mismatch description"));
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
                    + System.lineSeparator() + "Expected: full description"
                    + System.lineSeparator() + "     but: mismatch description"));
            return;
        }

        fail("IllegalArgumentException has not been thrown");
    }

    @Test
    public void doesNotThrowIllegalArgumentExceptionIfMatcherMatches() {
        validateThat("arbitrary object", matcherStub(true));
    }


    private Matcher<? super String> matcherStub(final boolean matches) {
        return new BaseMatcher<String>() {

            @Override
            public boolean matches(Object o) {
                return matches;
            }

            @Override
            public void describeMismatch(Object o, Description description) {
                description.appendText("mismatch description");
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("full description");
            }
        };
    }


}
