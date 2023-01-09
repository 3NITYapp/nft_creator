package com.app.nftcreatorappz.creator.activities.canvas

fun setPrimaryPixelColor(color: Int) {
    primaryColor = color
    binding.colorPrimaryViewActivityCanvas.setBackgroundColor(color)
}