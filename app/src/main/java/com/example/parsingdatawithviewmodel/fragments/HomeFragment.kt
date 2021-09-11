package com.example.parsingdatawithviewmodel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parsingdatawithviewmodel.adapters.VacancyAdapter
import com.example.parsingdatawithviewmodel.databinding.FragmentHomeBinding
import com.example.parsingdatawithviewmodel.viewmodels.VacancyViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VacancyViewModel by viewModels()
    private val adapter = VacancyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.adapter = adapter

        fetchVacancies()

        binding.refreshLayout.setOnRefreshListener {
            fetchVacancies()
        }

        return binding.root
    }

    private fun fetchVacancies() {
        binding.refreshLayout.isRefreshing = true

        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            binding.refreshLayout.isRefreshing = false
        })
    }


}