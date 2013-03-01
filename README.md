parameter-validation
====================

Validating input parameters of methods and constructors is a recurring and stupid task, especially when you are writing public APIs. This module tries to ease your life a bit by providing a static validateThat() method, allowing you to throw IllegalArgumentExceptions using hamcrest matchers.

Using the matchers is as easy as

	validateThat(name, not(isEmptyOrNullString()));


or

    validateThat("parameter 'name has an invalid value", name, not(isEmptyOrNullString()));

If it fails to validate the parameter, an Exception is thrown:
    Exception in thread "main" java.lang.IllegalArgumentException: parameter 'name has an invalid value
    Expected: not (null or an empty string)
         but: was null
    	at info.gehrels.parameterValidation.MatcherValidation.validateThat(MatcherValidation.java:40)
    	...

