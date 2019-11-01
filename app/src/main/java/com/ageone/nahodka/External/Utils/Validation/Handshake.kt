package com.ageone.nahodka.External.Utils.Validation

import com.ageone.nahodka.Models.User.user

fun isValidUser(keyParameter: KeyParameterValidation) = when(keyParameter) {
    KeyParameterValidation.phone -> {
        !user.data.phone.isBlank()
    }
}

enum class KeyParameterValidation {
    phone
}