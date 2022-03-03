package section6.exercise

def printWelcome = {
    println "Welcome to Closures!"
}

def print = { name ->
    println name
}

def formatToLowerCase(name) {
    return name.toLowerCase()
}
def formatToLowerCaseClosure = { name ->
    return name.toLowerCase()
}

// Closure - can call like any other method or use call
print("Hello! Closure")
formatToLowerCaseClosure("Hello! Closure")

print.call("Hello! Closure")
formatToLowerCaseClosure.call("Hello! Closure")

def greet = {
    return "Hello! ${it}"
}
assert greet("Alex") == "Hello! Alex"
println greet.call("King")
println greet("King")

// Multiple parameters
def multiply = { x, y ->
    return x * y
}
assert multiply(2, 4) == 8
println multiply(2, 4)

def calculate = {int x, int y, String operation ->
    def result = 0
    switch(operation) {
        case "ADD":
            result = x+y
            break
        case "SUB":
            result = x-y
            break
        case "MUL":
            result = x*y
            break
        case "DIV":
            result = x/y
            break
    }
    return result
}
assert calculate(12, 4, "ADD") == 16
assert calculate(43, 8, "DIV") == 5.375

//So far, we've seen the syntax, execution, and parameters of closures, which are fairly similar to methods.
// Let's now compare closures with methods.
//
//Unlike a regular Groovy method:
//
//We can pass a Closure as an argument to a method
//Unary closures can use the implicit it parameter
//We can assign a Closure to a variable and execute it later, either as a method or with call
//Groovy determines the return type of the closures at runtime
//We can declare and invoke closures inside a closure
//Closures always return a value
//Hence, closures have benefits over regular methods and are a powerful feature of Groovy.
