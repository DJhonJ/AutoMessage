package com.whatsmessage.framework.providers

import android.content.Context
import android.provider.ContactsContract
import com.whatsmessage.data.datasource.ILocalContacts
import com.whatsmessage.domain.Contact
import kotlinx.coroutines.*

class PhoneContentProvider(private val context: Context): ILocalContacts {
     override suspend fun getContacts(): List<Contact> {
         val displayName: String = ContactsContract.Data.DISPLAY_NAME

        val projection = arrayOf(displayName, ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER)
        val selectionClause = "${ContactsContract.CommonDataKinds.Phone.NUMBER} is not null and ${ContactsContract.Data.MIMETYPE} = ? and ${ContactsContract.RawContacts.ACCOUNT_TYPE} = ?"
        val selectionArgs = arrayOf(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, "com.whatsapp")
        val sortOrders = "$displayName ASC"

         val cursor = context.contentResolver.query(
             ContactsContract.Data.CONTENT_URI,
             projection,
             selectionClause,
             selectionArgs,
             sortOrders
         )

         return withContext(Dispatchers.IO) {
             var contacts: MutableList<Contact> = mutableListOf()

             cursor?.apply {
                 while (this.moveToNext()) {
                     contacts.add(
                         Contact(
                             this.getString(0),
                             this.getString(1).replace(Regex("""\D"""), "")
                             //this.getString(2) ?: "null"
                         )
                     )
                 }
             }

             contacts
         }
    }
}