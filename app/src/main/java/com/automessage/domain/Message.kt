package com.automessage.domain

import java.io.Serializable

//data class Message(val dateTimeMillisecond: Long, val dateTimeInfo: DateTime, var phone: String?, val message: String, val state: Int): Serializable
data class Message(val id_message: String, val date: String, val time: String, val dateTimeMillisecond: Long,
                   val contacts: String, var phones: String?, val message: String, val state: Int): Serializable