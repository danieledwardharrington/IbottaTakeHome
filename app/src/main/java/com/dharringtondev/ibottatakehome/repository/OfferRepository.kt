package com.dharringtondev.ibottatakehome.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.dharringtondev.ibottatakehome.data.DataReader
import com.dharringtondev.ibottatakehome.data.models.OfferModel
import com.dharringtondev.ibottatakehome.persistence.database.OfferDatabase
import com.dharringtondev.ibottatakehome.persistence.entities.OfferEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class OfferRepository(application: Application) {

    private val TAG = "OffersRepository"

    private val offerDatabase = OfferDatabase.getOfferInstance(application)!!
    private val dataReader = DataReader(application)

    var allOffersLD = MutableLiveData<List<OfferEntity>>()
    var singleOfferLD = MutableLiveData<OfferEntity>()
    var offersFromFileLD = MutableLiveData<List<OfferModel>>()

    //ROOM FUNCTIONS
    fun insertOffer(offerEntity: OfferEntity) {
        GlobalScope.launch {
            offerDatabase.offerDao().insertOffer(offerEntity)
            Log.d(TAG, "insertOffer")
        }
    }

    fun deleteOffer(offerEntity: OfferEntity) {
        GlobalScope.launch {
            offerDatabase.offerDao().deleteOffer(offerEntity)
            Log.d(TAG, "deleteOffer")
        }
    }

    fun updateOffer(offerEntity: OfferEntity) {
        GlobalScope.launch {
            offerDatabase.offerDao().updateOffer(offerEntity)
            Log.d(TAG, "updateOffer")
        }
    }

    fun deleteAllOffers() {
        GlobalScope.launch {
            offerDatabase.offerDao().deleteAllOffers()
            Log.d(TAG, "deleteAllOffers")
        }
    }

    fun getOfferById(offerId: String) {
        GlobalScope.launch {
            singleOfferLD.postValue(offerDatabase.offerDao().getOfferById(offerId))
            Log.d(TAG, "getOfferById")
        }
    }

    fun getAllOffers() = offerDatabase.offerDao().getAllOffers()

    //READING FROM THE JSON FILE
    fun getDataFromFile() {
        offersFromFileLD.postValue(dataReader.getData())
    }

}