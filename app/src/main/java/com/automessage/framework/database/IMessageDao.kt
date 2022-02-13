package com.automessage.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface IMessageDao {
    @Query("select * from Message where State = :state")
    fun getListMessage(state: Int): List<MessageEntity>

    @Query("select * from Message where id = :id")
    fun getByIdMessage(id: Int): MessageEntity

    @Insert
    fun insertMessage(message: MessageEntity)

    @Update()
    fun updateMessage(message: MessageEntity)
}