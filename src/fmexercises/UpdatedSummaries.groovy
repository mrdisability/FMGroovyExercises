package fmexercises

import groovy.json.JsonSlurper

println "Exercise 1"
// Read from json file
JsonSlurper slurper = new JsonSlurper()
def json = slurper.parse(new File('price.json'))

List products = json as List

// Clothing, health products, other
// makes it case insensitive
def clothingPattern = /(?i)(coat|jacket|sweater|jeans|jersey|pullover|pants|shirt|t-shirt|tank)/

// Solution for now, have to find out how to combine patterns
List clothingTypeProducts = products.findAll {
    product ->
        product.name =~ clothingPattern || product.description =~ clothingPattern
}

println "Clothing Type Products"
println clothingTypeProducts.size()

def healthPattern = /(?i)(vitamin|tablet|caplet|capsule)/

List healthTypeProducts = products.findAll {
    product ->
        product.name =~ healthPattern || product.description =~ healthPattern
}

println "Health Type Products"
println healthTypeProducts.size()

println "Other Type Products"
def otherTypeProducts = products -= clothingTypeProducts
otherTypeProducts = products -= healthTypeProducts

println otherTypeProducts.size()
