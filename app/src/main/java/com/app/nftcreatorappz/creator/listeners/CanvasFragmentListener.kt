package com.app.nftcreatorappz.creator.listeners

import com.app.nftcreatorappz.creator.models.Coordinates

interface CanvasFragmentListener {
    fun onPixelTapped(coordinatesTapped: Coordinates)
    fun onActionUp()
}