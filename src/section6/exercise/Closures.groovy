package section6.exercise

def myMethod(Closure c) {
    c();
}

def foo = {
    println "foo...";
}

myMethod(foo)

List names = ["King", "Howard", "Sam"];
names.each { name ->
    println name;
}

Map fruits = [strawberry:"Strawberry", apple:"Apple", banana:"Banana"];
fruits.each { k,v ->
    println "$k = $v";
}

def greet = { String greeting, String name ->
    println "$greeting, $name";
}

greet("Hello","King");
def sayHello = greet.curry("Hello");
sayHello("Joe");

List people = [
        [name:'King', city:"Auckland"],
        [name:'Sam', city:"Dunedin"],
        [name:'Howard', city:"Auckland"]
];

println people.find { person -> person.city == "Dunedin" }
println people.findAll { person -> person.city == "Auckland" }

println people.any { person -> person.city == "Auckland" }
println people.every { person -> person.city == "Auckland" }
println people.every { person -> person.name.size() >= 3 }

def peopleByCity = people.groupBy { person -> person.city }
println peopleByCity;
def peopleFromAuckland = peopleByCity["Auckland"];
def peopleFromDunedin = peopleByCity.Dunedin;

peopleFromAuckland.each {
    println it.name;
}
