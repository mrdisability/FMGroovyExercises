package section9.exercise


class Customer {

    String firstName
    String lastName
    String email
    List products

    Customer() {

    }

    def invokeMethod(String name, Object args) {
        println "invokeMethod() with args $args"
    }

    def getProperty(String property) {
        println "getProperty called"
        metaClass.getProperty(this,property)
    }

    void setProperty(String property, Object newValue) {
        println "setProperty() called with name $property and newValue $newValue"
        metaClass.setProperty(this,property,newValue)
    }


}
