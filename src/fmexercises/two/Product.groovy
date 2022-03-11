package fmexercises.two

class Product {
    String id
    String name
    String description
    BigDecimal price
    String imageTitle
    String image

    Product(String id, String name, String description,
            BigDecimal price, String imageTitle, String image) {
        this.id = id
        this.name = name
        this.description = description
        this.price = price
        this.imageTitle = imageTitle
        this.image = image
    }
}

// [{"id":"532","name":"Slicker Jacket","description":"Wind and rain are no match for our
// organic bamboo slicker jacket for men and women. Triple stitched seams, zippered pockets,
// and a stay-tight hood are just a few features of our best-selling jacket.","price":"125",
// "image_title":"slicker-jacket_lynda_29941","image":"https://hplussport.com/wp-content
// /uploads/2016/12/slicker-jacket_LYNDA_29941.jpg"},
