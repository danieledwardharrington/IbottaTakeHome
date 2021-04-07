package com.dharringtondev.ibottatakehome.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.dharringtondev.ibottatakehome.data.models.OfferModel
import com.dharringtondev.ibottatakehome.persistence.entities.OfferEntity
import com.google.android.material.snackbar.Snackbar

class Helpers(val context: Context) {

    /*
    Just a quick class with some helper functions I might need
    in various places in the app. Usually I'd have a function for
    checking network connection but it's not needed for this project.
    Not necessarily going to use these functions for this project but
    I like having these here.
     */

    private val TAG = "Helpers"

    //toasts and snackbars
    fun showShortToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showShortSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    fun showLongSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    //mapping remote model to room entity
    fun offerModelToEntity(offerModel: OfferModel): OfferEntity {
        val offerEntity = OfferEntity(offerModel.id,
                            offerModel.imageUrl,
                            offerModel.name,
                            offerModel.description,
                            offerModel.terms,
                            offerModel.currentValue)

        offerEntity.isFav = false

        return offerEntity
    }

}