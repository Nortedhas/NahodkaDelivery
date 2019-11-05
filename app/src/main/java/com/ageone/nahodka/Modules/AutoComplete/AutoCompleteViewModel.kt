package com.ageone.nahodka.Modules.AutoComplete

import android.util.Log
import com.ageone.nahodka.Application.placesClient
import com.ageone.nahodka.External.Extensions.Activity.startLocation
import com.ageone.nahodka.External.Interfaces.InterfaceModel
import com.ageone.nahodka.External.Interfaces.InterfaceViewModel
import com.ageone.nahodka.Models.User.user
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import timber.log.Timber


class AutoCompleteViewModel : InterfaceViewModel {
    var model = AutoCompleteModel()

    enum class EventType {

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is AutoCompleteModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun getComplete(query: String) {

        val token = AutocompleteSessionToken.newInstance()

        // Create a RectangularBounds object.
        val bounds = RectangularBounds.newInstance(
            LatLng(
                startLocation.latitude - 0.5,
                startLocation.longitude - 0.5
            ),
            LatLng(
                startLocation.latitude + 0.5,
                startLocation.longitude + 0.5
            )
        )

        val request = FindAutocompletePredictionsRequest.builder()
            .setLocationBias(bounds)
            .setCountry("ru")
            .setTypeFilter(TypeFilter.ADDRESS)
            .setSessionToken(token)
            .setQuery(query)
            .build()

        placesClient?.let { placesClient ->
            placesClient.findAutocompletePredictions(request)
                .addOnSuccessListener { response ->
                for (prediction in response.autocompletePredictions) {
                    Timber.i("Vars: ${prediction.getPrimaryText(null)} - ${prediction.getSecondaryText(null)}")
                }
            }.addOnFailureListener { exception ->

            }
        }
    }
}

class AutoCompleteModel : InterfaceModel {

}
