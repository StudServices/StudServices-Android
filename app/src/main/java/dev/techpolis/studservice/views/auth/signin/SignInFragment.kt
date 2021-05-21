package dev.techpolis.studservice.views.auth.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dev.techpolis.studservice.R

import dev.techpolis.studservice.common.base.BaseFragment
import dev.techpolis.studservice.views.auth.signup.SignUpFragment
import dev.techpolis.studservice.views.auth.signup.SignUpMvpView
import dev.techpolis.studservice.views.auth.signup.SignUpPresenter

class SignInFragment : BaseFragment() {

    private lateinit var presenter: SignInPresenter

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
        presenter.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    companion object {
        fun newInstance(): Fragment = SignInFragment()
    }

}
