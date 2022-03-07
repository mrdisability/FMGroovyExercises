package fmexercises

import groovy.json.JsonSlurper

// Read from json file
JsonSlurper slurper = new JsonSlurper()
def json = slurper.parse(new File('price.json'))

List products = json

// a)	Number of products per category
// b)	Number of products per product type

