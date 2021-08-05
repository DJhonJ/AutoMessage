package com.djhonj.automessage.framework.ui.contact

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.djhonj.automessage.R
import com.djhonj.automessage.domain.Contact
import com.djhonj.automessage.framework.ui.programming.ProgrammingActivity

class ContactsAdapter (private val contacts: List<Contact>, private val activity: IContactView
): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_contact, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contacts[position]

        holder.itemView.setOnClickListener {
            holder.click(activity, contact.number)
        }

        holder.bind(contact)
    }

    override fun getItemCount(): Int = contacts.size

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        //private val tvName = view.findViewById<TextView>(R.id.tvName)
        //private val imgContact = view.findViewById<ImageView>(R.id.imgContact)

        fun bind(contact: Contact) {
            view.findViewById<TextView>(R.id.tvName).setText("${contact.name}")
            //imgContact.setImageURI()
        }

        fun click(activity: IContactView, number: String) {
            activity.onClickItem(number)

//            view.context.startActivity(Intent(view.context, ProgrammingActivity::class.java).apply {
//                putExtra("number", number)
//            })
        }
    }
}