package com.dharringtondev.ibottatakehome.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "offers_table", indices = [Index(value = arrayOf("offer_id"), unique = true)])
data class OfferEntity(@ColumnInfo(name = "offer_id")
                       var offerId: String,

                       @ColumnInfo(name = "image_url")
                       var imageUrl: String?,

                       @ColumnInfo(name = "name")
                       var name: String,

                       @ColumnInfo(name = "description")
                       var description: String,

                       @ColumnInfo(name = "terms")
                       var terms: String,

                       @ColumnInfo(name = "current_value")
                       var currentValue: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "is_fav")
    var isFav: Boolean = false
}
