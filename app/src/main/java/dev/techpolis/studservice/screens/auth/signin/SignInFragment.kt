package dev.techpolis.studservice.screens.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.techpolis.studservice.screens.common.base.BaseFragment

class SignInFragment : BaseFragment() {

    private lateinit var presenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = presenterFactory.createSignInPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: SignInMvpView = mvpViewFactory.createSignInMvpView(container)
        presenter.bindView(view)
        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    companion object {
        fun newInstance(): Fragment = SignInFragment()
    }

}
