package com.whatsmessage.ui.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.whatsmessage.R
import com.whatsmessage.domain.Contact

class ContactsAdapter (private val contacts: List<Contact>, private val activity: IContactView
): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_contact, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (this.getItemCount() > 0) {
            val contact = contacts[position]

            holder.itemView.setOnClickListener {
                holder.click(activity, contact)
            }

            holder.bind(contact)
        }
    }

    override fun getItemCount(): Int = contacts.size

    class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        //private val tvName = view.findViewById<TextView>(R.id.tvName)
        //private val imgContact = view.findViewById<ImageView>(R.id.imgContact)

        fun bind(contact: Contact) {
            view.findViewById<TextView>(R.id.tvName).setText(contact.name)
            //imgContact.setImageURI()
        }

        fun click(activity: IContactView, contact: Contact) {
            activity.onClickItem(contact)

//            view.context.startActivity(Intent(view.context, ProgrammingActivity::class.java).apply {
//                putExtra("number", number)
//            })
        }
    }
}