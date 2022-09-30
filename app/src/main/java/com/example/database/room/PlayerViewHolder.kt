package com.example.database.room

import androidx.recyclerview.widget.RecyclerView
import com.example.database.databinding.ItemPlayerBinding

class PlayerViewHolder(
    private val binding: ItemPlayerBinding,
    private val onLongPlayerClicked: (Player) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Player) {
        with(binding) {

            playerFullInfo.text =
                "Player - ${item.id}: ${item.firstName} ${item.lastName}, ${item.nationality}"
            root.setOnLongClickListener {
                onLongPlayerClicked(item)
                true
            }
        }
    }
}