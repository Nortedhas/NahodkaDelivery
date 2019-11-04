package com.ageone.nahodka.SCAG

class Enums {

	// MARK: Enum PaymentType

	enum class PaymentType {
		cardtocourier, applepay, cash, card
	}

	// MARK: Enum OrderType

	enum class OrderType {
		delivered, aborted, cooked, created, paid, cooking, accepted
	}

	// MARK: Enum ShopType

	enum class ShopType {
		food, flowers
	}

	// MARK: Enum ProductType

	enum class ProductType {
		food, flowers
	}

	// MARK: Enum CategoryType

	enum class CategoryType {
		flowers, food
	}

	// MARK: Enum DocumentType

	enum class DocumentType {
		regular, faq
	}

	// MARK: Enum UserType

	enum class UserType {
		food, flowers
	}

}