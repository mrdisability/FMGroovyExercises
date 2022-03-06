package exercism

import org.apache.groovy.parser.antlr4.util.StringUtils

// GAGCCTACTAACGGGAT
// CATCGTAATGACGGCCT
// compare two strands of DNA and count the differences between them

// The general handling of this situation (e.g., raising an exception vs returning a special value)
// may differ between languages.

// The Hamming distance is only defined for sequences of equal length, so an attempt to calculate it between
// sequences of different lengths should not work. The general handling of this situation (e.g., raising an exception
// vs returning a special value) may differ between languages.

def distance(strand1, strand2) {
    def difference = 0

    // check strands for equal length
    // if not then throw exception

    if (strand1.length() == strand2.length()) {
        // Go through each strand and compare string at each position
        for (int i = 0; i < strand1.length(); i++) {
            // println strand1[i]

            // Compare to string at same position on strand2
            if (strand1[i] != strand2[i]) {
                difference += 1
            }
        }
    }else {
        throw new ArithmeticException("Strands have to be equal")
    }

    return difference
}

println distance("GAGCCTACTAACGGGAT", "CATCGTAATGACGGCCT")

def computeDistance(strand1, strand2) {
    [strand1, strand2]*.toList().transpose().count { a, b -> a != b }
}

println computeDistance("GAGCCTACTAACGGGAT", "CATCGTAATGACGGCCT")
