package com.automessage.framework

import com.automessage.domain.Message
import com.automessage.framework.database.MessageEntity

fun Message.toMessageEntity(): MessageEntity {
    return MessageEntity(0, id_message, date, time, dateTimeMillisecond, contacts, phones, message, state)
}

fun MessageEntity.toMessage(): Message {
    return Message(id_message, date, time, dateTimeMillisecond, contacts, phones, message, state)
}