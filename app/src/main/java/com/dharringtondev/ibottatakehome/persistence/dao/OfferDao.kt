package com.dharringtondev.ibottatakehome.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dharringtondev.ibottatakehome.persistence.entities.OfferEntity

@Dao
interface OfferDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOffer(offerEntity: OfferEntity)

    @Delete
    suspend fun deleteOffer(offerEntity: OfferEntity)

    @Update
    suspend fun updateOffer(offerEntity: OfferEntity)

    @Query("DELETE FROM offers_table")
    suspend fun deleteAllOffers()

    @Query("SELECT * FROM offers_table")
    fun getAllOffers(): LiveData<List<OfferEntity>>

    @Query("SELECT * FROM offers_table WHERE offer_id=:offerId")
    suspend fun getOfferById(offerId: String): OfferEntity

}