package io.github.vnicius.appempresas.ui.enterprisedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.os.bundleOf
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import coil.load
import io.github.vnicius.appempresas.R
import io.github.vnicius.appempresas.data.model.Enterprise
import io.github.vnicius.appempresas.databinding.FragmentEnterpriseDetailsBinding
import io.github.vnicius.appempresas.extension.doOnApplyWindowInsets
import io.github.vnicius.appempresas.extension.onClickListener
import io.github.vnicius.appempresas.extension.setTranslucentWindowControls
import org.koin.androidx.viewmodel.ext.android.viewModel


class EnterpriseDetailsFragment : Fragment() {

    private lateinit var viewBinding: FragmentEnterpriseDetailsBinding

    private val viewModel: EnterpriseDetailsViewModel by viewModel()

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            parentFragmentManager.popBackStackImmediate()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentEnterpriseDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(backPressedCallback)

        readArgs()
        setupTranslucentWindowControls()
        setupContentWindowInsets()
        setupEnterpriseData()
        setupBackButton()
    }

    override fun onDestroy() {
        backPressedCallback.remove()
        super.onDestroy()
    }

    // region Setup

    private fun readArgs() {
        viewModel.enterprise = arguments?.getParcelable(ARG_ENTERPRISE)
    }

    private fun setupTranslucentWindowControls() {
        context?.let {
            activity?.setTranslucentWindowControls(
                navigationBarColor = ContextCompat.getColor(
                    it,
                    R.color.colorWhiteNavigationBar
                ), withLightStatusBar = false, withLightNavigationBar = true
            )
        }
    }

    private fun setupContentWindowInsets() {
        viewBinding.contentContainer.doOnApplyWindowInsets { view, windowInsets, initialPadding ->
            view.updatePadding(bottom = initialPadding.bottom + windowInsets.systemWindowInsetBottom)
        }
    }

    private fun setupEnterpriseData() {
        viewModel.enterprise?.let { enterprise ->
            viewBinding.apply {
                name.text = enterprise.enterpriseName
                photo.load(enterprise.photo) {
                    crossfade(true)
                    placeholder(
                        ContextCompat.getColor(context ?: return, R.color.colorPrimary).toDrawable()
                    )
                }
                description.text = enterprise.description
            }
        }
    }

    private fun setupBackButton() {
        viewBinding.backButton.onClickListener {
            activity?.onBackPressed()
        }
    }

    // endregion

    companion object {
        const val TAG = "EnterpriseDetailsFragment"
        private const val ARG_ENTERPRISE = "arg_enterprise"

        fun newInstance(enterPrise: Enterprise? = null) = EnterpriseDetailsFragment().apply {
            arguments = bundleOf(ARG_ENTERPRISE to enterPrise)
        }
    }
}