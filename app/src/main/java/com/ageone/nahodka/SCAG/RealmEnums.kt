package com.ageone.nahodka.SCAG

class Enums {

	// MARK: Enum CategoryType

	enum class CategoryType {
		flowers, food
	}

	// MARK: Enum PaymentType

	enum class PaymentType(val value: String) {
		card("По карте"), cash("Наличные"), cardToCourier("Картой курьеру")
	}

	// MARK: Enum OrderType

	enum class OrderType {
		aborted, cooking, delivered, paid, accepted, cooked, created
	}

	// MARK: Enum UserType

	enum class UserType {
		food, flowers
	}

	// MARK: Enum DocumentType

	enum class DocumentType {
		faq, regular
	}

	// MARK: Enum ProductType

	enum class ProductType {
		food, flowers
	}

	// MARK: Enum ShopType

	enum class ShopType {
		food, flowers
	}

}