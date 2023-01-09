package com.app.nftcreatorappz.creator.algorithms

import android.graphics.Bitmap
import com.app.nftcreatorappz.creator.models.BitmapAction

class AlgorithmInfoParameter(
    val bitmap: Bitmap,
    val currentBitmapAction: BitmapAction,
    var color: Int,
    val currentBitmapActionData: List<BitmapAction>? = null
)
