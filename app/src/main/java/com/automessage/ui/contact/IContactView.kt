package com.automessage.ui.contact

import com.automessage.domain.Contact

interface IContactView {
    fun onClickItem(contact: Contact)
}