package section2.exercise

class HelloWorld {
    static void main(String[] args) {
        println "Hello World";

        printHelloWorld();

        // Greeting with name of person
        Greeting greeting = new Greeting("Hello", "King");
        println greeting.getGreetingWithName();
    }

    static void printHelloWorld() {
        println "Hello World from function";
    }
}
