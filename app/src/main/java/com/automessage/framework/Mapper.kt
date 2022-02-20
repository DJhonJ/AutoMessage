package com.automessage.framework

import com.automessage.domain.Contact
import com.automessage.domain.Message
import com.automessage.framework.database.MessageEntity
import net.servicestack.func.Func.join

fun Message.toMessageEntity(): MessageEntity {
    return MessageEntity(0, id_message, date, time, dateTimeMillisecond,
        contacts = contacts.joinToString(",") { c -> c.name },
        phones = contacts.joinToString(",") { c -> c.number },
        message, state)
}

fun MessageEntity.toMessage(): Message {
    val names = mutableListOf<Contact>()
    for ((index: Int, name: String) in contacts.split(',').withIndex()) {
        names.add(Contact(name, "", index))
    }

    val numbers = mutableListOf<Contact>()
    for ((index: Int, number: String) in phones.split(',').withIndex()) {
        numbers.add(Contact("", number, index))
    }

    val contactList: List<Contact> = join(names, numbers) { c, n -> c.id == n.id }.map { Contact(it.A.name, it.B.number)  }

    return Message(id_message, date, time, dateTimeMillisecond, contactList, message, state)
}