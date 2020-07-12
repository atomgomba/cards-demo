package com.ekezet.base.data.cache.layers

import com.ekezet.base.data.cache.BareCachedItem
import com.ekezet.base.data.cache.CachedItem
import com.ekezet.base.data.cache.ICacheLayer
import com.squareup.moshi.JsonAdapter
import java.io.File
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * @author kiri
 *
 * TODO: handle serialization problems, eg. changes in data structure
 */
class DiskCacheLayer<K, T>(
    private val cachePath: File,
    private val itemJsonAdapter: JsonAdapter<CachedItem<K, T>>,
    private val journalJsonAdapter: JsonAdapter<Map<String, BareCachedItem<K>>>
) : ICacheLayer<K, T>, CoroutineScope {
    override val cachedItems: List<BareCachedItem<K>>
        get() = journal.values.toList()

    override val coroutineContext = Dispatchers.IO

    private val journalFile = File(cachePath, JOURNAL_FILENAME)
    private val journal: MutableMap<String, BareCachedItem<K>> = HashMap()

    init {
        if (cachePath.mkdirs()) {
            Timber.d("Disk cache created at: %s", cachePath.absolutePath)
        } else {
            loadJournal()
        }
    }

    override suspend fun get(key: K): CachedItem<K, T>? {
        if (!journal.containsKey(key.toString())) {
            return null
        }
        val file = File(cachePath, key.toString())
        return if (file.exists()) {
            withContext(Dispatchers.IO) {
                itemJsonAdapter.fromJson(file.readText())
            }
        } else {
            null
        }
    }

    override suspend fun put(key: K, item: CachedItem<K, T>) {
        File(cachePath, key.toString()).writeText(itemJsonAdapter.toJson(item))
        journal[key.toString()] = item
    }

    override suspend fun remove(key: K) {
        File(cachePath, key.toString()).delete()
        journal.remove(key.toString())
    }

    override suspend fun removeAll() {
        cachePath.deleteRecursively()
        cachePath.mkdirs()
        journal.clear()
    }

    override suspend fun sync() {
        val json = journalJsonAdapter.toJson(journal)
        journalFile.writeText(json)
    }

    private fun loadJournal() {
        journal.clear()
        if (!journalFile.exists()) {
            return
        }
        val json = journalFile.readText()
        journal.putAll(journalJsonAdapter.fromJson(json) ?: HashMap())
    }

    companion object {
        const val JOURNAL_FILENAME = "journal.json"
    }
}
