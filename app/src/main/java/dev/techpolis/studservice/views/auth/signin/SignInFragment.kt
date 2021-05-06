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



class SignInFragment : BaseFragment() {

    private lateinit var activityNavController: NavController
    private lateinit var authNavController: NavController

    private lateinit var btnSignIn: AppCompatButton
    private lateinit var tvSignUp: AppCompatTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth__sign_in, container, false)
    }

    private fun View.initViews() {
        btnSignIn = findViewById(R.id.fragment_auth__sign_in__button)
        tvSignUp = findViewById(R.id.fragment_auth__sign_in__link)

        authNavController = findNavController()
        activityNavController = requireGrandParentFragment().findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.initViews()

        btnSignIn.setOnClickListener {
            activityNavController.navigate(R.id.mainFragment)
        }

        tvSignUp.setOnClickListener {
            authNavController.navigate(R.id.signUpFragment)
        }

    }

}
