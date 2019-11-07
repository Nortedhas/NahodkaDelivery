package com.ageone.nahodka.Modules.Restaurant

import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.SCAG.Category
import com.ageone.nahodka.SCAG.Product
import com.ageone.nahodka.SCAG.User

class RestaurantViewModel : InterfaceViewModel {
    var model = RestaurantModel()

    enum class EventType {
        OnInfoPressed,
        OnReviewPressed
    }

    var realmData = listOf<Product>()
    fun loadRealmData() {
        realmData = rxData.currentCompany?.products ?: emptyList()
    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RestaurantModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class RestaurantModel : InterfaceModel {

}

fun User.createCategoriesFromCompany(): List<Category> {
    val set = mutableSetOf<Category>()

    products.forEach { product ->
        product.category?.let { category ->
            set.add(category)
        }
    }

    return set.toList()
}
