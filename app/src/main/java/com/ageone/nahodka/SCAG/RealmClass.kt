// MARK: Realm Class

package com.ageone.nahodka.SCAG

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Banner (
	open var name: String = "",
	open var created: Int = 0,
	open var image: Image? = null,
	open var isExist: Boolean = false,
	open var company: User? = null,
	open var serialNum: Int = 0,
	open var updated: Int = 0,
	open var loadPosition: Int = 0,
	open var isActive: Boolean = false,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class CartItem (
	open var created: Int = 0,
	open var isExist: Boolean = false,
	open var productHashId: String = "",
	open var productName: String = "",
	open var updated: Int = 0,
	open var price: Int = 0,
	open var count: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Category (
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var name: String = "",
	open var created: Int = 0,
	open var serialNum: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Comment (
	open var rate: Int = 0,
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var created: Int = 0,
	open var userHashId: String = "",
	open var companyHashId: String = "",
	open var userName: String = "",
	open var isPublic: Boolean = false,
	open var text: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Document (
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var created: Int = 0,
	open var image: Image? = null,
	open var __type: String = "",
	open var txttext: String = "",
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Image (
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var original: String = "",
	open var preview: String = "",
	open var created: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Location (
	open var latitude: Double = 0.0,
	open var geoHash: String = "",
	open var address: String = "",
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var created: Int = 0,
	open var longitude: Double = 0.0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Order (
	open var room: String = "",
	open var isExist: Boolean = false,
	open var deliveryPrice: Int = 0,
	open var floor: String = "",
	open var address: String = "",
	open var orderNum: String = "",
	open var updated: Int = 0,
	open var total: Int = 0,
	open var created: Int = 0,
	open var payMethod: String = "",
	open var cutleryNum: Int = 0,
	open var orderPrice: Int = 0,
	open var items: RealmList<CartItem> = RealmList(),
	open var companyHashId: String = "",
	open var __status: String = "",
	open var comment: String = "",
	open var porch: String = "",
	open var intercomCode: String = "",
	open var userHashId: String = "",
	open var phone: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Product (
	open var updated: Int = 0,
	open var about: String = "",
	open var created: Int = 0,
	open var image: Image? = null,
	open var isExist: Boolean = false,
	open var ownerHashId: String = "",
	open var price: Int = 0,
	open var category: Category? = null,
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Sale (
	open var discount: Int = 0,
	open var updated: Int = 0,
	open var isExist: Boolean = false,
	open var created: Int = 0,
	open var image: Image? = null,
	open var product: Product? = null,
	open var companyHashId: String = "",
	open var txtInfo: String = "",
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Shop (
	open var updated: Int = 0,
	open var location: Location? = null,
	open var isExist: Boolean = false,
	open var created: Int = 0,
	open var ownerHashId: String = "",
	open var name: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class User (
	open var deviceId: String = "",
	open var isExist: Boolean = false,
	open var rating: Double = 0.0,
	open var txtAddressInfo: String = "",
	open var tags: RealmList<String> = RealmList(),
	open var __type: String = "",
	open var products: RealmList<Product> = RealmList(),
	open var email: String = "",
	open var name: String = "",
	open var updated: Int = 0,
	open var deliveryPrice: Int = 0,
	open var created: Int = 0,
	open var password: String = "",
	open var isAdmin: Boolean = false,
	open var commentsNum: Int = 0,
	open var fcmToken: String = "",
	open var averageСheck: Int = 0,
	open var txtLegalInfo: String = "",
	open var deliveryFrom: Int = 0,
	open var txtWorkTimeInfo: String = "",
	open var phone: String = "",
	open var role: String = "",
	open var image: Image? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()