package com.app.nftcreatorappz.creator.activities.canvas

fun hideMenuItems() {
    for (i in 0 until menu.size()) {
        menu.getItem(i).isVisible = false
    }
}