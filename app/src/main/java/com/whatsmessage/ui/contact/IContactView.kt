package com.whatsmessage.ui.contact

import com.whatsmessage.domain.Contact

interface IContactView {
    fun onClickItem(contact: Contact)
}