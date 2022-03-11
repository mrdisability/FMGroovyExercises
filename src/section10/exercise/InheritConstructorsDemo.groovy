package section10.exercise

import groovy.transform.InheritConstructors

// The @InheritConstructor AST transformation aims at generating constructors
// matching super constructors for you
@InheritConstructors
class CustomException extends Exception {}

// all those are generated constructors
//new CustomException()
//new CustomException("A custom message")
//new CustomException("A custom message", new RuntimeException())
//new CustomException(new RuntimeException())
//new CustomException(new ArrayIndexOutOfBoundsException())

try {
    def arr = new int[3]
    arr[5] = 5;
} catch(ArrayIndexOutOfBoundsException ex) {
    CustomException customException = new CustomException("Custom message",
        ex)
    println customException.getProperties()
    println customException.getProperty("cause")
}
