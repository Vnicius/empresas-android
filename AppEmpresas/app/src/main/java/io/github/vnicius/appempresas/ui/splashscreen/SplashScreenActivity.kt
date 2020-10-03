package io.github.vnicius.appempresas.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.vnicius.appempresas.databinding.ActivitySplashScreenBinding
import io.github.vnicius.appempresas.extension.setTranslucentWindowControls
import io.github.vnicius.appempresas.extension.setupFullScreenSystemUiFlags
import io.github.vnicius.appempresas.ui.signin.SignInActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupFullScreenSystemUiFlags()
        setTranslucentWindowControls(true, true)

        viewBinding.root.postDelayed({
            startActivity(Intent(this, SignInActivity::class.java))
        }, START_DELAY)
    }

    companion object {
        private const val START_DELAY = 1500L
    }
}