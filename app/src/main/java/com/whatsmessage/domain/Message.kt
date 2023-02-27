package com.whatsmessage.domain

import java.io.Serializable

//data class Message(val dateTimeMillisecond: Long, val dateTimeInfo: DateTime, var phone: String?, val message: String, val state: Int): Serializable
/**
 * contacts: son los nombres de las personas
 * phones: son sus telefonos
 * **/
data class Message(
    val id: Int?,
    val phone: String,
    val date: String,
    val dateShowUser: String,
    val time: String,
    val currentDate: String,
    val message: String,
    var contacts: List<Contact>,

    val state: Int): Serializable