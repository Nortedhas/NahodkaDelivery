package com.ageone.nahodka.SCAG
import io.realm.Realm
import net.alexandroid.shpref.ShPref


object UserData {

	var tags: ArrayList<String>
		get() = ShPref.getListOfStrings("userDataTags)")
		set(value) = ShPref.putList("userDataTags)", value)

	var __type: String
		get() = ShPref.getString("userData__type", "")
		set(value) = ShPref.put("userData__type", value)

	var txtWorkTimeInfo: String
		get() = ShPref.getString("userDataTxtWorkTimeInfo", "")
		set(value) = ShPref.put("userDataTxtWorkTimeInfo", value)

	var categories: List<Category>
		get() {
			val hashes = ShPref.getListOfStrings("userDataCategories)")
			return if (hashes.isNullOrEmpty()) {
				emptyList()
			} else {
				val list = mutableListOf<Category>()//todo: change
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
				ShPref.putList("userDataCategories)", emptyList<String>())
			} else {
				val hashes = mutableListOf<String>()
				for (obj in list) {
					val hash = obj.hashId
					if (hash != "") {
						hashes.add(hash)
					}
				}
				ShPref.putList("userDataCategories)", hashes)
			}
		}

	var averageСheck: Int
		get() = ShPref.getInt("userDataAverageСheck", 0)
		set(value) = ShPref.put("userDataAverageСheck", value)

	var commentsNum: Int
		get() = ShPref.getInt("userDataCommentsNum", 0)
		set(value) = ShPref.put("userDataCommentsNum", value)

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

	var phone: String
		get() = ShPref.getString("userDataPhone", "")
		set(value) = ShPref.put("userDataPhone", value)

	var firstName: String
		get() = ShPref.getString("userDataFirstName", "")
		set(value) = ShPref.put("userDataFirstName", value)

	var password: String
		get() = ShPref.getString("userDataPassword", "")
		set(value) = ShPref.put("userDataPassword", value)

	var email: String
		get() = ShPref.getString("userDataEmail", "")
		set(value) = ShPref.put("userDataEmail", value)

	var rating: Double
		get() = ShPref.getDouble("userDataRating", 0.0)
		set(value) = ShPref.put("userDataRating", value)

	var legalInfo: String
		get() = ShPref.getString("userDataLegalInfo", "")
		set(value) = ShPref.put("userDataLegalInfo", value)

	var role: String
		get() = ShPref.getString("userDataRole", "")
		set(value) = ShPref.put("userDataRole", value)

	var name: String
		get() = ShPref.getString("userDataName", "")
		set(value) = ShPref.put("userDataName", value)

	var timeFrom: Int
		get() = ShPref.getInt("userDataTimeFrom", 0)
		set(value) = ShPref.put("userDataTimeFrom", value)

	var txtAddressInfo: String
		get() = ShPref.getString("userDataTxtAddressInfo", "")
		set(value) = ShPref.put("userDataTxtAddressInfo", value)

	var timeTo: Int
		get() = ShPref.getInt("userDataTimeTo", 0)
		set(value) = ShPref.put("userDataTimeTo", value)

	var txtLegalInfo: String
		get() = ShPref.getString("userDataTxtLegalInfo", "")
		set(value) = ShPref.put("userDataTxtLegalInfo", value)

	var deliveryFrom: Int
		get() = ShPref.getInt("userDataDeliveryFrom", 0)
		set(value) = ShPref.put("userDataDeliveryFrom", value)

	var productLists: List<ProductList>
		get() {
			val hashes = ShPref.getListOfStrings("userDataProductLists)")
			return if (hashes.isNullOrEmpty()) {
				emptyList()
			} else {
				val list = mutableListOf<ProductList>()
				hashes.forEach{ hash ->
					Realm.getDefaultInstance()
						.where(ProductList::class.java)
						.equalTo("primaryKey", hash)
						.equalTo("isExist", true)
						.findFirst()?.let { obj -> list.add(obj) }
				}
				list
			}
		}
		set(list) {
			if (list.isNullOrEmpty()) {
				ShPref.putList("userDataProductLists)", emptyList<String>())
			} else {
				val hashes = mutableListOf<String>()
				for (obj in list) {
					val hash = obj.hashId
					if (hash != "") {
						hashes.add(hash)
					}
				}
				ShPref.putList("userDataProductLists)", hashes)
			}
		}

	var lastName: String
		get() = ShPref.getString("userDataLastName", "")
		set(value) = ShPref.put("userDataLastName", value)


}