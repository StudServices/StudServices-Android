package dev.techpolis.studservice.screens.main.filters

import android.widget.RadioGroup
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import dev.techpolis.studservice.screens.common.mvp.MvpPresenter
import dev.techpolis.studservice.screens.common.nav.BackPressDispatcher
import dev.techpolis.studservice.screens.common.nav.app.AppScreenRouter
import dev.techpolis.studservice.screens.main.userservices.newservice.ServiceTypeEnum

class FiltersPresenter(
    private val appScreenRouter: AppScreenRouter,
    private val backPressDispatcher: BackPressDispatcher,
): MvpPresenter<FiltersMvpView>, FiltersMvpView.Listener {
    private lateinit var view: FiltersMvpView
    private lateinit var geography: String
    private lateinit var serviceType: ServiceTypeEnum
    private var chipSet = mutableSetOf<String>()

    override fun bindView(view: FiltersMvpView) {
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

    override fun onFilterBtnClicked() {
        appScreenRouter.navigateUp()
    }

    override fun onBackPressed(): Boolean {
        return false
    }
}
