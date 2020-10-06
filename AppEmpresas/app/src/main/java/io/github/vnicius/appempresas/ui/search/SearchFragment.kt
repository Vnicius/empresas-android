package io.github.vnicius.appempresas.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePadding
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import io.github.vnicius.appempresas.R
import io.github.vnicius.appempresas.data.auth.AuthException
import io.github.vnicius.appempresas.data.model.Enterprise
import io.github.vnicius.appempresas.databinding.FragmentSearchBinding
import io.github.vnicius.appempresas.extension.*
import io.github.vnicius.appempresas.ui.enterprisedetails.EnterpriseDetailsFragment
import io.github.vnicius.appempresas.ui.signin.SignInActivity
import io.github.vnicius.appempresas.util.GlobalSafeClickHelper
import io.github.vnicius.appempresas.util.RequestState
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private lateinit var viewBinding: FragmentSearchBinding
    private lateinit var searchResultAdapter: SearchResultAdapter

    private var performSearchRunnable: Runnable? = null

    private val viewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onStart() {
        viewBinding.container.requestApplyInsets()
        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupWindowInset()
        setupStartSearchButton()
        setupRequestStateUpdate()
        setupCancelSearchButton()
        setupSearchButton()
        setupSearchAction()
        setupTextChange()
        setupSearchResult()
        setupSearchResultList()
        setupBackStackListener()
    }

    // region Setup

    private fun setupWindowInset() {
        viewBinding.searchResultList.doOnApplyWindowInsets { view, windowInsets, initialPadding ->
            view.updatePadding(bottom = initialPadding.bottom + windowInsets.systemWindowInsetBottom)
        }
    }

    private fun setupStartSearchButton() {
        viewBinding.searchButton.globalSafeClickListener {
            startSearch()
            viewBinding.startSearchMessage.isVisible = false
        }
    }

    private fun setupCancelSearchButton() {
        viewBinding.searchTextInputLayout.apply {
            isEndIconVisible = true
            endIconDrawable =
                ResourcesCompat.getDrawable(context.resources, R.drawable.ic_close, null)
            setEndIconOnClickListener {
                cancelSearch()
            }
        }
    }

    private fun setupSearchButton() {
        viewBinding.searchTextInputLayout.setStartIconOnClickListener {
            if (GlobalSafeClickHelper.canClick()) {
                performSearch()
            }
        }
    }

    private fun setupSearchAction() {
        viewBinding.searchTextInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                true
            } else {
                false
            }
        }
    }

    private fun setupTextChange() {
        viewBinding.searchTextInput.doOnTextChanged { text, _, _, _ ->
            text?.takeIf { it.isNotEmpty() }?.let { text ->
                performSearchDelayed()
            }
        }
    }

    private fun setupSearchResultList() {
        viewBinding.searchResultList.apply {
            adapter = SearchResultAdapter(::onEnterpriseSelected).also {
                searchResultAdapter = it
            }
            itemAnimator = null
        }
    }

    private fun setupSearchResult() {
        viewModel.searchResult.observe(viewLifecycleOwner, {
            if (it?.isNotEmpty() == true) {
                if (viewBinding.noResultMessage.isVisible) {
                    viewBinding.noResultMessage.isVisible = false
                }

                if (!viewBinding.searchResultList.isVisible) {
                    viewBinding.searchResultList.isVisible = true
                }

                searchResultAdapter.updateList(it)
            } else if (performSearchRunnable == null) {
                searchResultAdapter.updateList(listOf())
                viewBinding.apply {
                    noResultMessage.isVisible = true
                    searchResultList.isVisible = false
                }
            }
        })
    }

    private fun setupRequestStateUpdate() {
        viewModel.requestState.observe(viewLifecycleOwner, {
            if (it is RequestState.FAILED && it.exception is AuthException.UnauthorizedException) {
                onAuthError()
            }
        })
    }

    private fun setupBackStackListener() {
        childFragmentManager.addOnBackStackChangedListener {
            if (childFragmentManager.backStackEntryCount == 0) {
                context?.let {
                    activity?.setTranslucentWindowControls(
                        navigationBarColor = ContextCompat.getColor(
                            it,
                            R.color.colorDefaultNavigationBar
                        ), withLightStatusBar = false, withLightNavigationBar = true
                    )
                }
            }
        }
    }

    // endregion

    private fun search() {
        viewBinding.searchTextInput.text?.toString()?.let {
            viewModel.search(it)
        }
    }

    private fun performSearch() {
        search()
        cancelDelayedSearch()
        closeKeyboard()
    }

    private fun startSearch() {
        viewBinding.apply {
            logo.isVisible = false
            searchButton.isVisible = false
            searchTextInputLayout.isVisible = true
            searchTextInputLayout.requestFocus()
            activity?.showKeyboard()
        }
    }

    private fun cancelSearch() {
        closeKeyboard()
        viewBinding.apply {
            searchTextInputLayout.isVisible = false
            searchTextInput.text = null
            logo.isVisible = true
            searchButton.isVisible = true
        }
    }

    private fun closeKeyboard() {
        activity?.hideKeyboard()
        viewBinding.root.requestFocus()
    }

    private fun performSearchDelayed() {
        cancelDelayedSearch()
        performSearchRunnable = Runnable {
            search()
            performSearchRunnable = null
        }.also {
            view?.postDelayed(it, SEARCH_DELAY)
        }
    }

    private fun cancelDelayedSearch() {
        performSearchRunnable?.let {
            view?.removeCallbacks(it)
            performSearchRunnable = null
        }
    }

    private fun onAuthError() {
        activity?.apply {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }

    private fun onEnterpriseSelected(enterprise: Enterprise) {
        closeKeyboard()
        childFragmentManager.commit {
            setCustomAnimations(R.anim.slide_in_from_right, 0, 0, R.anim.slide_out_to_right)
            add(
                viewBinding.container.id,
                EnterpriseDetailsFragment.newInstance(enterprise),
                EnterpriseDetailsFragment.TAG
            )
            addToBackStack(EnterpriseDetailsFragment.TAG)
        }
    }

    companion object {
        const val TAG = "io.github.vnicius.appempresas.ui.search.SearchFragment"
        private const val SEARCH_DELAY = 300L

        fun newInstance() = SearchFragment()
    }
}