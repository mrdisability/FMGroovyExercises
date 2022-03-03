package section5.exercise

// Create a list days (Sun - Wednesday)
def days = ["Sunday", "Monday", "Tuesday", "Wednesday"];

// Print out the list
println(days);

// Print the size of the list
println(days.size());

// Remove Wednesday from the list
days.removeLast();
println(days);

// Add Wednesday back by appending it to the list
days << "Wednesday";
println(days);

// Print out Wednesday using its index
println(days[3]);
