package section3.exercise

import groovy.transform.Canonical
import groovy.transform.Immutable
import groovy.transform.ToString

//@Immutable
@Canonical
@ToString
class Person {
    String name;
    Integer age;
}
