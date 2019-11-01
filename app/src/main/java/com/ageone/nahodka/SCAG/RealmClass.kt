// MARK: Realm Class

package com.ageone.nahodka.SCAG

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Banner (
	open var isExist: Boolean = false,
	open var image: RealmList<Image> = RealmList(),
	open var updated: Int = 0,
	open var name: String = "",
	open var companyHashId: String = "",
	open var created: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class CartItem (
	open var productName: String = "",
	open var isExist: Boolean = false,
	open var updated: Int = 0,
	open var productHashId: String = "",
	open var count: Int = 0,
	open var price: Int = 0,
	open var created: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Category (
	open var isExist: Boolean = false,
	open var serialNum: Int = 0,
	open var unselectedImage: Image? = null,
	open var updated: Int = 0,
	open var name: String = "",
	open var __type: String = "",
	open var created: Int = 0,
	open var selectedImage: Image? = null,
	open var whiteImage: Image? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class City (
	open var name: String = "",
	open var isExist: Boolean = false,
	open var created: Int = 0,
	open var updated: Int = 0,
	open var location: Location? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Comment (
	open var userHashId: String = "",
	open var isExist: Boolean = false,
	open var rate: Int = 0,
	open var updated: Int = 0,
	open var text: String = "",
	open var companyHashId: String = "",
	open var created: Int = 0,
	open var userName: String = "",
	open var isPublic: Boolean = false,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class DistrictPoint (
	open var isExist: Boolean = false,
	open var updated: Int = 0,
	open var name: String = "",
	open var city: City? = null,
	open var created: Int = 0,
	open var location: Location? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Document (
	open var isExist: Boolean = false,
	open var image: Image? = null,
	open var updated: Int = 0,
	open var name: String = "",
	open var __type: String = "",
	open var txttext: String = "",
	open var created: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Image (
	open var isExist: Boolean = false,
	open var preview: String = "",
	open var created: Int = 0,
	open var updated: Int = 0,
	open var original: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Location (
	open var latitude: Double = 0.0,
	open var isExist: Boolean = false,
	open var geoHash: String = "",
	open var address: String = "",
	open var updated: Int = 0,
	open var created: Int = 0,
	open var longitude: Double = 0.0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Order (
	open var isExist: Boolean = false,
	open var floor: String = "",
	open var updated: Int = 0,
	open var orderNum: String = "",
	open var address: String = "",
	open var phone: String = "",
	open var companyHashId: String = "",
	open var items: RealmList<CartItem> = RealmList(),
	open var payMethod: String = "",
	open var cutleryNum: Int = 0,
	open var deliveryPrice: Double = 0.0,
	open var orderPrice: Double = 0.0,
	open var userHashId: String = "",
	open var porch: String = "",
	open var intercomCode: String = "",
	open var __status: String = "",
	open var room: String = "",
	open var created: Int = 0,
	open var total: Double = 0.0,
	open var comment: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Product (
	open var isExist: Boolean = false,
	open var image: Image? = null,
	open var price: Int = 0,
	open var about: String = "",
	open var updated: Int = 0,
	open var name: String = "",
	open var created: Int = 0,
	open var tag: String = "",
	open var ownerHashId: String = "",
	open var category: Category? = null,
	open var shopName: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class ProductList (
	open var isExist: Boolean = false,
	open var updated: Int = 0,
	open var products: RealmList<Product> = RealmList(),
	open var name: String = "",
	open var created: Int = 0,
	open var ownerHashId: String = "",
	open var category: Category? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Sale (
	open var value: Int = 0,
	open var isExist: Boolean = false,
	open var image: Image? = null,
	open var updated: Int = 0,
	open var companyHashId: String = "",
	open var created: Int = 0,
	open var txtAbout: String = "",
	open var product: Product? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Shop (
	open var isExist: Boolean = false,
	open var address: String = "",
	open var updated: Int = 0,
	open var name: String = "",
	open var city: City? = null,
	open var created: Int = 0,
	open var __type: String = "",
	open var ownerHashId: String = "",
	open var location: Location? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class User (
	open var __type: String = "",
	open var created: Int = 0,
	open var name: String = "",
	open var rating: Double = 0.0,
	open var image: Image? = null,
	open var timeTo: Int = 0,
	open var txtLegalInfo: String = "",
	open var fcmToken: String = "",
	open var isAdmin: Boolean = false,
	open var timeFrom: Int = 0,
	open var legalInfo: String = "",
	open var categories: RealmList<Category> = RealmList(),
	open var password: String = "",
	open var productLists: RealmList<ProductList> = RealmList(),
	open var firstName: String = "",
	open var email: String = "",
	open var updated: Int = 0,
	open var txtWorkTimeInfo: String = "",
	open var commentsNum: Int = 0,
	open var txtAddressInfo: String = "",
	open var deviceId: String = "",
	open var averageСheck: Int = 0,
	open var lastName: String = "",
	open var role: String = "",
	open var isExist: Boolean = false,
	open var phone: String = "",
	open var deliveryFrom: Int = 0,
	open var tags: RealmList<String> = RealmList(),
	@PrimaryKey open var hashId: String = ""
): RealmObject()