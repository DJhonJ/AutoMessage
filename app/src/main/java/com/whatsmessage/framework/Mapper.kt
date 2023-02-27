package com.whatsmessage.framework

import android.util.Log
import com.whatsmessage.domain.Contact
import com.whatsmessage.domain.Message
import com.whatsmessage.framework.database.MessageEntity
import kotlinx.coroutines.NonCancellable.join

fun Message.toMessageEntity(): MessageEntity {
    this.contacts

    return MessageEntity(0,
        phone,
        date,
        dateShowUser,
        time,
        currentDate,
        this.contacts.joinToString(",") { c -> "${c.name}:${c.phone}:${c.type}" },
        //phones = contacts.joinToString(",") { c -> c.phone },
        message,
        state
    )
}

fun MessageEntity.toMessage(): Message {
    //los contactos se guardaban por separado como unos phones y names
    //val names = mutableListOf<Contact>()
    //for ((index: Int, name: String) in contacts.split(',').withIndex()) {
        //names.add(Contact(name, "", index))
    //}

    //val numbers = mutableListOf<Contact>()
    //for ((index: Int, number: String) in phones.split(',').withIndex()) {
//        numbers.add(Contact("", number, index))
    //}

    //val contactList: List<Contact> = join(names, numbers) { c, n -> c.id == n.id }.map { Contact(it.A.name, it.B.phone)  }
    val contactList: List<Contact> = contacts.split(",").map { val c = it.split(":"); Contact(c[0], c[1], c[2])  }

    return Message(id, phone, date, dateShowUser, time, currentDate, message, contactList, state)
}