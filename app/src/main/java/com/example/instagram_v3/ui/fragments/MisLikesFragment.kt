package com.example.instagram_v3.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram_v3.R
import com.example.instagram_v3.ui.adapters.PersonaAdapter
import com.example.instagram_v3.ui.viewmodels.PersonasViewModel

class MisLikesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PersonaAdapter
    //private val viewModel: PersonasViewModel by viewModels()
    private val viewModel: PersonasViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mis_likes, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = PersonaAdapter()
        recyclerView.adapter = adapter

        viewModel.misLikes.observe(viewLifecycleOwner) { likes ->
            adapter.submitList(likes)
        }
        return view
    }
}
