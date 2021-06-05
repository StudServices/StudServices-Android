package dev.techpolis.studservice.screens.auth.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import dev.techpolis.studservice.screens.common.base.BaseFragment

class SignInFragment : BaseFragment(), LoginIntentListener {

    private lateinit var loginForResult: ActivityResultLauncher<Intent>
    private lateinit var presenter: SignInPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = presenterFactory.createSignInPresenter(this)
        loginForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            presenter::handleResult
        )
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

    override fun launchLoginForResult(signInIntent: Intent) {
        loginForResult.launch(signInIntent)
    }
}

interface LoginIntentListener {
    fun launchLoginForResult(signInIntent: Intent)
}
