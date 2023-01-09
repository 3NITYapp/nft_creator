package com.app.nftcreatorappz.creator.activities.canvas

import android.content.Context
import com.app.nftcreatorappz.data.SharedPref

fun CanvasActivity.initSharedPreferenceObject() {
    sharedPreferenceObject = this.getPreferences(Context.MODE_PRIVATE)
    sharedPref = SharedPref(this)
}