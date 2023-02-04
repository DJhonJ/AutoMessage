package com.whatsmessage.domain

import java.io.Serializable

//data class Message(val dateTimeMillisecond: Long, val dateTimeInfo: DateTime, var phone: String?, val message: String, val state: Int): Serializable
/**
 * contacts: son los nombres de las personas
 * phones: son sus telefonos
 * **/
data class Message(val id_message: Int, val date: String, val time: String, val dateTimeMillisecond: Long, var contacts: List<Contact>, val message: String,
                   val state: Int): Serializable