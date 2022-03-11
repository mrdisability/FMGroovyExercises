package section10.exercise

// @MapConstructor creates a map constructor
def customer = new Customer(firstName: 'King', lastName: 'Ioane')
assert customer.toString() == 'section10.exercise.Customer(King, Ioane)'

// The @MapConstructor annotation aims at eliminating boilerplate code by generating a
// map constructor for you. A map constructor is created such that each property in the
// class is set based on the value in the supplied map having the key with the name of the
// property. Usage is as shown in this example:

// Generates this map contructor
//public Customer(Map args) {
//    if (args.containsKey('firstName')) {
//        this.firstName = args.get('firstName')
//    }
//    if (args.containsKey('lastName')) {
//        this.lastName = args.get('lastName')
//    }
//}
