package com.whatsmessage.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.whatsmessage.framework.database.MessageEntity

@Dao
interface IMessageDao {
    @Query("select * from Message where State = :state order by Id desc")
    fun getListMessage(state: Int): List<MessageEntity>

    @Query("select * from Message where id = :id")
    fun getByIdMessage(id: Int): MessageEntity

    @Insert
    fun insertMessage(message: MessageEntity)

    @Update()
    fun updateMessage(message: MessageEntity)
}