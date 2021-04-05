package dev.techpolis.studservice.views.auth.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dev.techpolis.studservice.R
import dev.techpolis.studservice.utils.requireGrandParentFragment

class SignUpFragment : Fragment() {

    private lateinit var activityNavController: NavController
    private lateinit var btnSignUp: AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth__sign_up, container, false)
    }

    private fun View.initViews() {
        btnSignUp = findViewById(R.id.fragment_auth__sign_up__button)
        activityNavController = requireGrandParentFragment().findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.initViews()

        btnSignUp.setOnClickListener {
            activityNavController.navigate(R.id.mainFragment)
        }
    }

}
