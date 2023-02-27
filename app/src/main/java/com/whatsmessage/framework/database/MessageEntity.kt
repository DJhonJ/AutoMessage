package com.whatsmessage.framework.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.whatsmessage.domain.Contact
import java.io.Serializable

@Entity(tableName = "Message")
data class MessageEntity (@PrimaryKey(autoGenerate = true) val id: Int,
                          val phone: String,
                          val date: String,
                          val dateShowUser: String,
                          val time: String,
                          val currentDate: String,
                          var contacts: String,
                          val message: String,
                          val state: Int)