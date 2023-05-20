package org.d3if3115.aplikasikalkulatorkalori.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface KaloriDao {
    @Insert
    fun insert(kaloriEntity: KaloriEntity)
    @Query("SELECT * FROM kalori ORDER BY id DESC")
    fun getLastKalori(): LiveData<List<KaloriEntity>>

    @Query("DELETE FROM Kalori")
    fun clearData()
}