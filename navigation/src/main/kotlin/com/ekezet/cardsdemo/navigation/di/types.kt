package com.ekezet.cardsdemo.navigation.di

import kotlinx.coroutines.channels.ConflatedBroadcastChannel

/**
 * @author kiri
 */
typealias NavigationChannel = ConflatedBroadcastChannel<Int>
