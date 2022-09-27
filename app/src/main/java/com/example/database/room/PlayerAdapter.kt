package com.example.database.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.database.databinding.ItemPlayerBinding

class PlayerAdapter(
    private val onLongPlayerClicked: (Player) -> Unit,
) : ListAdapter<Player, PlayerViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemPlayerBinding.inflate(layoutInflater, parent, false)
        return PlayerViewHolder(binding, onLongPlayerClicked = onLongPlayerClicked)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Player>() {
            override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
                return oldItem == newItem
            }
        }
    }
}