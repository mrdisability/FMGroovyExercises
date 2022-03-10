package fmexercises.two

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient

// RESTClient dependency
// Have to downgrade groovy for it to work
// Add module through Project Structure from Maven repo
// org.codehaus.groovy.modules.http-builder:http-builder:0.7.1

String base = 'https://hplussport.com'

def client = new RESTClient(base)

def data

client.contentType = ContentType.JSON
client.get(path: '/api/products/order/price') { response, json ->
    // println response.status
    data = json
}

//JsonSlurper slurper = new JsonSlurper()
//def json = slurper.parse(new File('price.json'))

List products = data as List

// Constants
def ECONOMY_MAX = 10
def STANDARD_MIN = 10
def STANDARD_MAX = 99
def PREMIUM_MIN = 100

// a) Number of products per category - economy: price < 10, standard: 10 - 99, premium: 100+
List economyProducts = products.findAll { product ->
    product.price.toBigDecimal() < ECONOMY_MAX }

List standardProducts = products.findAll {
    product ->  product.price.toBigDecimal() >= STANDARD_MIN &&
            product.price.toBigDecimal() <= STANDARD_MAX
}

List premiumProducts = products.findAll {
    product ->  product.price.toBigDecimal() >= PREMIUM_MIN
}

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

// name
List healthTypeProducts = products.findAll {
    product ->
        product.name =~ healthProductsPattern
}

def otherTypeProducts = products -= clothingTypeProducts
otherTypeProducts = products -= healthTypeProducts

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
    clothing(clothingTypeProducts.sort {it.price.toBigDecimal()})
    health(healthTypeProducts.sort {it.price.toBigDecimal()})
    other(otherTypeProducts.sort {it.price.toBigDecimal()})
}

new File('data/productsByProductType.json').write(productTypebuilder.toPrettyString())

// Exercise 3
// 3. Validate outliers in each product category.
// Find the accepted price range:  between 25% and 200% of the average price in the category.
// Identify the minimum and maximum price points and list all items that fall outside the
// range and would be considered outliers.

// economy, standard, premium
// Find average, 25% (.25) and 200% (2) of average to find min and max
// Average: Sum of the numbers divided by the total number of values in the set

// Calculate using collect
//Double getAverage(List products) {
//    def sum = 0
//
//    for (product in products) {
//        sum += product.price.toBigDecimal()
//    }
//
//    return sum / products.size()
//}

BigDecimal getAverageThree(List productList) {
    BigDecimal average = productList.stream()
            .mapToDouble(product -> product.price.toDouble())
            .average()
            .orElse(0)
    return average
}

BigDecimal getAverage(List productList) {
    BigDecimal average = productList.sum {it.price.toBigDecimal()} / productList.size()
    return average
}

BigDecimal getAverageTwo(List productList) {
    return productList.average {it.price.toBigDecimal()} as BigDecimal
}

println "Average One: ${getAverage(economyProducts)}"
println "Average Two: ${getAverageTwo(economyProducts)}"
println "Average Three: ${getAverageThree(economyProducts)}"

def twentyFivePercentOfEconomyAverage = 0.25 * getAverage(economyProducts)
def twoHundredPercentOfEconomyAverage = 2 * getAverage(economyProducts)
// < 25 or > 200
def economyOutliers = economyProducts.findAll {
    product ->
        product.price.toBigDecimal() < twentyFivePercentOfEconomyAverage ||
                product.price.toBigDecimal() > twoHundredPercentOfEconomyAverage
}

def twentyFivePercentOfStandardAverage = 0.25 * getAverage(standardProducts)
def twoHundredPercentOfStandardAverage = 2 * getAverage(standardProducts)
// < 25 or > 200
def standardOutliers = standardProducts.findAll {
    product ->
        product.price.toBigDecimal() < twentyFivePercentOfStandardAverage ||
                product.price.toBigDecimal() > twoHundredPercentOfStandardAverage
}

//def premiumMax = premiumProducts.max { it.price.toDouble() }
//def premiumMin = premiumProducts.min { it.price.toDouble() }
def twentyFivePercentOfPremiumAverage = 0.25 * getAverage(premiumProducts)
def twoHundredPercentOfPremiumAverage = 2 * getAverage(premiumProducts)
// < 25 or > 200
def premiumOutliers = premiumProducts.findAll {
    product ->
        product.price.toBigDecimal() < twentyFivePercentOfPremiumAverage ||
                product.price.toBigDecimal() > twoHundredPercentOfPremiumAverage
}

println "Exercise 1"
println "Economy Total: ${economyProducts.size()}"
println "Standard Total: ${standardProducts.size()}"
println "Premium Total: ${premiumProducts.size()}"
println "Clothing Type Products Total: ${clothingTypeProducts.size()}"
println "Health Type Products Total: ${healthTypeProducts.size()}"
println "Other Type Products Total: ${otherTypeProducts.size()}"

println()
println "Exercise 3"
println "Economy Outliers"
println economyOutliers
println "Standard Outliers"
println standardOutliers
println "Premium Outliers"
println premiumOutliers



