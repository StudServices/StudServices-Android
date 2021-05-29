package dev.techpolis.studservice.screens.main.userservices.newservice

import android.widget.RadioGroup
import androidx.core.view.get
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter

class NewServicePresenter(
    private val appScreenRouter: AppScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
): MvpPresenter<NewServiceMvpView>, NewServiceMvpView.Listener {
    private lateinit var view: NewServiceMvpView
    private lateinit var title: String
    private lateinit var password: String
    private lateinit var price: String
    private lateinit var geography: String
    private lateinit var serviceType: ServiceTypeEnum
    private var chipSet = mutableSetOf<String>()

    override fun bindView(view: NewServiceMvpView) {
        this.view = view
    }

    override fun onStart() {
        view.registerListener(this)
        backPressDispatcher.registerListener(this)
    }

    override fun onStop() {
        view.unregisterListener(this)
        backPressDispatcher.unregisterListener(this)
    }

    override fun onDestroy() {
//        TODO("Not yet implemented")
    }

    override fun onCreateServiceBtnClicked() {
        appScreenRouter.navigateUp()
    }

    override fun onTitleFieldTextChanged(title: String) {
        this.title = title
    }

    override fun onPasswordFieldTextChanged(password: String) {
        this.password = password
    }

    override fun onPriceFieldTextChanged(price: String) {
        this.price = price
    }

    override fun onTagsCheckedChanged(chipGroup: ChipGroup, position: Int) {
        chipSet.clear()
        chipGroup.checkedChipIds
            .forEach {
                chipSet.add((chipGroup.getChildAt(it) as Chip)
                    .text
                    .toString())
            }
    }

    override fun onServiceTypeChanged(radioGroup: RadioGroup) {
        serviceType = if (radioGroup.checkedRadioButtonId == 0)
            ServiceTypeEnum.OFFER
        else
            ServiceTypeEnum.REQUEST
    }

    override fun onGeographyChanged(geography: String) {
        this.geography = geography
    }

    override fun onBackPressed(): Boolean {
//        TODO("Not yet implemented")
        return false
    }
}

enum class ServiceTypeEnum {
    OFFER, REQUEST
}
