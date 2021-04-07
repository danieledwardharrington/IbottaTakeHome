package com.dharringtondev.ibottatakehome.data

import android.content.Context
import android.util.Log
import com.dharringtondev.ibottatakehome.data.models.OfferModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DataReader(private val context: Context) {

    /*
    We have a JSON file for this project but normally getting data would
    probably be done using something like retrofit, which I'd have a service
    for. I'm making this class to sort of mimic that and keep things to one responsibility.
     */

    private val TAG = "DataReader"

    fun getData(): List<OfferModel> {
        //getting all the json data from the file
        val jsonString = context.assets.open("Offers.json").bufferedReader().use { it.readText() }
        val offerList: List<OfferModel> = Json.decodeFromString(jsonString)

        for (offer in offerList) {
            Log.d(TAG, offer.name)
        }

        return offerList
    }


}