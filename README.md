parameter-validation
====================

Validating input parameters of methods and constructors is a recurring and stupid task, especially when you are 		writing public APIs. This module tries to ease your life a bit by providing a static validateThat() method, allowing you to throw IllegalArgumentExceptions using hamcrest matchers.