package com.whatsmessage.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Message")
data class MessageEntity (@PrimaryKey(autoGenerate = true) val id: Int, val id_message: Int, val date: String, val time: String,
                          val dateTimeMillisecond: Long, val contacts: String, val phones: String, val message: String, val state: Int)