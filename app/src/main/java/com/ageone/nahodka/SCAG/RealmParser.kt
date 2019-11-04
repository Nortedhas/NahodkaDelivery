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
				DataBase.Comment -> {
					json.parseComment()
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
	some.updated = optInt("updated", 0)
	some.serialNum = optInt("serialNum", 0)
	some.name = optString("name", "")
	some.created = optInt("created", 0)
	some.loadPosition = optInt("loadPosition", 0)
	some.isExist = optBoolean("isExist", false)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.hashId = optString("hashId", "")
	optJSONObject("company")?.let { company ->
		some.company = company.parseUser()
	}
	some.isActive = optBoolean("isActive", false)
	return some
}

fun JSONObject.parseCartItem(): CartItem {
	val some = CartItem()
	some.productHashId = optString("productHashId", "")
	some.updated = optInt("updated", 0)
	some.count = optInt("count", 0)
	some.price = optInt("price", 0)
	some.created = optInt("created", 0)
	some.productName = optString("productName", "")
	some.isExist = optBoolean("isExist", false)
	some.hashId = optString("hashId", "")
	return some
}

fun JSONObject.parseCategory(): Category {
	val some = Category()
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	some.isExist = optBoolean("isExist", false)
	some.hashId = optString("hashId", "")
	some.serialNum = optInt("serialNum", 0)
	some.name = optString("name", "")
	return some
}

fun JSONObject.parseComment(): Comment {
	val some = Comment()
	some.companyHashId = optString("companyHashId", "")
	some.userHashId = optString("userHashId", "")
	some.isPublic = optBoolean("isPublic", false)
	some.updated = optInt("updated", 0)
	some.rate = optInt("rate", 0)
	some.created = optInt("created", 0)
	some.userName = optString("userName", "")
	some.isExist = optBoolean("isExist", false)
	some.text = optString("text", "")
	some.hashId = optString("hashId", "")
	return some
}

fun JSONObject.parseDocument(): Document {
	val some = Document()
	some.updated = optInt("updated", 0)
	some.name = optString("name", "")
	some.created = optInt("created", 0)
	some.isExist = optBoolean("isExist", false)
	some.txttext = optString("txttext", "")
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.__type = optString("__type", "")
	some.hashId = optString("hashId", "")
	return some
}

fun JSONObject.parseImage(): Image {
	val some = Image()
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	some.isExist = optBoolean("isExist", false)
	some.preview = optString("preview", "")
	some.hashId = optString("hashId", "")
	some.original = optString("original", "")
	return some
}

fun JSONObject.parseLocation(): Location {
	val some = Location()
	some.updated = optInt("updated", 0)
	some.latitude = optDouble("latitude", 0.0)
	some.longitude = optDouble("longitude", 0.0)
	some.address = optString("address", "")
	some.created = optInt("created", 0)
	some.geoHash = optString("geoHash", "")
	some.isExist = optBoolean("isExist", false)
	some.hashId = optString("hashId", "")
	return some
}

fun JSONObject.parseOrder(): Order {
	val some = Order()
	some.phone = optString("phone", "")
	some.orderNum = optString("orderNum", "")
	some.deliveryPrice = optDouble("deliveryPrice", 0.0)
	some.payMethod = optString("payMethod", "")
	some.intercomCode = optString("intercomCode", "")
	some.address = optString("address", "")
	some.porch = optString("porch", "")
	some.isExist = optBoolean("isExist", false)
	some.room = optString("room", "")
	some.updated = optInt("updated", 0)
	some.userHashId = optString("userHashId", "")
	some.total = optDouble("total", 0.0)
	some.floor = optString("floor", "")
	optJSONObject("items")?.let { itemss ->
 		for (i in 0 until itemss.length()) {
 			some.items.add(
				itemss.optJSONObject("$i")?.let { items ->
					items.parseCartItem()
				}
			)
		}
	}
	some.comment = optString("comment", "")
	some.__status = optString("__status", "")
	some.orderPrice = optDouble("orderPrice", 0.0)
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	some.companyHashId = optString("companyHashId", "")
	return some
}

fun JSONObject.parseProduct(): Product {
	val some = Product()
	some.ownerHashId = optString("ownerHashId", "")
	some.updated = optInt("updated", 0)
	optJSONObject("category")?.let { category ->
		some.category = category.parseCategory()
	}
	some.price = optInt("price", 0)
	some.about = optString("about", "")
	some.name = optString("name", "")
	some.created = optInt("created", 0)
	some.isExist = optBoolean("isExist", false)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.hashId = optString("hashId", "")
	return some
}

fun JSONObject.parseSale(): Sale {
	val some = Sale()
	some.updated = optInt("updated", 0)
	some.value = optInt("value", 0)
	optJSONObject("product")?.let { product ->
		some.product = product.parseProduct()
	}
	some.name = optString("name", "")
	some.created = optInt("created", 0)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.isExist = optBoolean("isExist", false)
	some.hashId = optString("hashId", "")
	optJSONObject("company")?.let { company ->
		some.company = company.parseUser()
	}
	some.txtInfo = optString("txtInfo", "")
	return some
}

fun JSONObject.parseShop(): Shop {
	val some = Shop()
	some.isExist = optBoolean("isExist", false)
	optJSONObject("location")?.let { location ->
		some.location = location.parseLocation()
	}
	some.created = optInt("created", 0)
	some.ownerHashId = optString("ownerHashId", "")
	some.name = optString("name", "")
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	some.address = optString("address", "")
	return some
}

fun JSONObject.parseUser(): User {
	val some = User()
	some.email = optString("email", "")
	some.averageСheck = optInt("averageСheck", 0)
	some.deviceId = optString("deviceId", "")
	some.commentsNum = optInt("commentsNum", 0)
	optJSONObject("categories")?.let { categoriess ->
 		for (i in 0 until categoriess.length()) {
 			some.categories.add(
				categoriess.optJSONObject("$i")?.let { categories ->
					categories.parseCategory()
				}
			)
		}
	}
	some.role = optString("role", "")
	some.updated = optInt("updated", 0)
	some.password = optString("password", "")
	some.hashId = optString("hashId", "")
	some.deliveryFrom = optInt("deliveryFrom", 0)
	optJSONObject("products")?.let { productss ->
 		for (i in 0 until productss.length()) {
 			some.products.add(
				productss.optJSONObject("$i")?.let { products ->
					products.parseProduct()
				}
			)
		}
	}
	some.txtAddressInfo = optString("txtAddressInfo", "")
	some.lastName = optString("lastName", "")
	some.rating = optDouble("rating", 0.0)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.workTime = optString("workTime", "")
	some.isAdmin = optBoolean("isAdmin", false)
	some.txtWorkTimeInfo = optString("txtWorkTimeInfo", "")
	some.name = optString("name", "")
	some.txtLegalInfo = optString("txtLegalInfo", "")
	some.phone = optString("phone", "")
	some.legalInfo = optString("legalInfo", "")
	some.firstName = optString("firstName", "")
	some.isExist = optBoolean("isExist", false)
	some.created = optInt("created", 0)
	some.fcmToken = optString("fcmToken", "")
	some.__type = optString("__type", "")
	return some
}