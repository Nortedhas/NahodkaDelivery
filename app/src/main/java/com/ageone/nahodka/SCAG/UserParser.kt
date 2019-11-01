package com.ageone.nahodka.SCAG

import com.ageone.nahodka.Models.User.user
import org.json.JSONObject

fun Parser.userData(json: JSONObject) {
	json.optJSONObject("User")?.let { userJson ->
		user.hashId = userJson.optString("hashId", "")
		user.data.timeFrom = userJson.optInt("timeFrom", 0)
		user.data.__type = userJson.optString("__type", "")
		user.data.averageСheck = userJson.optInt("averageСheck", 0)
		user.data.email = userJson.optString("email", "")
		user.data.firstName = userJson.optString("firstName", "")
		user.data.timeTo = userJson.optInt("timeTo", 0)
		user.data.legalInfo = userJson.optString("legalInfo", "")
		user.data.phone = userJson.optString("phone", "")
		user.data.txtAddressInfo = userJson.optString("txtAddressInfo", "")
		user.data.lastName = userJson.optString("lastName", "")
		user.data.password = userJson.optString("password", "")
		user.data.commentsNum = userJson.optInt("commentsNum", 0)
		user.data.txtWorkTimeInfo = userJson.optString("txtWorkTimeInfo", "")
		user.data.deliveryFrom = userJson.optInt("deliveryFrom", 0)
		user.data.name = userJson.optString("name", "")
		user.data.txtLegalInfo = userJson.optString("txtLegalInfo", "")
		user.data.role = userJson.optString("role", "")
		user.data.rating = userJson.optDouble("rating", 0.0)
	}
}