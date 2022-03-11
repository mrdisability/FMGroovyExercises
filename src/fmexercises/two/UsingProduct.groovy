package fmexercises.two

import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient

String base = 'https://hplussport.com'

def client = new RESTClient(base)

def data

client.contentType = ContentType.JSON
client.get(path: '/api/products/order/price') { response, json ->
    // println response.status
    data = json
}

List<Product> productList = data.collect {
    new Product(it.id, it.name, it.description, it.price.toBigDecimal(),
        it.image_title, it.image)
}

//println productList.each {println it.name}
def ECONOMY_MAX = 10

List<Product> economyProducts = productList.findAll { product ->
    product.getPrice() < ECONOMY_MAX }

println economyProducts.size()

economyProducts.each {println it.getPrice()}
