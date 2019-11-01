package com.ageone.nahodka.SCAG

class Enums {

	// MARK: Enum ShopType

	enum class ShopType {
		food, flowers
	}

	// MARK: Enum CategoryType

	enum class CategoryType {
		food, flowers
	}

	// MARK: Enum OrderType

	enum class OrderType {
		cooked, cooking, delivered, accepted, aborted, created, paid
	}

	// MARK: Enum ProductType

	enum class ProductType {
		food, flowers
	}

	// MARK: Enum PaymentType

	enum class PaymentType {
		applepay, cardtocourier, card, cash
	}

	// MARK: Enum DocumentType

	enum class DocumentType {
		regular, faq
	}

	// MARK: Enum UserType

	enum class UserType {
		flowers, food
	}

}