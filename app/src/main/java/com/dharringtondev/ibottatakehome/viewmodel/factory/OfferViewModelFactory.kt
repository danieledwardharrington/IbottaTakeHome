package com.dharringtondev.ibottatakehome.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dharringtondev.ibottatakehome.viewmodel.OfferViewModel

class OfferViewModelFactory(private val application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return OfferViewModel(application) as T
    }
}