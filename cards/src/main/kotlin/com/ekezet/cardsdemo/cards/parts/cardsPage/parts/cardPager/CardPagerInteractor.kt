package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager

import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BaseInteractor
import com.ekezet.base.api.ApiResponse.SuccessResponse
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.data.CardsRepository
import com.ekezet.cardsdemo.cards.di.SelectedCard
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.CardPagerSpec.Interactor
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

/**
 * @author kiri
 */
@ActivityScope
class CardPagerInteractor @Inject constructor(
    private val selectedCard: SelectedCard,
    private val cardsRepository: CardsRepository
) : BaseInteractor<Interactor.Presenter>(), Interactor {
    override val coroutineContext = Dispatchers.Main

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        launch {
            cardsRepository.cardsChannel.consumeEach {
                if (it is SuccessResponse) {
                    presenter.onCardsLoaded(it.data)
                }
            }
        }

        launch {
            selectedCard.consumeEach { card ->
                val index = cardsRepository.findIndex(card) ?: return@consumeEach
                presenter.onSelectedCardChanged(card, index, cardsRepository.numCards)
            }
        }
    }

    override fun selectCardByIndex(index: Int) = launch {
        val card = cardsRepository.findByIndex(index) ?: return@launch
        if (selectedCard.valueOrNull != card) {
            selectedCard.send(card)
        }
    }
}
