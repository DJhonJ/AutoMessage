package com.whatsmessage.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.whatsmessage.R
import com.whatsmessage.domain.Message
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class MessagesAdapter(private  val messages: List<Message>): RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_message, parent, false)

        return MessageViewHolder(view)
    }

    //pasamos vista al view holder
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        if (itemCount > 0) {
            holder.bind(messages[position])
        }
    }

    override fun getItemCount(): Int = messages.size

    inner class MessageViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(message: Message) {

            view.findViewById<TextView>(R.id.tvDateContact)?.apply {
                text = "${message.date} "
            }

            view.findViewById<TextView>(R.id.tvTimeContact)?.apply {
                text = message.time
            }

            view.findViewById<ChipGroup>(R.id.cardChipGroup)?.apply {
                this.removeAllViews()

                for (contact in message.contacts) {
                    if (contact.number.isNotEmpty()) {
                        val chip: Chip =  Chip(context).apply {
                            text = "${contact.name}"
                            setChipIconResource(R.drawable.ic_baseline_account_circle_24)
                            isCheckable = false
                            isCloseIconVisible = false
                        }

                        this.addView(chip)
                    }
                }
            }
        }
    }
}