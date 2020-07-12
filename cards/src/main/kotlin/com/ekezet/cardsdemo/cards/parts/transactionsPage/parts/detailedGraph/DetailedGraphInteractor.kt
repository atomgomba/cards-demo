package com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph

import androidx.lifecycle.LifecycleOwner
import com.ekezet.base.BaseInteractor
import com.ekezet.base.di.ActivityScope
import com.ekezet.cardsdemo.cards.di.SelectedCard
import com.ekezet.cardsdemo.cards.parts.transactionsPage.parts.detailedGraph.DetailedGraphSpec.Interactor
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

/**
 * @author kiri
 */
@ActivityScope
class DetailedGraphInteractor @Inject constructor(
    private val selectedCard: SelectedCard
) : BaseInteractor<Interactor.Presenter>(), Interactor {
    override val coroutineContext = Dispatchers.Main

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        launch {
            selectedCard.consumeEach { card ->
                presenter.onSelectedCardChanged(card)
            }
        }
    }
}
