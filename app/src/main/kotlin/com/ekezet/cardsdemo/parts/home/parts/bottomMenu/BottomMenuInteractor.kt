package com.ekezet.cardsdemo.parts.home.parts.bottomMenu

import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BaseInteractor
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.navigation.di.NavigationChannel
import com.ekezet.cardsdemo.parts.home.parts.bottomMenu.BottomMenuSpec.Interactor
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

/**
 * @author kiri
 */
@ActivityScope
class BottomMenuInteractor @Inject constructor(
    private val navigationChannel: NavigationChannel
) : BaseInteractor<Interactor.Presenter>(), Interactor {
    override val coroutineContext = Dispatchers.Main

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        launch {
            navigationChannel.consumeEach { menuId ->
                presenter.onNavigationEvent(menuId)
            }
        }
    }

    override fun emitNavigationEvent(menuItemId: Int) {
        launch {
            navigationChannel.send(menuItemId)
        }
    }
}
