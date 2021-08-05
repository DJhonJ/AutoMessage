package com.automessage.framework.data

import android.content.Context
import android.database.Cursor
import android.provider.ContactsContract
import android.provider.Settings
import com.automessage.data.datasource.ILocalContacts
import com.automessage.domain.Contact
import kotlinx.coroutines.*

class PhoneContentProvider(private val context: Context): ILocalContacts {
     override suspend fun getContacts(): List<Contact> {
        val projection = arrayOf<String>(ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NORMALIZED_NUMBER,
            ContactsContract.CommonDataKinds.Photo.PHOTO)
        val selectionClause = "${ContactsContract.CommonDataKinds.Phone.NUMBER} is not null and ${ContactsContract.Data.MIMETYPE} = ? and ${ContactsContract.RawContacts.ACCOUNT_TYPE} = ?"
        val selectionArgs = arrayOf<String>(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, "com.whatsapp")
        val sortOrders = "${ContactsContract.Data.DISPLAY_NAME} ASC"

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
                             this.getString(0) ?: "null",
                             this.getString(1) ?: "null",
                             this.getString(2) ?: "null"
                         )
                     )
                 }
             }

             contacts
         }
    }
}