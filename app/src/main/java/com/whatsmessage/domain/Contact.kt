package com.whatsmessage.domain

import java.io.Serializable

data class Contact(val name: String, val number: String, var id: Int? = null): Serializable