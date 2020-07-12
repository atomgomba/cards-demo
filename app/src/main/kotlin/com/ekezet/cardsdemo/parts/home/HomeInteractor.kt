package com.ekezet.cardsdemo.parts.home

import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BaseInteractor
import com.ekezet.base.api.ApiResponse.FailureResponse
import com.ekezet.base.api.ApiResponse.LoadingResponse
import com.ekezet.base.api.ApiResponse.SuccessResponse
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.data.CardsRepository
import com.ekezet.cardsdemo.cards.di.SelectedCard
import com.ekezet.cardsdemo.navigation.di.NavigationChannel
import com.ekezet.cardsdemo.parts.home.HomeSpec.Interactor
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

/**
 * @author kiri
 */
@ActivityScope
class HomeInteractor @Inject constructor(
    private val cardsRepository: CardsRepository,
    private val selectedCard: SelectedCard,
    private val navigationChannel: NavigationChannel
) : BaseInteractor<Interactor.Presenter>(), Interactor {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        launch(Dispatchers.Main) {
            cardsRepository.cardsChannel.consumeEach {
                when (it) {
                    is LoadingResponse -> presenter.onLoadingStarted()
                    is SuccessResponse -> onSuccess(it.data)
                    is FailureResponse -> presenter.onLoadingError(it.throwable)
                }
            }
        }

        launch(Dispatchers.Main) {
            navigationChannel.consumeEach {
                presenter.onNavigationItemSelected(it)
            }
        }
    }

    override fun updateCardsList() = launch {
        cardsRepository.getCards()
    }

    private suspend fun onSuccess(items: List<Card>) {
        presenter.onLoadingSuccessful(items)
        if (items.isNotEmpty()) {
            selectedCard.send(items.first())
        }
    }
}
