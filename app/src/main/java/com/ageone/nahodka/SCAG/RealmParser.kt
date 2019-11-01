// MARK: Parser

package com.ageone.nahodka.SCAG

import com.ageone.nahodka.External.Extensions.Realm.add
import org.json.JSONObject

class Parser {
fun parseAnyObject(json: JSONObject, type: DataBase) {
	json.optJSONArray(type.name)?.let{array ->
		for (i in 0 until array.length()) {
			val json = array[i] as JSONObject
			val obj = when (type) {
				DataBase.Banner -> {
					json.parseBanner()
				}
				DataBase.CartItem -> {
					json.parseCartItem()
				}
				DataBase.Category -> {
					json.parseCategory()
				}
				DataBase.City -> {
					json.parseCity()
				}
				DataBase.Comment -> {
					json.parseComment()
				}
				DataBase.DistrictPoint -> {
					json.parseDistrictPoint()
				}
				DataBase.Document -> {
					json.parseDocument()
				}
				DataBase.Image -> {
					json.parseImage()
				}
				DataBase.Location -> {
					json.parseLocation()
				}
				DataBase.Order -> {
					json.parseOrder()
				}
				DataBase.Product -> {
					json.parseProduct()
				}
				DataBase.ProductList -> {
					json.parseProductList()
				}
				DataBase.Sale -> {
					json.parseSale()
				}
				DataBase.Shop -> {
					json.parseShop()
				}
				DataBase.User -> {
					json.parseUser()
				}
				}
			add(obj)
		}
	}
}

}

// MARK: Parse JSON to Realm

fun JSONObject.parseBanner(): Banner {
	val some = Banner()
	some.companyHashId = optString("companyHashId", "")
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	optJSONObject("image")?.let { images ->
 		for (i in 0 until images.length()) {
 			some.image.add(
				images.optJSONObject("$i")?.let { image ->
					image.parseImage()
				}
			)
		}
	}
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	some.name = optString("name", "")
	return some
}

fun JSONObject.parseCartItem(): CartItem {
	val some = CartItem()
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	some.productHashId = optString("productHashId", "")
	some.isExist = optBoolean("isExist", false)
	some.count = optInt("count", 0)
	some.updated = optInt("updated", 0)
	some.price = optInt("price", 0)
	some.productName = optString("productName", "")
	return some
}

fun JSONObject.parseCategory(): Category {
	val some = Category()
	optJSONObject("selectedImage")?.let { selectedImage ->
		some.selectedImage = selectedImage.parseImage()
	}
	some.hashId = optString("hashId", "")
	some.serialNum = optInt("serialNum", 0)
	optJSONObject("unselectedImage")?.let { unselectedImage ->
		some.unselectedImage = unselectedImage.parseImage()
	}
	some.created = optInt("created", 0)
	some.__type = optString("__type", "")
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	some.name = optString("name", "")
	optJSONObject("whiteImage")?.let { whiteImage ->
		some.whiteImage = whiteImage.parseImage()
	}
	return some
}

fun JSONObject.parseCity(): City {
	val some = City()
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	optJSONObject("location")?.let { location ->
		some.location = location.parseLocation()
	}
	some.name = optString("name", "")
	some.created = optInt("created", 0)
	return some
}

fun JSONObject.parseComment(): Comment {
	val some = Comment()
	some.userName = optString("userName", "")
	some.companyHashId = optString("companyHashId", "")
	some.hashId = optString("hashId", "")
	some.userHashId = optString("userHashId", "")
	some.created = optInt("created", 0)
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	some.text = optString("text", "")
	some.isPublic = optBoolean("isPublic", false)
	some.rate = optInt("rate", 0)
	return some
}

fun JSONObject.parseDistrictPoint(): DistrictPoint {
	val some = DistrictPoint()
	some.hashId = optString("hashId", "")
	optJSONObject("city")?.let { city ->
		some.city = city.parseCity()
	}
	optJSONObject("location")?.let { location ->
		some.location = location.parseLocation()
	}
	some.created = optInt("created", 0)
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	some.name = optString("name", "")
	return some
}

fun JSONObject.parseDocument(): Document {
	val some = Document()
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.__type = optString("__type", "")
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	some.name = optString("name", "")
	some.txttext = optString("txttext", "")
	return some
}

fun JSONObject.parseImage(): Image {
	val some = Image()
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	some.original = optString("original", "")
	some.created = optInt("created", 0)
	some.preview = optString("preview", "")
	return some
}

fun JSONObject.parseLocation(): Location {
	val some = Location()
	some.latitude = optDouble("latitude", 0.0)
	some.address = optString("address", "")
	some.hashId = optString("hashId", "")
	some.longitude = optDouble("longitude", 0.0)
	some.created = optInt("created", 0)
	some.geoHash = optString("geoHash", "")
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	return some
}

fun JSONObject.parseOrder(): Order {
	val some = Order()
	some.isExist = optBoolean("isExist", false)
	some.floor = optString("floor", "")
	some.updated = optInt("updated", 0)
	some.orderNum = optString("orderNum", "")
	some.address = optString("address", "")
	some.phone = optString("phone", "")
	some.companyHashId = optString("companyHashId", "")
	optJSONObject("items")?.let { itemss ->
 		for (i in 0 until itemss.length()) {
 			some.items.add(
				itemss.optJSONObject("$i")?.let { items ->
					items.parseCartItem()
				}
			)
		}
	}
	some.payMethod = optString("payMethod", "")
	some.cutleryNum = optInt("cutleryNum", 0)
	some.deliveryPrice = optDouble("deliveryPrice", 0.0)
	some.orderPrice = optDouble("orderPrice", 0.0)
	some.hashId = optString("hashId", "")
	some.userHashId = optString("userHashId", "")
	some.porch = optString("porch", "")
	some.intercomCode = optString("intercomCode", "")
	some.__status = optString("__status", "")
	some.room = optString("room", "")
	some.created = optInt("created", 0)
	some.total = optDouble("total", 0.0)
	some.comment = optString("comment", "")
	return some
}

fun JSONObject.parseProduct(): Product {
	val some = Product()
	some.hashId = optString("hashId", "")
	some.about = optString("about", "")
	some.shopName = optString("shopName", "")
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.tag = optString("tag", "")
	some.isExist = optBoolean("isExist", false)
	some.created = optInt("created", 0)
	some.ownerHashId = optString("ownerHashId", "")
	some.name = optString("name", "")
	some.price = optInt("price", 0)
	some.updated = optInt("updated", 0)
	optJSONObject("category")?.let { category ->
		some.category = category.parseCategory()
	}
	return some
}

fun JSONObject.parseProductList(): ProductList {
	val some = ProductList()
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	some.ownerHashId = optString("ownerHashId", "")
	some.name = optString("name", "")
	optJSONObject("products")?.let { productss ->
 		for (i in 0 until productss.length()) {
 			some.products.add(
				productss.optJSONObject("$i")?.let { products ->
					products.parseProduct()
				}
			)
		}
	}
	optJSONObject("category")?.let { category ->
		some.category = category.parseCategory()
	}
	return some
}

fun JSONObject.parseSale(): Sale {
	val some = Sale()
	some.companyHashId = optString("companyHashId", "")
	some.hashId = optString("hashId", "")
	some.value = optInt("value", 0)
	some.created = optInt("created", 0)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.txtAbout = optString("txtAbout", "")
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	optJSONObject("product")?.let { product ->
		some.product = product.parseProduct()
	}
	return some
}

fun JSONObject.parseShop(): Shop {
	val some = Shop()
	some.address = optString("address", "")
	some.hashId = optString("hashId", "")
	optJSONObject("location")?.let { location ->
		some.location = location.parseLocation()
	}
	optJSONObject("city")?.let { city ->
		some.city = city.parseCity()
	}
	some.created = optInt("created", 0)
	some.__type = optString("__type", "")
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	some.ownerHashId = optString("ownerHashId", "")
	some.name = optString("name", "")
	return some
}

fun JSONObject.parseUser(): User {
	val some = User()
	optJSONObject("tags")?.let { arrayString ->
		for (i in 0 until arrayString.length()) {
			some.tags.add(
				arrayString.optString("$i", "")
			)
		}
	}
	some.__type = optString("__type", "")
	some.rating = optDouble("rating", 0.0)
	some.legalInfo = optString("legalInfo", "")
	some.password = optString("password", "")
	some.timeTo = optInt("timeTo", 0)
	some.txtWorkTimeInfo = optString("txtWorkTimeInfo", "")
	some.fcmToken = optString("fcmToken", "")
	some.role = optString("role", "")
	optJSONObject("productLists")?.let { productListss ->
 		for (i in 0 until productListss.length()) {
 			some.productLists.add(
				productListss.optJSONObject("$i")?.let { productLists ->
					productLists.parseProductList()
				}
			)
		}
	}
	some.deliveryFrom = optInt("deliveryFrom", 0)
	some.txtLegalInfo = optString("txtLegalInfo", "")
	some.txtAddressInfo = optString("txtAddressInfo", "")
	some.lastName = optString("lastName", "")
	some.isExist = optBoolean("isExist", false)
	some.commentsNum = optInt("commentsNum", 0)
	some.hashId = optString("hashId", "")
	some.timeFrom = optInt("timeFrom", 0)
	some.created = optInt("created", 0)
	some.phone = optString("phone", "")
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.deviceId = optString("deviceId", "")
	optJSONObject("categories")?.let { categoriess ->
 		for (i in 0 until categoriess.length()) {
 			some.categories.add(
				categoriess.optJSONObject("$i")?.let { categories ->
					categories.parseCategory()
				}
			)
		}
	}
	some.email = optString("email", "")
	some.firstName = optString("firstName", "")
	some.name = optString("name", "")
	some.averageСheck = optInt("averageСheck", 0)
	some.updated = optInt("updated", 0)
	some.isAdmin = optBoolean("isAdmin", false)
	return some
}