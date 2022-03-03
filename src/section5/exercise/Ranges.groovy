package section5.exercise

public enum DAYS {
    Sunday, Monday, Tuesday
}

// Print size of range
def range = DAYS.Sunday..DAYS.Tuesday;
println(range.size());

// Use the contains method to see if Wednesday is in that Range
println(range.contains(DAYS.Monday));

// Print the from element of this range
println(range.from);

// Print the to element of this range
println(range.to);
