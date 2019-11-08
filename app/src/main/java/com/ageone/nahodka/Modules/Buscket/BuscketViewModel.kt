package com.ageone.nahodka.Modules.Buscket

import com.ageone.nahodka.Application.rxData
import com.ageone.nahodka.Application.utils
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.Network.HTTP.ItemForOrder
import com.ageone.nahodka.SCAG.Product
import timber.log.Timber

class BuscketViewModel : InterfaceViewModel {
    var model = BuscketModel()

    enum class EventType {

        OnCheckPressed
    }

    var realmData = mutableListOf<ProductForBucket>()
    fun loadRealmData() {


        Timber.i("Prodict item : ${rxData.selectedItems}")
        val setProducts = rxData.selectedItems.toSet()

        setProducts.forEach { product ->
            var priceWithSale = product.price

            utils.realm.sale.getAllObjects().forEach { sale ->
                if (sale.product?.hashId == product.hashId) {
                    priceWithSale = product.price * (100 - sale.discount) / 100
                }
            }

            realmData.add(
                ProductForBucket(
                    product,
                   // count,
                    rxData.selectedItems.count{ currentProduct ->
                        currentProduct.hashId == product.hashId
                    },
                    priceWithSale
                )
            )
        }
    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is BuscketModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun getOrderPrice(): Int {
        model.orderPrice = 0
        realmData.forEach { item ->
            model.orderPrice += (item.count * item.priceWithSale)
        }
        return model.orderPrice
    }

    fun setItemList() {
        model.itemList.clear()
        realmData.forEach { product ->
            model.itemList.add(
                ItemForOrder(
                    product.count,
                    product.product.hashId
                )
            )
        }
    }
}

class BuscketModel : InterfaceModel {
    var appliancesCount = 1
    var itemList = mutableListOf<ItemForOrder>()
    var orderPrice: Int = 0
}

data class ProductForBucket (
    var product: Product,
    var count: Int,
    var priceWithSale: Int
)
