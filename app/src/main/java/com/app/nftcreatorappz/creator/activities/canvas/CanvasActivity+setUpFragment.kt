package com.app.nftcreatorappz.creator.activities.canvas

import com.app.nftcreatorappz.R
import com.app.nftcreatorappz.creator.fragments.outercanvas.OuterCanvasFragment

fun CanvasActivity.setUpFragment() {
    outerCanvasInstance = if (index == -1) {
        OuterCanvasFragment.newInstance(spanCount)
    } else {
        OuterCanvasFragment.newInstance(spanCount, true)
    }
    supportFragmentManager.beginTransaction().add(R.id.outerCanvasFragmentHostActivityCanvas, outerCanvasInstance).commit()
}