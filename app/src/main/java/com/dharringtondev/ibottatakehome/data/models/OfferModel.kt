package com.dharringtondev.ibottatakehome.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfferModel(
    @SerialName("id")
    var id: String,

    @SerialName("url")
    var imageUrl: String?,

    @SerialName("name")
    var name: String,

    @SerialName("description")
    var description: String,

    @SerialName("terms")
    var terms: String,

    @SerialName("current_value")
    var currentValue: String
)