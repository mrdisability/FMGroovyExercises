package exercism

//Given a name, return a string with the message:
//
//        One for name, one for me.
//        Where "name" is the given name.
//
//        However, if the name is missing, return the string:
//
//One for you, one for me.

//Alice	One for Alice, one for me.
//Bob	One for Bob, one for me.
//One for you, one for me.
//Zaphod	One for Zaphod, one for me.

static String twoFer(String name) {
    if (name == null) {
        return "One for you, one for me."
    } else if (name.isEmpty()){
        return "One for you, one for me."
    } else {
        return "One for $name, one for me."
    }
}

println twoFer("Alice")
println twoFer("Bob")
println twoFer()
println twoFer("Zaphod")