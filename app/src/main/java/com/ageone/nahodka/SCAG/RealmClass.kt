// MARK: Realm Class

package com.ageone.nahodka.SCAG

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Banner (
	open var loadPosition: Int = 0,
	open var created: Int = 0,
	open var isExist: Boolean = false,
	open var company: User? = null,
	open var isActive: Boolean = false,
	open var updated: Int = 0,
	open var serialNum: Int = 0,
	open var name: String = "",
	open var image: Image? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class CartItem (
	open var created: Int = 0,
	open var price: Int = 0,
	open var productHashId: String = "",
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var count: Int = 0,
	open var productName: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Category (
	open var created: Int = 0,
	open var isExist: Boolean = false,
	open var serialNum: Int = 0,
	open var updated: Int = 0,
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Comment (
	open var created: Int = 0,
	open var companyHashId: String = "",
	open var isPublic: Boolean = false,
	open var updated: Int = 0,
	open var userName: String = "",
	open var rate: Int = 0,
	open var isExist: Boolean = false,
	open var text: String = "",
	open var userHashId: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Document (
	open var created: Int = 0,
	open var __type: String = "",
	open var image: Image? = null,
	open var name: String = "",
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var txttext: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Image (
	open var preview: String = "",
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var original: String = "",
	open var created: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Location (
	open var latitude: Double = 0.0,
	open var created: Int = 0,
	open var address: String = "",
	open var geoHash: String = "",
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var longitude: Double = 0.0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Order (
	open var deliveryPrice: Double = 0.0,
	open var porch: String = "",
	open var orderNum: String = "",
	open var isExist: Boolean = false,
	open var items: RealmList<CartItem> = RealmList(),
	open var room: String = "",
	open var total: Double = 0.0,
	open var updated: Int = 0,
	open var comment: String = "",
	open var phone: String = "",
	open var companyHashId: String = "",
	open var orderPrice: Double = 0.0,
	open var userHashId: String = "",
	open var floor: String = "",
	open var address: String = "",
	open var payMethod: String = "",
	open var __status: String = "",
	open var created: Int = 0,
	open var intercomCode: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Product (
	open var ownerHashId: String = "",
	open var created: Int = 0,
	open var image: Image? = null,
	open var name: String = "",
	open var category: Category? = null,
	open var updated: Int = 0,
	open var about: String = "",
	open var isExist: Boolean = false,
	open var price: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Sale (
	open var created: Int = 0,
	open var image: Image? = null,
	open var company: User? = null,
	open var product: Product? = null,
	open var value: Int = 0,
	open var name: String = "",
	open var txtInfo: String = "",
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Shop (
	open var ownerHashId: String = "",
	open var created: Int = 0,
	open var address: String = "",
	open var name: String = "",
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var location: Location? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class User (
	open var lastName: String = "",
	open var name: String = "",
	open var txtWorkTimeInfo: String = "",
	open var txtLegalInfo: String = "",
	open var workTime: String = "",
	open var role: String = "",
	open var legalInfo: String = "",
	open var password: String = "",
	open var firstName: String = "",
	open var phone: String = "",
	open var txtAddressInfo: String = "",
	open var categories: RealmList<Category> = RealmList(),
	open var deviceId: String = "",
	open var rating: Double = 0.0,
	open var created: Int = 0,
	open var fcmToken: String = "",
	open var __type: String = "",
	open var updated: Int = 0,
	open var averageСheck: Int = 0,
	open var products: RealmList<Product> = RealmList(),
	open var deliveryFrom: Int = 0,
	open var isExist: Boolean = false,
	open var image: Image? = null,
	open var email: String = "",
	open var isAdmin: Boolean = false,
	open var commentsNum: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()