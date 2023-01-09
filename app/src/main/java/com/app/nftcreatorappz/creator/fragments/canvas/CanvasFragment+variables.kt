package com.app.nftcreatorappz.creator.fragments.canvas

import com.app.nftcreatorappz.creator.customviews.pixelgridview.PixelGridView
import com.app.nftcreatorappz.databinding.FragmentCanvasBinding
import com.app.nftcreatorappz.creator.listeners.CanvasFragmentListener

var binding_: FragmentCanvasBinding? = null

val binding get() = binding_!!

lateinit var caller: CanvasFragmentListener

lateinit var myCanvasViewInstance: PixelGridView