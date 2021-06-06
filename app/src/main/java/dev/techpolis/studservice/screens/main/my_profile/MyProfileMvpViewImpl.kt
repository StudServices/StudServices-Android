package dev.techpolis.studservice.screens.main.my_profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import dev.techpolis.studservice.R
import dev.techpolis.studservice.screens.common.mvp.MvpViewObservableBase
import dev.techpolis.studservice.screens.main.profile.ProfileMvpView

class MyProfileMvpViewImpl(
    layoutInflater: LayoutInflater,
    parent: ViewGroup?
) : MvpViewObservableBase<MyProfileMvpView.Listener>(), MyProfileMvpView {
    override var rootView: View =
        layoutInflater.inflate(R.layout.fragment_main__my_profile, parent, false)

    private val ibLogout: AppCompatImageButton = findViewById(R.id.fragment_main__my_profile__logout_btn)

    init {
        ibLogout.setOnClickListener { listeners.forEach { it.onLogoutBtnClicked() } }
    }
}