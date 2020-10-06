package io.github.vnicius.appempresas.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.commitNow
import io.github.vnicius.appempresas.R
import io.github.vnicius.appempresas.databinding.ActivityMainBinding
import io.github.vnicius.appempresas.extension.setTranslucentWindowControls
import io.github.vnicius.appempresas.extension.setupFullScreenSystemUiFlags
import io.github.vnicius.appempresas.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        setupFullScreenSystemUiFlags()
        setTranslucentWindowControls(
            navigationBarColor = ContextCompat.getColor(
                baseContext,
                R.color.colorDefaultNavigationBar
            ), withLightStatusBar = false, withLightNavigationBar = true
        )
        navigateToSearchScreen()
    }

    private fun navigateToSearchScreen() {
        supportFragmentManager.commitNow {
            add(viewBinding.container.id, SearchFragment.newInstance())
        }
    }
}