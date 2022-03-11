package section10.exercise

import groovy.transform.MapConstructor
import groovy.transform.ToString

@ToString
@MapConstructor
class Customer {
    String firstName
    String lastName
}
