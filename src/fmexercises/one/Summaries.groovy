package fmexercises.one

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

println "Exercise 1"
// Read from json file
JsonSlurper slurper = new JsonSlurper()
def json = slurper.parse(new File('price.json'))

List products = json as List

// a)	Number of products per category
// economy: price < 10, standard: 10 - 99, premium: 100+
List economyProducts = products.findAll { product ->  product.price.toDouble() < 10 }
//println economyProducts

List standardProducts = products.findAll {
    product ->  product.price.toDouble() >= 10 && product.price.toDouble() <= 99
}
//println standardProducts

List premiumProducts = products.findAll {
    product ->  product.price.toDouble() >= 100
}
//println premiumProducts

println "Economy"
println economyProducts.size()
println "Standard"
println standardProducts.size()
println "Premium"
println premiumProducts.size()

// b)	Number of products per product type
//If name or description contain any of the following words: coat, jacket, sweater, jeans,
// jersey, pullover, pants, shirt, t-shirt, tank	Clothing
//If name contains vitamin, tablet, caplet, capsule	health products
//In all other cases other

// Clothing, health products, other
// makes it case insensitive
def pattern1 = /(?i)coat/
def pattern2 = /(?i)jacket/
def pattern3 = /(?i)sweater/
def pattern4 = /(?i)jeans/
def pattern5 = /(?i)jersey/
def pattern6 = /(?i)pullover/
def pattern7 = /(?i)pants/
def pattern8 = /(?i)shirt/
def pattern9 = /(?i)t-shirt/
def pattern10 = /(?i)tank/

// Solution for now, have to find out how to combine patterns
List clothingTypeProducts = products.findAll {
    product ->
        product.name =~ pattern1 || product.description =~ pattern1 ||
                product.name =~ pattern2 || product.description =~ pattern2 ||
                product.name =~ pattern3 || product.description =~ pattern3 ||
                product.name =~ pattern4 || product.description =~ pattern4 ||
                product.name =~ pattern5 || product.description =~ pattern5 ||
                product.name =~ pattern6 || product.description =~ pattern6 ||
                product.name =~ pattern7 || product.description =~ pattern7 ||
                product.name =~ pattern8 || product.description =~ pattern8 ||
                product.name =~ pattern9 || product.description =~ pattern9 ||
                product.name =~ pattern10 || product.description =~ pattern10
}

println "Clothing Type Products"
println clothingTypeProducts.size()

// vitamin, tablet, caplet, capsule
def healthPattern1 = /(?i)vitamin/
def healthPattern2 = /(?i)tablet/
def healthPattern3 = /(?i)caplet/
def healthPattern4 = /(?i)capsule/

List healthTypeProducts = products.findAll {
    product ->
        product.name =~ healthPattern1 || product.description =~ healthPattern1 ||
                product.name =~ healthPattern2 || product.description =~ healthPattern2 ||
                product.name =~ healthPattern3 || product.description =~ healthPattern3 ||
                product.name =~ healthPattern4 || product.description =~ healthPattern4
}

println "Health Type Products"
println healthTypeProducts.size()

println "Other Type Products"
def otherTypeProducts = products -= clothingTypeProducts
otherTypeProducts = products -= healthTypeProducts

println otherTypeProducts.size()

// Exercise 2
// Create two new files
// a) By price category, within each category, ordered by id
//JsonBuilder builder = new JsonBuilder()

JsonBuilder builder = new JsonBuilder()

builder.products {
    economy(economyProducts.sort {it.id})
    standard(standardProducts.sort {it.id})
    premium(premiumProducts.sort {it.id})
}

new File('data/productsByPriceCategory.json').write(builder.toPrettyString())

// b) By product type, ordered by price within each product type
JsonBuilder productTypebuilder = new JsonBuilder()

productTypebuilder.products {
    clothing(clothingTypeProducts.sort {it.price.toDouble()})
    health(healthTypeProducts.sort {it.price.toDouble()})
    other(otherTypeProducts.sort {it.price.toDouble()})
}

new File('data/productsByProductType.json').write(productTypebuilder.toPrettyString())

println "Exercise 3"
// Exercise 3
// 3. Validate outliers in each product category.
// Find the accepted price range:  between 25% and 200% of the average price in the category.
// Identify the minimum and maximum price points and list all items that fall outside the
// range and would be considered outliers.

// economy, standard, premium
// find average, .25 and 2 of average to find min and max
// Display outliers
// the sum of the numbers divided by the total number of values in the set

// economy
Double getAverage(List products) {
    def sum = 0

    for (product in products) {
        sum += product.price.toDouble()
    }

    return sum / products.size()
}

println "Economy"
println "Average: ${getAverage(economyProducts)}"
def twentyFive = 0.25 * getAverage(economyProducts)
def twoHundred = 2 * getAverage(economyProducts)
//println "25%: $twentyFive"
//println "200%: $twoHundred"
// < 25 or > 200
def economyOutliers = economyProducts.findAll {
    product ->
        product.price.toDouble() < twentyFive || product.price.toDouble() > twoHundred
}
println economyOutliers

println "Standard"
println "Average: ${getAverage(standardProducts)}"
def twentyFiveStandard = 0.25 * getAverage(standardProducts)
def twoHundredStandard = 2 * getAverage(standardProducts)
//println "25%: $twentyFiveStandard"
//println "200%: $twoHundredStandard"
// < 25 or > 200
def standardOutliers = standardProducts.findAll {
    product ->
        product.price.toDouble() < twentyFiveStandard ||
                product.price.toDouble() > twoHundredStandard
}
println standardOutliers

//def premiumMax = premiumProducts.max { it.price.toDouble() }
//def premiumMin = premiumProducts.min { it.price.toDouble() }
println "Premium"
println "Average: ${getAverage(premiumProducts)}"
def twentyFivePremium = 0.25 * getAverage(premiumProducts)
def twoHundredPremium = 2 * getAverage(premiumProducts)
// < 25 or > 200
def premiumOutliers = premiumProducts.findAll {
    product ->
        product.price.toDouble() < twentyFivePremium ||
                product.price.toDouble() > twoHundredPremium
}
println premiumOutliers


