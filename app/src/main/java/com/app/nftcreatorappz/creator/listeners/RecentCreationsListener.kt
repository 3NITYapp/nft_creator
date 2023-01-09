package com.app.nftcreatorappz.creator.listeners

import com.app.nftcreatorappz.creator.models.PixelArt

interface RecentCreationsListener {
    fun onCreationTapped(creationTapped: PixelArt)
    fun onCreationLongTapped(creationTapped: PixelArt)
}