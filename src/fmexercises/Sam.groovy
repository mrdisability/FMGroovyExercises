package fmexercises

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import java.util.regex.Pattern

//Define filename to be read
filename = 'price.json'
//Testing a thing
//Files to be written to
File file = new File("productsOrderedByPrice.json")
File file1 = new File("productsOrderedByType.json")

//JsonSlurper to read jsons
def jsonParser = new JsonSlurper()
def values = jsonParser.parse(new File(filename))

Pattern validClothing = ~/(?i)(coat|jacket|sweater|jeans|jersey|pullover|pants|shirts|t-shirt|tank)/
Pattern validHealth = ~/(?i)(vitamin|tablet|caplet|capsule)/

//Generate lists according to price - Note exclusions include values between 9-10, and 99-100
economy = values.collect { ele -> if (ele.price.toDouble() < 10) { ele } else { null } }
standard = values.collect { ele -> if (ele.price.toDouble() >= 10 && ele.price.toDouble() <= 99) { ele } else { null } }
premium = values.collect { ele -> if (ele.price.toDouble() >= 100) { ele } else { null } }

//Generate lists according to product type
//Probably want something more verbose than this. This statement compares list sizes to see changes
clothing = values.collect { ele -> if (ele.name =~ (validClothing) || ele.description =~ (validClothing)){ ele } else { null } }
healthProducts = values.collect { ele -> if(ele.name =~ (validHealth) || ele.description =~ (validHealth)){ ele } else { null } }
others = values.collect { ele -> if (clothing.contains(ele) || healthProducts.contains(ele)) { null } else { ele } }

//Prints out sizes of lists
println("Number of Products per category: \nEconomy:" + (economy - null).size() + "\nStandard:" + (standard - null).size() + "\nPremium:" + (premium - null).size())
println()
println("Number of Products per product type: \nClothing:" + (clothing - null).size() + "\nHealth Products:" + (healthProducts - null).size() + "\nOthers:" + (others - null).size())
println()
//Create list to add sorted values to.
sortedPriceId = []
//Sorted by Economy>Standard>Premium, by ID
sortedPriceId.add((economy - null).sort { a, b -> a['id'] <=> b['id'] })
sortedPriceId.add((standard - null).sort { a, b -> a['id'] <=> b['id'] })
sortedPriceId.add((premium - null).sort { a, b -> a['id'] <=> b['id'] })

//Write to file
file.write(JsonOutput.toJson(sortedPriceId))
println("Written to " + file.name)

sortedProductCategory = []

//Combines the elements into a list after sorting them via price in their own category.
sortedProductCategory.add((clothing - null).sort { a, b -> a['price'] <=> b['price'] })
sortedProductCategory.add((healthProducts - null).sort { a, b -> a['price'] <=> b['price'] })
sortedProductCategory.add((others - null).sort { a, b -> a['price'] <=> b['price'] })

file1.write(JsonOutput.toJson(sortedProductCategory))
println("Written to " + file1.name)

//Small function to find average of list
def avg(List l) {
    sum = 0
    for (i in (l - null)) {
        sum += i['price'] as Double
    }
    sum /= (l - null).size()
    //return sum
}

//Function to find outliers based on the average.
def findItems(List l) {
    println()
    //Made the variables more verbose to prevent possible method calls.
    List outliers = []
    double low = avg(l) * 0.25
    double high = avg(l) * 2
    for (i in (l - null)) {
        double x = i['price'] as Double
        if (x <= low || x >= high) {
            outliers.add(i)
        }
    }
    println("min: $low \tmax: $high \tOutliers:${outliers['name']}")
    return outliers
}

println(findItems(economy))
println(findItems(standard))
println(findItems(premium))