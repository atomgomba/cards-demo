package com.ekezet.cardsdemo.cards.di

import com.ekezet.cardsdemo.cards.data.Card
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

/**
 * @author kiri
 */
typealias SelectedCard = ConflatedBroadcastChannel<Card>
