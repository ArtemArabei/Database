package com.example.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    fun getAll(): List<Player>

    @Query("SELECT * FROM player WHERE id IN (:playerIds)")
    fun loadAllByIds(playerIds: IntArray): List<Player>

    @Query("SELECT * FROM player WHERE first_name LIKE :firstName AND last_name LIKE :lastName LIMIT 1")
    fun findByFullName(firstName: String, lastName: String): Player

    @Insert
    fun insertAll(vararg players: Player)

    @Delete
    fun delete(player: Player)

    @Query("SELECT * FROM player")
    fun observePlayers(): LiveData<List<Player>>
}