package com.dharringtondev.ibottatakehome.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dharringtondev.ibottatakehome.R
import com.dharringtondev.ibottatakehome.databinding.DialogFragmentOfferDetailsBinding
import com.dharringtondev.ibottatakehome.persistence.entities.OfferEntity
import com.dharringtondev.ibottatakehome.viewmodel.OfferViewModel
import com.dharringtondev.ibottatakehome.viewmodel.factory.OfferViewModelFactory

class OfferDetailsDialogFragment: DialogFragment() {
    private val TAG = "OfferDetailsDialogFragment"

    private val fmdArgs: OfferDetailsDialogFragmentArgs by navArgs()
    private var _binding: DialogFragmentOfferDetailsBinding? = null
    private lateinit var offerEntity: OfferEntity
    private lateinit var offerViewModel: OfferViewModel
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.d(TAG, "onCreateDialog")
        _binding = DialogFragmentOfferDetailsBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root).setPositiveButton("Okay") {_, _ ->
            checkFavorite()
            dismiss()}
        initComponents()
        return builder.create()
    }

    private fun initComponents() {
        Log.d(TAG, "initComponents")
        offerViewModel = ViewModelProvider(this, OfferViewModelFactory(requireActivity().application)).get(OfferViewModel::class.java)
        val offerId = fmdArgs.offerId
        Log.d(TAG, "initComponents; offerId = $offerId")
        offerViewModel.getOfferById(offerId)
        offerViewModel.getSingleOfferLD().observe(this, Observer { newEntity ->
            offerEntity = newEntity
            setupViews()
        })
    }

    private fun setupViews() {
        Log.d(TAG, "setupViews")
        binding.apply {
            offerNameTv.text = offerEntity.name
            cashBackTv.text = offerEntity.currentValue
            termsTextTv.text = offerEntity.terms
            descriptionTextTv.text = offerEntity.description

            //setting the state of the switch based on the fav boolean in the object
            if (offerEntity.isFav) {
                favSwitch.isChecked = true
            }

            Glide.with(requireContext())
                .load(offerEntity.imageUrl)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(offerImageIv)
        }
    }

    //checking the favorite status of the switch and applying new data accordingly
    //Can't use the old interface callback style with nav component so I'm doing fragmentResult
    private fun checkFavorite() {
        Log.d(TAG, "checkFavorite")
        if (binding.favSwitch.isChecked && !offerEntity.isFav) {
            Log.d(TAG, "setting favorite to true")
            offerEntity.isFav = true
            offerViewModel.updateOffer(offerEntity)
            setFragmentResult(REQUEST_KEY, bundleOf("data" to "toggled"))
        } else if (!binding.favSwitch.isChecked && offerEntity.isFav) {
            Log.d(TAG, "setting favorite to false")
            offerEntity.isFav = false
            offerViewModel.updateOffer(offerEntity)
            setFragmentResult(REQUEST_KEY, bundleOf("data" to "toggled"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
        _binding = null
    }

    //for callback
    companion object {
        val REQUEST_KEY = "OFFER_DIALOG_REQUEST_KEY"
    }
}