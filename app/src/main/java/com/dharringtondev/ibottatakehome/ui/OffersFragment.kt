package com.dharringtondev.ibottatakehome.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dharringtondev.ibottatakehome.R
import com.dharringtondev.ibottatakehome.adapters.OfferAdapter
import com.dharringtondev.ibottatakehome.databinding.FragmentOffersBinding
import com.dharringtondev.ibottatakehome.persistence.entities.OfferEntity
import com.dharringtondev.ibottatakehome.util.Helpers
import com.dharringtondev.ibottatakehome.viewmodel.OfferViewModel
import com.dharringtondev.ibottatakehome.viewmodel.factory.OfferViewModelFactory

class OffersFragment: Fragment(), OfferAdapter.OnOfferClickedListener {
    private val TAG = "OffersFragment"

    private var _binding: FragmentOffersBinding? = null
    private val binding get() = _binding!!

    private val offerAdapter = OfferAdapter()
    private lateinit var offerViewModel: OfferViewModel

    //handling whether or not to load from file with shared prefs
    private val LOAD_PREF = "LoadPref"
    private val LOAD_BOOL_KEY = "LoadBool"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        _binding = FragmentOffersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        Log.d(TAG, "initComponents")
        checkLoad()
        initViewModel()
        setFragmentResultListener(OfferDetailsDialogFragment.REQUEST_KEY) { requestKey, bundle ->
            Log.d(TAG, "setFragmentResultListener")
            offerViewModel.getAllOffers()
        }
    }

    //checking if we've already gotten data from the JSON file with shared prefs
    private fun checkLoad(): Boolean {
        Log.d(TAG, "checkLoad")
        val prefs = requireActivity().getSharedPreferences(LOAD_PREF, Context.MODE_PRIVATE)
        val loaded = prefs.getBoolean(LOAD_BOOL_KEY, false)
        return loaded
    }

    //saving in shared prefs that we have gotten the data and don't need to do this next launch
    private fun saveLoad() {
        Log.d(TAG, "saveLoad")
        val prefs = requireActivity().getSharedPreferences(LOAD_PREF, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putBoolean(LOAD_BOOL_KEY, true).apply()
    }

    private fun initViewModel() {
        Log.d(TAG, "initViewModel")
        offerViewModel = ViewModelProvider(this, OfferViewModelFactory(requireActivity().application)).get(OfferViewModel::class.java)
        initRV()

        /*
        Basically checking if the app has been run before since the data is all static and in a file.
        Basically just to avoid re-reading the file every time.
         */
        if (!checkLoad()) {
            Log.d(TAG, "checkLoad false")
            offerViewModel.getOffersFromFile()
            offerViewModel.getOffersFromFileLD()
                .observe(viewLifecycleOwner, Observer { offerModelList ->
                    for (offerModel in offerModelList) {
                        offerModel.imageUrl?.let { Log.d("IMAGEURL", it) }
                        val offerEntity =
                            context?.let { it1 -> Helpers(it1).offerModelToEntity(offerModel) }
                        offerViewModel.insertOffer(offerEntity!!)
                        saveLoad()
                    }
                })
        }

        offerViewModel.getAllOffers().observe(viewLifecycleOwner, Observer { offerEntityList ->
            Log.d(TAG, "getAllOffersLD observe")
            offerAdapter.submitData(offerEntityList)
        })

    }

    private fun initRV() {
        Log.d(TAG, "initRV")
        binding.offersRv.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = offerAdapter
            offerAdapter.setOfferClickedListener(this@OffersFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //sending the offer id to the dialog for specifics
    override fun onOfferClicked(offerEntity: OfferEntity) {
        Log.d(TAG, "onOfferClicked")
        val action = OffersFragmentDirections.actionOffersFragmentToOfferDetailsDialogFragment(offerEntity.offerId)
        findNavController().navigate(action)
    }

}