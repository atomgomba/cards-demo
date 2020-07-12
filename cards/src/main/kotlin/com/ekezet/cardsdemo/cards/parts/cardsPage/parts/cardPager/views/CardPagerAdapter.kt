package com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ekezet.cardsdemo.cards.R
import com.ekezet.cardsdemo.cards.data.Card
import com.ekezet.cardsdemo.cards.parts.cardsPage.parts.cardPager.views.CardPagerAdapter.CardViewHolder
import dagger.Reusable
import javax.inject.Inject

/**
 * @author kiri
 */
@Reusable
class CardPagerAdapter @Inject constructor(
    private val items: List<Card>
) : RecyclerView.Adapter<CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CardViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.page_card_pager_item, parent, false)
        )

    override fun getItemCount() = items.size

    override fun getItemId(position: Int) = items[position].cardId.toLong()

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.cardImage.setImageResource(
            when (items[position].cardImage) {
                1 -> R.drawable.cccard1
                2 -> R.drawable.cccard2
                3 -> R.drawable.cccard3
                else -> 0 // TODO: use fallback image?
            }
        )
    }

    class CardViewHolder(view: View) : ViewHolder(view) {
        val cardImage: ImageView = view.findViewById(R.id.cardImage)
    }
}
