package section3.exercise

class PersonDemo {
    static void main(String[] args) {
        Person person = new Person("King", 28);
        println person;

        // @Canonical
        // You don't need to provide all arguments in constructor calls. If using named parameters, any property
        // names not referenced will be given their default value (as per Java's default unless an explicit initialization
        // constant is provided when defining the property). If using a tuple constructor, parameters are supplied
        // in the order in which the properties are defined. Supplied parameters fill the tuple from the left.
        // Any parameters missing on the right are given their default value.
        // Similar to @Immutable
        Person person2 = new Person("King", 28);
        println person2;
    }
}
