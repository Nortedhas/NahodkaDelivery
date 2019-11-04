// MARK: DataBase

package com.ageone.nahodka.SCAG

import com.ageone.nahodka.Application.utils

enum class DataBase {

	Banner, CartItem, Category, Comment, Document, Image, Location, Order, Product, Sale, Shop, User;

	companion object DataObjects {
		var url: String = "http://195.133.48.131"
		val headers
			get() = mutableMapOf("x-access-token" to utils.variable.token)
	}
}