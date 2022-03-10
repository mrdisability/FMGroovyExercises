package fmexercises.two

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper

println "Exercise 1"
JsonSlurper slurper = new JsonSlurper()
def json = slurper.parse(new File('price.json'))

List products = json as List

// Constants
def ECONOMY_MAX = 10
def STANDARD_MIN = 10
def STANDARD_MAX = 99
def PREMIUM_MIN = 100

// a) Number of products per category - economy: price < 10, standard: 10 - 99, premium: 100+
List economyProducts = products.findAll { product ->
    product.price.toDouble() < ECONOMY_MAX }

List standardProducts = products.findAll {
    product ->  product.price.toDouble() >= STANDARD_MIN &&
            product.price.toDouble() <= STANDARD_MAX
}

List premiumProducts = products.findAll {
    product ->  product.price.toDouble() >= PREMIUM_MIN
}

println "Economy Total: ${economyProducts.size()}"
println "Standard Total: ${standardProducts.size()}"
println "Premium Total: ${premiumProducts.size()}"

// b)	Number of products per product type
//If name or description contain any of the following words: coat, jacket, sweater, jeans,
// jersey, pullover, pants, shirt, t-shirt, tank	Clothing
//If name contains vitamin, tablet, caplet, capsule	health products
//In all other cases other

def clothingPattern = ~/(?i)(coat|jacket|sweater|jeans|jersey|pullover|pants|shirts|t-shirt|tank)/
def healthProductsPattern = ~/(?i)(vitamin|tablet|caplet|capsule)/

// name or description
List clothingTypeProducts = products.findAll {
    product ->
        product.name =~ clothingPattern || product.description =~ clothingPattern
}

println "Clothing Type Products Total: ${clothingTypeProducts.size()}"

// name
List healthTypeProducts = products.findAll {
    product ->
        product.name =~ healthProductsPattern
}

println "Health Type Products Total: ${healthTypeProducts.size()}"

def otherTypeProducts = products -= clothingTypeProducts
otherTypeProducts = products -= healthTypeProducts

println "Other Type Products Total: ${otherTypeProducts.size()}"

// Exercise 2
// Create two new files
// a) By price category, within each category, ordered by id

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

println()
println "Exercise 3"
// Exercise 3
// 3. Validate outliers in each product category.
// Find the accepted price range:  between 25% and 200% of the average price in the category.
// Identify the minimum and maximum price points and list all items that fall outside the
// range and would be considered outliers.

// economy, standard, premium
// Find average, 25% (.25) and 200% (2) of average to find min and max
// Average: Sum of the numbers divided by the total number of values in the set

// Calculate using collect
Double getAverage(List products) {
    def sum = 0

    for (product in products) {
        sum += product.price.toDouble()
    }

    return sum / products.size()
}

println "Economy"
// println "Average: ${getAverage(economyProducts)}"
def twentyFivePercentOfEconomyAverage = 0.25 * getAverage(economyProducts)
def twoHundredPercentOfEconomyAverage = 2 * getAverage(economyProducts)
// < 25 or > 200
def economyOutliers = economyProducts.findAll {
    product ->
        product.price.toDouble() < twentyFivePercentOfEconomyAverage ||
                product.price.toDouble() > twoHundredPercentOfEconomyAverage
}
println economyOutliers

println "Standard"
def twentyFivePercentOfStandardAverage = 0.25 * getAverage(standardProducts)
def twoHundredPercentOfStandardAverage = 2 * getAverage(standardProducts)
// < 25 or > 200
def standardOutliers = standardProducts.findAll {
    product ->
        product.price.toDouble() < twentyFivePercentOfStandardAverage ||
                product.price.toDouble() > twoHundredPercentOfStandardAverage
}
println standardOutliers

//def premiumMax = premiumProducts.max { it.price.toDouble() }
//def premiumMin = premiumProducts.min { it.price.toDouble() }
println "Premium"
def twentyFivePercentOfPremiumAverage = 0.25 * getAverage(premiumProducts)
def twoHundredPercentOfPremiumAverage = 2 * getAverage(premiumProducts)
// < 25 or > 200
def premiumOutliers = premiumProducts.findAll {
    product ->
        product.price.toDouble() < twentyFivePercentOfPremiumAverage ||
                product.price.toDouble() > twoHundredPercentOfPremiumAverage
}
println premiumOutliers
