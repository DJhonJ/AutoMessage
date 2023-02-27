package com.whatsmessage.domain

import java.io.Serializable

data class Contact(val name: String, val phone: String, val type: String, var id: Int? = null): Serializable