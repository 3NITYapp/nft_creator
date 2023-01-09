package com.app.nftcreatorappz.data

import android.app.Application
import com.app.nftcreatorappz.creator.dao.PixelArtCreationsDao
import com.app.nftcreatorappz.creator.database.AppData
import com.app.nftcreatorappz.creator.database.ColorPalettesDatabase
import com.app.nftcreatorappz.creator.database.PixelArtDatabase

class DataApp : Application() {

    lateinit var sharedPref: SharedPref
    lateinit var dao: PixelArtCreationsDao

    override fun onCreate() {
        super.onCreate()
        sharedPref = SharedPref(this)

        AppData.pixelArtDB = PixelArtDatabase.getDatabase(this)
        AppData.colorPalettesDB = ColorPalettesDatabase.getDatabase(this)

        dao = PixelArtDatabase.getDatabase(this).pixelArtCreationsDao()

    }



}