package com.dharringtondev.ibottatakehome.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dharringtondev.ibottatakehome.R
import com.dharringtondev.ibottatakehome.databinding.CardviewOfferBinding
import com.dharringtondev.ibottatakehome.persistence.entities.OfferEntity

class OfferAdapter: RecyclerView.Adapter<OfferAdapter.OfferViewHolder>() {

    private val TAG = "OfferAdapter"

    private var offersList = mutableListOf<OfferEntity>()
    private lateinit var clickedListener: OnOfferClickedListener

    //this if for handling when an offer is clicked
    interface OnOfferClickedListener {
        fun onOfferClicked(offerEntity: OfferEntity)
    }

    fun setOfferClickedListener(newListener: OnOfferClickedListener) {
        clickedListener = newListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        Log.d(TAG, "onCreateViewHolder")
        val itemBinding = CardviewOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder")
        holder.bind(offersList[holder.adapterPosition])
        holder.itemView.setOnClickListener {
            Log.d(TAG, "Offer clicked")
            offersList[holder.adapterPosition].let { offerEntity ->
                clickedListener.onOfferClicked(offerEntity)
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount")
        return offersList.size
    }

    fun addOffers(offers: List<OfferEntity>) {
        Log.d(TAG, "addOffers")
        val oldPosition = offersList.size
        offersList.addAll(offers)
        notifyItemRangeChanged(oldPosition, offers.size)
    }

    fun submitData(offers: List<OfferEntity>) {
        offersList.clear()
        offersList.addAll(offers)
        notifyDataSetChanged()
    }

    class OfferViewHolder(itemBinding: CardviewOfferBinding, parentContext: Context): RecyclerView.ViewHolder(itemBinding.root) {
        private val TAG = "OfferViewHolder"

        private val context = parentContext
        private val cashBackTV = itemBinding.cashBackTv
        private val nameTV = itemBinding.nameTv
        private val offerIV = itemBinding.offerIv
        private val favIconIV = itemBinding.favoriteIconIv

        fun bind(offerEntity: OfferEntity) {
            Log.d(TAG, "bind")

            cashBackTV.text = offerEntity.currentValue
            nameTV.text = offerEntity.name

            //setting filled icon if object is fav'd or outline if not
            if (!offerEntity.isFav) {
                Log.d(TAG, "ID: ${offerEntity.offerId} setting icon outline")
                favIconIV.setImageResource(R.drawable.ic_favorite_outline)
            } else if (offerEntity.isFav) {
                Log.d(TAG, "ID: ${offerEntity.offerId}setting icon filled")
                favIconIV.setImageResource(R.drawable.ic_favorite_filled)
            }

            //loading image with Glide
            Glide.with(context)
                    .load(offerEntity.imageUrl)
                    .error(R.drawable.ic_placeholder) //I noticed two of the JSON objects have null images, that's what this is for
                    .placeholder(R.drawable.ic_placeholder)
                    .into(offerIV)
        }
    }
}