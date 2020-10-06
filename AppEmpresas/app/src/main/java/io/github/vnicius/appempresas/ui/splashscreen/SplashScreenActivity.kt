package io.github.vnicius.appempresas.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.github.vnicius.appempresas.R
import io.github.vnicius.appempresas.databinding.ActivitySplashScreenBinding
import io.github.vnicius.appempresas.extension.setTranslucentWindowControls
import io.github.vnicius.appempresas.extension.setupFullScreenSystemUiFlags
import io.github.vnicius.appempresas.ui.main.MainActivity
import io.github.vnicius.appempresas.ui.signin.SignInActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivitySplashScreenBinding

    private val viewModel: SplashScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupFullScreenSystemUiFlags()
        setTranslucentWindowControls(
            navigationBarColor = ContextCompat.getColor(
                baseContext,
                R.color.colorDefaultNavigationBar
            ), withLightStatusBar = true, withLightNavigationBar = true
        )

        viewBinding.root.postDelayed({
            startApp()
        }, START_DELAY)
    }

    private fun startApp() {
        viewModel.checkCurrentAuthentication(::onSuccessAuth, ::onFailureAuth)
    }

    private fun onSuccessAuth() {
        startScreen(MainActivity::class.java)
    }

    private fun onFailureAuth() {
        //startScreen(MainActivity::class.java)
        startScreen(SignInActivity::class.java)
    }

    private fun <T> startScreen(className: Class<T>) {
        startActivity(Intent(this, className))
    }

    companion object {
        private const val START_DELAY = 1500L
    }
}