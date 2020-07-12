package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator

import com.ekezet.cardsdemo.cards.R
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorSpec.Interactor
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.balanceIndicator.BalanceIndicatorSpec.View
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * @author kiri
 */
@ExtendWith(MockKExtension::class)
class BalanceIndicatorPresenterTest {
    @MockK internal lateinit var view: View
    @MockK internal lateinit var interactor: Interactor

    private lateinit var subject: BalanceIndicatorPresenter

    @BeforeEach fun setUp() {
        subject = BalanceIndicatorPresenter()
        subject.onBoot(BalanceIndicatorPart(view, interactor, subject, null))
    }

    @Test fun `correct layout must be set`() {
        subject.onCreate(mockk())

        verify {
            view.setup(R.layout.view_balance_indicator)
        }
    }

    @Test fun `selected card with zero available balance`() {
        val card = mockk<Card> {
            every { availableBalance } returns 0F
            every { totalBalance } returns 1000F
        }

        subject.onSelectedCardChanged(card)

        verify {
            view.setBalanceText("0.00", R.color.error_red)
            view.setBalanceRatio(1F, isFastAnimation = true)
            view.toggleAlert(true)
        }
    }

    @Test fun `selected card with zero total balance`() {
        val card = mockk<Card> {
            every { availableBalance } returns 0F
            every { totalBalance } returns 0F
        }

        subject.onSelectedCardChanged(card)

        verify {
            view.setBalanceText("0.00", R.color.error_red)
            view.setBalanceRatio(1F, isFastAnimation = true)
            view.toggleAlert(true)
        }
    }

    @Test fun `selected card with available balance`() {
        val card = mockk<Card> {
            every { availableBalance } returns 400F
            every { totalBalance } returns 1000F
        }
        val ratio = 1F - card.availableBalance / card.totalBalance

        subject.onSelectedCardChanged(card)

        verify {
            view.setBalanceText("400.00", R.color.status_blue)
            view.setBalanceRatio(ratio, isFastAnimation = false)
            view.toggleAlert(false)
        }
    }
}
