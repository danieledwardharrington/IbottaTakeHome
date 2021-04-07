package com.dharringtondev.ibottatakehome

import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.dharringtondev.ibottatakehome.persistence.dao.OfferDao
import com.dharringtondev.ibottatakehome.persistence.database.OfferDatabase
import com.dharringtondev.ibottatakehome.persistence.entities.OfferEntity
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OfferDatabaseTest {

    private var offerDao: OfferDao? = null

    @Before
    fun setup() {
        OfferDatabase.TEST_MODE = true
        offerDao = OfferDatabase.getOfferInstance(InstrumentationRegistry.getInstrumentation().context)!!.offerDao()
    }

    @After
    fun tearDown() {}

    @Test
    suspend fun flush() {
        offerDao!!.deleteAllOffers()
        Assert.assertEquals(offerDao!!.getAllOffers().value?.size, 0)
    }

}