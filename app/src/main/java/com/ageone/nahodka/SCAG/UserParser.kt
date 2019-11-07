package com.ageone.nahodka.SCAG

import com.ageone.nahodka.Models.User.user
import org.json.JSONObject

fun Parser.userData(json: JSONObject) {
	json.optJSONObject("User")?.let { userJson ->
		user.hashId = userJson.optString("hashId", "")
		user.data.__type = userJson.optString("__type", "")
		user.data.email = userJson.optString("email", "")
		user.data.phone = userJson.optString("phone", "")
		user.data.name = userJson.optString("name", "")
		user.data.deliveryFrom = userJson.optInt("deliveryFrom", 0)
		user.data.rating = userJson.optDouble("rating", 0.0)
		user.data.txtWorkTimeInfo = userJson.optString("txtWorkTimeInfo", "")
		user.data.deliveryPrice = userJson.optInt("deliveryPrice", 0)
		user.data.role = userJson.optString("role", "")
		user.data.commentsNum = userJson.optInt("commentsNum", 0)
		user.data.txtLegalInfo = userJson.optString("txtLegalInfo", "")
		user.data.txtAddressInfo = userJson.optString("txtAddressInfo", "")
		user.data.password = userJson.optString("password", "")
		user.data.averageСheck = userJson.optInt("averageСheck", 0)
	}
}