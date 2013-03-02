Parameter validation using Hamcrest matchers
============================================
Validating input parameters of methods and constructors is a recurring and stupid task, especially when you are writing public APIs. This module tries to ease your life a bit by providing a static validateThat() method, allowing you to throw IllegalArgumentExceptions using hamcrest matchers.

Using the matchers is as easy as

    import static info.gehrels.parameterValidation.MatcherValidation.validateThat;
    import static org.hamcrest.CoreMatchers.not;
    import static org.hamcrest.Matchers.isEmptyOrNullString;

	...

	public MyConstructor(String name) {
		this.name = validateThat(name, not(isEmptyOrNullString()));
	}


or

    validateThat("parameter 'name has an invalid value", name, not(isEmptyOrNullString()));

If it fails to validate the parameter, an Exception is thrown:

    Exception in thread "main" java.lang.IllegalArgumentException: parameter 'name has an invalid value
    Expected: not (null or an empty string)
         but: was null
    	at info.gehrels.parameterValidation.MatcherValidation.validateThat(MatcherValidation.java:40)
    	...

How to get this?
----------------
You will (soon) find the lib at Maven Central. Just add the following lines to your pom.xml:

    <dependency>
    	<groupId>info.gehrels</groupId>
        <artifactId>parameter-validation</artifactId>
    	<version>1.0</version>
    </dependency>

How is it licensed?
-------------------
Since the code ist mostly copied from the original Hamcrest sources, i do not claim copyright myself. The code therefore
falls under Hamcrests BSD 3-Clause License. It may be found at
https://raw.github.com/hamcrest/JavaHamcrest/master/LICENSE.txt