package com.ekezet.cardsdemo.navigation.viewmodels

import androidx.annotation.IdRes

/**
 * @author kiri
 */
data class NavigationItem(
    @IdRes val menuId: Int,
    val part: Any
)
