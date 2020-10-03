package io.github.vnicius.appempresas.ui.splashscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.vnicius.appempresas.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}