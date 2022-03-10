package section9.exercise

// The Expando class in Groovy is sort of a dynamic bean. We can add properties and
// we can add closures as methods to an instance of the Expando class. This is useful
// if we don't want to create a new class for a simple bean.

//def user = new Expando(username: 'mrhaki')
//assert 'mrhaki' == user.username
//
//// Add an extra property.
//user.email = 'email@host.com'
//assert 'email@host.com' == user.email
//
//// Assign closure as method. The closure can
//// take parameters.
//user.printInfo = { writer ->
//    writer << "Username: $username"
//    writer << ", email: $email"
//}
//
//def sw = new StringWriter()
//user.printInfo(sw)
//assert 'Username: mrhaki, email: email@host.com' == sw.toString()

Expando e = new Expando()

e.firstName = "King"
e.lastName  = "Ioane"
e.email = "king@gmail.com"

e.getFullName = {
    "$firstName $lastName"
}

println e.toString()
println e.getFullName()

@groovy.transform.ToString(includeNames = true)
class Person {
    String firstName, lastName
}

Person person = new Person(firstName: "King", lastName: "Ioane")
// Sets property
person.metaClass.email = "king@gmail.com"
println person
println person.email

println()
println "Customer"
Customer customer = new Customer(firstName: "King", lastName: "Ioane",
        email: "king@gmail.com")
customer.buyProduct("Pizza")
println customer.firstName
customer.products = ["Pizza", "Burger King"]

println()
println "Times Two"
//metaClass - changing property or method of a class
Integer.metaClass.timesTwo = {delegate * 2}

println 2.timesTwo()
println 10.timesTwo()
