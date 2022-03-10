package fmexercises.one

import groovy.json.JsonSlurper
import org.apache.groovy.json.internal.LazyMap

import java.util.regex.Pattern

File productJSONFile = new File('price.json')
JsonSlurper jsonReader = new JsonSlurper()
ArrayList<LazyMap> data = jsonReader.parse(productJSONFile) as ArrayList<LazyMap>

Pattern clothingPattern = ~/(?i)(coat|jacket|sweater|jeans|jersey|pullover|pants|shirts|t-shirt|tank)/
Pattern healthProductsPattern = ~/(?i)(vitamin|tablet|caplet|capsule)/

data.each { Map mapInData ->
    // adds price category to the data
    BigDecimal price = mapInData.get("price").toBigDecimal()
    if (price < 10) {
        mapInData.put("category", "economy")
    } else if (price >=10 && price <100) {
        mapInData.put("category", "standard")
    } else {
        mapInData.put("category", "premium")
    }

    // adds product category to the data
    if (mapInData.get("name") =~ clothingPattern || mapInData.get("description") =~ clothingPattern) {
        mapInData.put("product type", "clothing")
    } else if (mapInData.get("name") =~ healthProductsPattern ||
            mapInData.get("description") =~ healthProductsPattern) {
        mapInData.put("product type", "health products")
    } else {
        mapInData.put("product type", "other")
    }
}

static Map<String,Integer> numberOfProductsByCategory(ArrayList<LazyMap> data){
    Map<String, Integer> returnMap = new HashMap<String, Integer>()

    returnMap.put("economy", getCountWhereKeyEqualsValue(data, "category", "economy"))
    returnMap.put("standard", getCountWhereKeyEqualsValue(data, "category", "standard"))
    returnMap.put("premium", getCountWhereKeyEqualsValue(data, "category", "premium"))

    return returnMap
}

static Map<String,Integer> numberOfProductsByProductType(ArrayList<LazyMap> data){
    Map<String, Integer> returnMap = new HashMap<String, Integer>()

    returnMap.put("clothing", getCountWhereKeyEqualsValue(data, "product type", "clothing"))
    returnMap.put("health products", getCountWhereKeyEqualsValue(data, "product type", "health products"))
    returnMap.put("other", getCountWhereKeyEqualsValue(data, "product type", "other"))

    return returnMap
}

private static int getCountWhereKeyEqualsValue(ArrayList<LazyMap> data, String key, String value){
    int count = 0
    data.each{ LazyMap mapInData ->
        if (mapInData.get(key)==value) {
            count++
        }
    }
    return count
}

Map<String, Map<Integer, BigDecimal>> result = new HashMap()
BigDecimal economyCount = 0
BigDecimal economyPrice = 0

BigDecimal standardCount = 0
BigDecimal standardPrice = 0

BigDecimal premiumCount = 0
BigDecimal premiumPrice = 0

data.each {mapInData ->
    if(mapInData.get("category") == "economy"){
        economyCount++
        economyPrice += mapInData.get("price").toBigDecimal()
    }
    else if(mapInData.get("category") == "standard"){
        standardCount++
        standardPrice += mapInData.get("price").toBigDecimal()
    }
    else{
        premiumCount++
        premiumPrice += mapInData.get("price").toBigDecimal()
    }
}

static Map<Integer, BigDecimal> getPercentiles(BigDecimal price, BigDecimal count){
    Map<Integer, BigDecimal> returnMap = new HashMap()
    BigDecimal average = price/count
    returnMap.put(25, average*0.25)
    returnMap.put(100, average)
    returnMap.put(200, average*2)

    return returnMap
}

economyMap = getPercentiles(economyPrice, economyCount)
standardMap = getPercentiles(standardPrice, standardCount)
premiumMap = getPercentiles(premiumPrice, premiumCount)
result.put("economy", economyMap)
result.put("standard", standardMap)
result.put("premium", premiumMap)

println(result.toString())


println (data.findAll() {
    ((it.get("category") == "economy") &&
            (it.get("price").toBigDecimal() < result.get("economy").get(25) ||
                    it.get("price").toBigDecimal() > result.get("economy").get(200)))
})

println (data.findAll() {
    ((it.get("category") == "standard") &&
            (it.get("price").toBigDecimal() < result.get("standard").get(25) ||
                    it.get("price").toBigDecimal() > result.get("standard").get(200)))
})

println (data.findAll() {
    ((it.get("category") == "premium") &&
            (it.get("price").toBigDecimal() < result.get("premium").get(25) ||
                    it.get("price").toBigDecimal() > result.get("premium").get(200)))
})
