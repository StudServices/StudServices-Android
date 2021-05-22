package dev.techpolis.studservice.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import dev.techpolis.studservice.R
import dev.techpolis.studservice.common.mvp.MvpViewObservableBase
import dev.techpolis.studservice.views.auth.signin.SignInMvpView

class MainMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?, override var rootView: View
) : MvpViewObservableBase<MainMvpView.Listener>(), MainMvpView
{

    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main, parent, false)

    private val buttonUserServices: AppCompatButton = findViewById(R.id.menu_item__userServices)
    private val buttonServices: AppCompatEditText = findViewById(R.id.menu_item__services)
    private val buttonProfile: AppCompatEditText = findViewById(R.id.menu_item__profile)

    init {
        buttonProfile.setOnClickListener {
            listeners.forEach {
                it.onProfileServices()
            }
        }

        buttonUserServices.setOnClickListener {
            listeners.forEach {
                it.onMyServicesClicked()
            }
        }

        buttonServices.setOnClickListener {
            listeners.forEach {
                it.onServicesClicked()
            }
        }
    }

    override fun registerListener(listener: SignInMvpView.Listener) {
        TODO("Not yet implemented")
    }

    override fun unregisterListener(listener: SignInMvpView.Listener) {
        TODO("Not yet implemented")
    }
}