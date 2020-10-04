package io.github.vnicius.appempresas.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import io.github.vnicius.appempresas.databinding.ActivityMainBinding
import io.github.vnicius.appempresas.extension.setTranslucentWindowControls
import io.github.vnicius.appempresas.extension.setupFullScreenSystemUiFlags
import io.github.vnicius.appempresas.ui.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewBiding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBiding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBiding.root)

        setupFullScreenSystemUiFlags()
        setTranslucentWindowControls(false, true)
        navigateToSearchScreen()
    }

    private fun navigateToSearchScreen() {
        supportFragmentManager.commit {
            add(viewBiding.container.id, SearchFragment.newInstance())
        }
    }
}