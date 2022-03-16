package fmexercises.two

// jdbc:postgresql://localhost:5432/groovytodo
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient

String username = "postgres"
String password = "paddle81"

def sql = Sql.newInstance("jdbc:postgresql://localhost:5432/fmexercises",
        username, password)

println "Connected to db."

// create schema
sql.execute('DROP TABLE IF EXISTS products')
sql.execute '''
CREATE TABLE products (
      id INT NOT NULL,
      name VARCHAR(200) NOT NULL,
      description TEXT NULL,
      price DECIMAL,
      image_title VARCHAR(200) NOT NULL,
      image VARCHAR(200) NOT NULL,
      PRIMARY KEY (id)
  );
'''

String base = 'https://hplussport.com'
def client = new RESTClient(base)
def data
client.contentType = ContentType.JSON
client.get(path: '/api/products/order/price') { response, json ->
    // println response.status
    data = json
}

List<Product> productList = data.collect {
    new Product(id:it.id.toInteger(), name:it.name, description:it.description, price:it.price.toBigDecimal(),
            imageTitle: it.image_title, image:it.image)
}

def ECONOMY_MAX = 10

List<Product> economyProducts = productList.findAll { product ->
    product.getPrice() < ECONOMY_MAX }

 economyProducts.each {
     sql.execute """
         INSERT INTO products (id,name,description,price,image_title,image)
         VALUES
         ($it.id, $it.name, $it.description, 
            $it.price, $it.imageTitle, $it.image)
     """
 }

//for (product in economyProducts) {
//    sql.execute """
//         INSERT INTO products (id,name,description,price,image_title,image)
//         VALUES
//         ($product.id, $product.name, $product.description,
//            $product.price, $product.imageTitle, $product.image)
//     """
//}

// Best practice from Howard
//sortedById.each {element ->
//    sql.execute("""INSERT INTO category_then_id (id, name, description, price, imageTitle, image)
//VALUES
//    (?,?,?,?,?,?)
//""",new ArrayList([element.id.toInteger(),element.name,element.description,element.price.toBigDecimal(),element.imageTitle,element.image]))
//}

// Create a new file to hold products in and put in the header values
//def file = new File('products.csv')
//file.write("id,name,description,price,image_title,image\n")
//
//sql.eachRow('select * from products') { row ->
//    file.append("${row.id},${row.name},${row.description}" +
//            ",${row.price},${row.image_title},${row.image}\n")
//}


// close the connection
sql.close()
println "Completed!"
