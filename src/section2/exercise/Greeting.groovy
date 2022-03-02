package section2.exercise

import groovy.transform.ToString

@ToString
class Greeting {
    private String greeting;
    private String name;

    public Greeting(String greeting, String name) {
        this.greeting = greeting;
        this.name = name;
    }

    String getGreeting() {
        return greeting;
    }

    void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getGreetingWithName() {
        return "${this.greeting} ${this.name}!";
    }
}
