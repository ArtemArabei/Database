package com.example.database.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PlayerViewModel(
    private val playerDao: PlayerDao,
) : ViewModel() {

    val liveData: LiveData<List<Player>> = playerDao.observePlayers()

    fun onButtonClicked(firstName: String, lastName: String, nationality: String) {
        val newPlayer =
            Player(firstName = firstName, lastName = lastName, nationality = nationality)
        playerDao.insertAll(newPlayer)
    }
}