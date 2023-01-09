package com.app.nftcreatorappz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.app.nftcreatorappz.databinding.ActivitySplashBinding
import com.app.nftcreatorappz.utils.Tools

class ActivitySplash : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Tools.setSystemBarColor(this)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, ActivityHome::class.java)
            startActivity(intent)
            finish()
        }, 800)
    }
}