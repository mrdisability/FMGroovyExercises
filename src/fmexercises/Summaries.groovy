package fmexercises

import groovy.json.JsonSlurper

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

