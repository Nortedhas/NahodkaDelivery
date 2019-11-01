package com.ageone.nahodka.SCAG

import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.SCAG.Parser
import org.json.JSONObject

fun Parser.config(json: JSONObject) {
	json.optJSONArray("Config")?.optJSONObject(0)?.let { userJson ->
		utils.config.name = userJson.optString("name", "")
	}
}