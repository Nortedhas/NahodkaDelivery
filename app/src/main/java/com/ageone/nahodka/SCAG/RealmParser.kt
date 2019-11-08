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
	some.loadPosition = optInt("loadPosition", 0)
	some.isActive = optBoolean("isActive", false)
	some.created = optInt("created", 0)
	optJSONObject("company")?.let { company ->
		some.company = company.parseUser()
	}
	some.serialNum = optInt("serialNum", 0)
	some.name = optString("name", "")
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	some.isExist = optBoolean("isExist", false)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	return some
}

fun JSONObject.parseCartItem(): CartItem {
	val some = CartItem()
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	some.price = optInt("price", 0)
	some.updated = optInt("updated", 0)
	some.isExist = optBoolean("isExist", false)
	some.productHashId = optString("productHashId", "")
	some.productName = optString("productName", "")
	some.count = optInt("count", 0)
	return some
}

fun JSONObject.parseCategory(): Category {
	val some = Category()
	some.isExist = optBoolean("isExist", false)
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	some.serialNum = optInt("serialNum", 0)
	some.name = optString("name", "")
	some.updated = optInt("updated", 0)
	return some
}

fun JSONObject.parseComment(): Comment {
	val some = Comment()
	some.isPublic = optBoolean("isPublic", false)
	some.hashId = optString("hashId", "")
	some.rate = optInt("rate", 0)
	some.companyHashId = optString("companyHashId", "")
	some.created = optInt("created", 0)
	some.text = optString("text", "")
	some.updated = optInt("updated", 0)
	some.isExist = optBoolean("isExist", false)
	some.userHashId = optString("userHashId", "")
	some.userName = optString("userName", "")
	return some
}

fun JSONObject.parseDocument(): Document {
	val some = Document()
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	some.name = optString("name", "")
	some.updated = optInt("updated", 0)
	some.__type = optString("__type", "")
	some.isExist = optBoolean("isExist", false)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.txttext = optString("txttext", "")
	return some
}

fun JSONObject.parseImage(): Image {
	val some = Image()
	some.isExist = optBoolean("isExist", false)
	some.preview = optString("preview", "")
	some.hashId = optString("hashId", "")
	some.original = optString("original", "")
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	return some
}

fun JSONObject.parseLocation(): Location {
	val some = Location()
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	some.longitude = optDouble("longitude", 0.0)
	some.updated = optInt("updated", 0)
	some.latitude = optDouble("latitude", 0.0)
	some.isExist = optBoolean("isExist", false)
	some.address = optString("address", "")
	some.geoHash = optString("geoHash", "")
	return some
}

fun JSONObject.parseOrder(): Order {
	val some = Order()
	some.isExist = optBoolean("isExist", false)
	some.address = optString("address", "")
	some.phone = optString("phone", "")
	some.comment = optString("comment", "")
	some.payMethod = optString("payMethod", "")
	some.companyHashId = optString("companyHashId", "")
	some.__status = optString("__status", "")
	some.orderNum = optString("orderNum", "")
	some.porch = optString("porch", "")
	some.hashId = optString("hashId", "")
	some.userHashId = optString("userHashId", "")
	some.created = optInt("created", 0)
	some.orderPrice = optInt("orderPrice", 0)
	some.room = optString("room", "")
	optJSONObject("items")?.let { itemss ->
 		for (i in 0 until itemss.length()) {
 			some.items.add(
				itemss.optJSONObject("$i")?.let { items ->
					items.parseCartItem()
				}
			)
		}
	}
	some.total = optInt("cutleryNum", 0)
	some.total = optInt("total", 0)
	some.intercomCode = optString("intercomCode", "")
	some.updated = optInt("updated", 0)
	some.deliveryPrice = optInt("deliveryPrice", 0)
	some.floor = optString("floor", "")

	return some
}

fun JSONObject.parseProduct(): Product {
	val some = Product()
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	some.price = optInt("price", 0)
	optJSONObject("category")?.let { category ->
		some.category = category.parseCategory()
	}
	some.name = optString("name", "")
	some.updated = optInt("updated", 0)
	some.about = optString("about", "")
	some.isExist = optBoolean("isExist", false)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.ownerHashId = optString("ownerHashId", "")
	return some
}

fun JSONObject.parseSale(): Sale {
	val some = Sale()
	some.txtInfo = optString("txtInfo", "")
	some.discount = optInt("discount", 0)
	some.companyHashId = optString("companyHashId", "")
	some.created = optInt("created", 0)
	some.name = optString("name", "")
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	some.isExist = optBoolean("isExist", false)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	optJSONObject("product")?.let { product ->
		some.product = product.parseProduct()
	}
	return some
}

fun JSONObject.parseShop(): Shop {
	val some = Shop()
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	some.name = optString("name", "")
	some.updated = optInt("updated", 0)
	some.isExist = optBoolean("isExist", false)
	some.ownerHashId = optString("ownerHashId", "")
	optJSONObject("location")?.let { location ->
		some.location = location.parseLocation()
	}
	return some
}

fun JSONObject.parseUser(): User {
	val some = User()
	some.deviceId = optString("deviceId", "")
	some.isExist = optBoolean("isExist", false)
	some.role = optString("role", "")
	some.phone = optString("phone", "")
	some.__type = optString("__type", "")
	some.fcmToken = optString("fcmToken", "")
	some.txtAddressInfo = optString("txtAddressInfo", "")
	some.email = optString("email", "")
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.password = optString("password", "")
	some.commentsNum = optInt("commentsNum", 0)
	optJSONObject("tags")?.let { arrayString ->
		for (i in 0 until arrayString.length()) {
			some.tags.add(
				arrayString.optString("$i", "")
			)
		}
	}
	some.hashId = optString("hashId", "")
	some.averageСheck = optInt("averageСheck", 0)
	some.isAdmin = optBoolean("isAdmin", false)
	some.name = optString("name", "")
	some.created = optInt("created", 0)
	some.txtLegalInfo = optString("txtLegalInfo", "")
	some.txtWorkTimeInfo = optString("txtWorkTimeInfo", "")
	some.deliveryFrom = optInt("deliveryFrom", 0)
	some.updated = optInt("updated", 0)
	some.rating = optDouble("rating", 0.0)
	some.deliveryPrice = optInt("deliveryPrice", 0)
	optJSONObject("products")?.let { productss ->
 		for (i in 0 until productss.length()) {
 			some.products.add(
				productss.optJSONObject("$i")?.let { products ->
					products.parseProduct()
				}
			)
		}
	}
	return some
}