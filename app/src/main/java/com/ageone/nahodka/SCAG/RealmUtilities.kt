// MARK: Realm Utiles

package com.ageone.nahodka.SCAG

import io.realm.Realm
import io.realm.RealmResults
import timber.log.Timber


object RealmUtilities {
	val banner = BannerUtiles()
	val cartitem = CartItemUtiles()
	val category = CategoryUtiles()
	val comment = CommentUtiles()
	val document = DocumentUtiles()
	val image = ImageUtiles()
	val location = LocationUtiles()
	val order = OrderUtiles()
	val product = ProductUtiles()
	val sale = SaleUtiles()
	val shop = ShopUtiles()
	val user = UserUtiles()
}

class BannerUtiles {

	fun getObjectById(id: String): Banner? =
	try {
		Realm.getDefaultInstance()
			.where(Banner::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Banner> =
	try {
		Realm.getDefaultInstance()
			.where(Banner::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Banner::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class CartItemUtiles {

	fun getObjectById(id: String): CartItem? =
	try {
		Realm.getDefaultInstance()
			.where(CartItem::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<CartItem> =
	try {
		Realm.getDefaultInstance()
			.where(CartItem::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(CartItem::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class CategoryUtiles {

	fun getObjectById(id: String): Category? =
	try {
		Realm.getDefaultInstance()
			.where(Category::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Category> =
	try {
		Realm.getDefaultInstance()
			.where(Category::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Category::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class CommentUtiles {

	fun getObjectById(id: String): Comment? =
	try {
		Realm.getDefaultInstance()
			.where(Comment::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Comment> =
	try {
		Realm.getDefaultInstance()
			.where(Comment::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Comment::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class DocumentUtiles {

	fun getObjectById(id: String): Document? =
	try {
		Realm.getDefaultInstance()
			.where(Document::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Document> =
	try {
		Realm.getDefaultInstance()
			.where(Document::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Document::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class ImageUtiles {

	fun getObjectById(id: String): Image? =
	try {
		Realm.getDefaultInstance()
			.where(Image::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Image> =
	try {
		Realm.getDefaultInstance()
			.where(Image::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Image::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class LocationUtiles {

	fun getObjectById(id: String): Location? =
	try {
		Realm.getDefaultInstance()
			.where(Location::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Location> =
	try {
		Realm.getDefaultInstance()
			.where(Location::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Location::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class OrderUtiles {

	fun getObjectById(id: String): Order? =
	try {
		Realm.getDefaultInstance()
			.where(Order::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Order> =
	try {
		Realm.getDefaultInstance()
			.where(Order::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Order::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class ProductUtiles {

	fun getObjectById(id: String): Product? =
	try {
		Realm.getDefaultInstance()
			.where(Product::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Product> =
	try {
		Realm.getDefaultInstance()
			.where(Product::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Product::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class SaleUtiles {

	fun getObjectById(id: String): Sale? =
	try {
		Realm.getDefaultInstance()
			.where(Sale::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Sale> =
	try {
		Realm.getDefaultInstance()
			.where(Sale::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Sale::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class ShopUtiles {

	fun getObjectById(id: String): Shop? =
	try {
		Realm.getDefaultInstance()
			.where(Shop::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Shop> =
	try {
		Realm.getDefaultInstance()
			.where(Shop::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Shop::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class UserUtiles {

	fun getObjectById(id: String): User? =
	try {
		Realm.getDefaultInstance()
			.where(User::class.java)
		.equalTo("hashId", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<User> =
	try {
		Realm.getDefaultInstance()
			.where(User::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(User::class.java)
		.alwaysFalse()
		.findAll()
	}

}

