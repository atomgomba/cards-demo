package com.ekezet.base.api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BroadcastChannel
import timber.log.Timber

/**
 * @author kiri
 */
fun <T> CoroutineScope.callOnChannel(
    channel: BroadcastChannel<ApiResponse<T>>,
    call: suspend () -> T
) = async(coroutineContext) {
    channel.send(ApiResponse.loading())
    try {
        val result = call()
        channel.send(ApiResponse.success(result))
        return@async result
    } catch (t: Throwable) {
        Timber.w(t)
        channel.send(ApiResponse.failure(t))
        return@async null
    }
}
