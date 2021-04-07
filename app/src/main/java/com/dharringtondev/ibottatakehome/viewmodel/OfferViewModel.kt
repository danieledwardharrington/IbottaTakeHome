package com.dharringtondev.ibottatakehome.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dharringtondev.ibottatakehome.data.models.OfferModel
import com.dharringtondev.ibottatakehome.persistence.entities.OfferEntity
import com.dharringtondev.ibottatakehome.repository.OfferRepository

class OfferViewModel(application: Application): ViewModel() {

    private val TAG = "OfferViewModel"

    private val offerRepository = OfferRepository(application)

    fun insertOffer(offerEntity: OfferEntity) {
        offerRepository.insertOffer(offerEntity)
    }

    fun deleteOffer(offerEntity: OfferEntity) {
        offerRepository.deleteOffer(offerEntity)
    }

    fun updateOffer(offerEntity: OfferEntity) {
        offerRepository.updateOffer(offerEntity)
    }

    fun deleteAllOffers() {
        offerRepository.deleteAllOffers()
    }

    fun getAllOffers() = offerRepository.getAllOffers()

    fun getOfferById(offerId: String) {
        offerRepository.getOfferById(offerId)
    }

    fun getOffersFromFile() {
        offerRepository.getDataFromFile()
    }

    //getting live data from repo
    fun getOffersFromFileLD(): MutableLiveData<List<OfferModel>> {
        return offerRepository.offersFromFileLD
    }

    fun getAllOffersLD(): MutableLiveData<List<OfferEntity>> {
        return offerRepository.allOffersLD
    }

    fun getSingleOfferLD(): MutableLiveData<OfferEntity> {
        return offerRepository.singleOfferLD
    }

}