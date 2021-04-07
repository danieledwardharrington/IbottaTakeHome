package com.dharringtondev.ibottatakehome.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dharringtondev.ibottatakehome.persistence.dao.OfferDao
import com.dharringtondev.ibottatakehome.persistence.entities.OfferEntity

@Database(entities = [OfferEntity::class], version = 1)
abstract class OfferDatabase: RoomDatabase() {

    abstract fun offerDao(): OfferDao

    companion object {
        var TEST_MODE = false
        private var offerInstance: OfferDatabase? = null

        fun getOfferInstance(context: Context): OfferDatabase? {
            if (offerInstance == null) {
                if (TEST_MODE) {
                    offerInstance = Room.inMemoryDatabaseBuilder(context, OfferDatabase::class.java).allowMainThreadQueries().build()
                }

                synchronized(OfferDatabase::class) {
                    offerInstance = Room.databaseBuilder(
                        context.applicationContext,
                        OfferDatabase::class.java,
                        "offer_db.db"
                    ).fallbackToDestructiveMigration().build()
                }
            }

            return offerInstance
        }
    }

}