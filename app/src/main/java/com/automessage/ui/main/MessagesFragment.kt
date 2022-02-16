package com.automessage.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.automessage.R
import com.automessage.databinding.ActivityMainBinding

class MessagesFragment(private val adapter: MessagesAdapter) : Fragment() {
    private var _binding: ActivityMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.messages_fragment, container, false)

        root.findViewById<RecyclerView>(R.id.recyclerMessage)?.let {
            it.adapter = adapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}