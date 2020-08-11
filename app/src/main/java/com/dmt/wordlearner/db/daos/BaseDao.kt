package com.dmt.wordlearner.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

@Dao
interface BaseDao<T> {

    @Insert
    fun insert(vararg item: T) : List<Long>

    @Update
    fun update(vararg item: T) : Int

    @Delete
    fun delete(vararg item: T) : Int
}