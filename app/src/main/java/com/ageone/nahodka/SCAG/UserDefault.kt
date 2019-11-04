package com.ageone.nahodka.SCAG
import io.realm.Realm
import net.alexandroid.shpref.ShPref


object UserData {

	var legalInfo: String
		get() = ShPref.getString("userDataLegalInfo", "")
		set(value) = ShPref.put("userDataLegalInfo", value)

	var categories: List<Category>
		get() {
			val hashes = ShPref.getListOfStrings("userDataCategories")//todo: delete )
			return if (hashes.isNullOrEmpty()) {
				emptyList()
			} else {
				val list = mutableListOf<Category>()
				hashes.forEach{ hash ->
					Realm.getDefaultInstance()
						.where(Category::class.java)
						.equalTo("primaryKey", hash)
						.equalTo("isExist", true)
						.findFirst()?.let { obj -> list.add(obj) }
				}
				list
			}
		}
		set(list) {
			if (list.isNullOrEmpty()) {
				ShPref.putList("userDataCategories", emptyList<String>())
			} else {
				val hashes = mutableListOf<String>()
				for (obj in list) {
					val hash = obj.hashId
					if (hash != "") {
						hashes.add(hash)
					}
				}
				ShPref.putList("userDataCategories", hashes)
			}
		}

	var workTime: String
		get() = ShPref.getString("userDataWorkTime", "")
		set(value) = ShPref.put("userDataWorkTime", value)

	var txtLegalInfo: String
		get() = ShPref.getString("userDataTxtLegalInfo", "")
		set(value) = ShPref.put("userDataTxtLegalInfo", value)

	var commentsNum: Int
		get() = ShPref.getInt("userDataCommentsNum", 0)
		set(value) = ShPref.put("userDataCommentsNum", value)

	var products: List<Product>
		get() {
			val hashes = ShPref.getListOfStrings("userDataProducts")
			return if (hashes.isNullOrEmpty()) {
				emptyList()
			} else {
				val list = mutableListOf<Product>()
				hashes.forEach{ hash ->
					Realm.getDefaultInstance()
						.where(Product::class.java)
						.equalTo("primaryKey", hash)
						.equalTo("isExist", true)
						.findFirst()?.let { obj -> list.add(obj) }
				}
				list
			}
		}
		set(list) {
			if (list.isNullOrEmpty()) {
				ShPref.putList("userDataProducts", emptyList<String>())
			} else {
				val hashes = mutableListOf<String>()
				for (obj in list) {
					val hash = obj.hashId
					if (hash != "") {
						hashes.add(hash)
					}
				}
				ShPref.putList("userDataProducts)", hashes)
			}
		}

	var phone: String
		get() = ShPref.getString("userDataPhone", "")
		set(value) = ShPref.put("userDataPhone", value)

	var email: String
		get() = ShPref.getString("userDataEmail", "")
		set(value) = ShPref.put("userDataEmail", value)

	var deliveryFrom: Int
		get() = ShPref.getInt("userDataDeliveryFrom", 0)
		set(value) = ShPref.put("userDataDeliveryFrom", value)

	var txtAddressInfo: String
		get() = ShPref.getString("userDataTxtAddressInfo", "")
		set(value) = ShPref.put("userDataTxtAddressInfo", value)

	var __type: String
		get() = ShPref.getString("userData__type", "")
		set(value) = ShPref.put("userData__type", value)

	var lastName: String
		get() = ShPref.getString("userDataLastName", "")
		set(value) = ShPref.put("userDataLastName", value)

	var firstName: String
		get() = ShPref.getString("userDataFirstName", "")
		set(value) = ShPref.put("userDataFirstName", value)

	var role: String
		get() = ShPref.getString("userDataRole", "")
		set(value) = ShPref.put("userDataRole", value)

	var rating: Double
		get() = ShPref.getDouble("userDataRating", 0.0)
		set(value) = ShPref.put("userDataRating", value)

	var name: String
		get() = ShPref.getString("userDataName", "")
		set(value) = ShPref.put("userDataName", value)

	var averageСheck: Int
		get() = ShPref.getInt("userDataAverageСheck", 0)
		set(value) = ShPref.put("userDataAverageСheck", value)

	var txtWorkTimeInfo: String
		get() = ShPref.getString("userDataTxtWorkTimeInfo", "")
		set(value) = ShPref.put("userDataTxtWorkTimeInfo", value)

	var image: Image?
		get() {
			val hash = ShPref.getString("userDataImage)", "")
			return if (hash == "")
				null
			else
				Realm.getDefaultInstance().where(Image::class.java).equalTo("primaryKey", hash).equalTo("isExist", true).findFirst()
		}
		set(value) {
			value?.let { obj ->
				val hash = obj.hashId
				if (hash != "")
					ShPref.put("userDataImage)", hash)
			}
		}

	var password: String
		get() = ShPref.getString("userDataPassword", "")
		set(value) = ShPref.put("userDataPassword", value)


}