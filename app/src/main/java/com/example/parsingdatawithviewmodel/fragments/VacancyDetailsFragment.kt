package com.example.parsingdatawithviewmodel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.parsingdatawithviewmodel.databinding.FragmentVacancyDetailsBinding
import com.example.parsingdatawithviewmodel.viewmodels.VacancyViewModel
import com.squareup.picasso.Picasso

class VacancyDetailsFragment : Fragment() {

    private lateinit var binding: FragmentVacancyDetailsBinding
    private val viewModel by lazy { ViewModelProvider(this).get(VacancyViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentVacancyDetailsBinding.inflate(layoutInflater)

        binding.progressLayout.visibility = View.VISIBLE

        val args = VacancyDetailsFragmentArgs.fromBundle(requireArguments())
        val item = args.vacancy

        binding.companyTextView.text = item.company
        binding.positionTextView.text = item.position
        binding.employmentTypeTextView.text = item.employmentType
        binding.salaryTextView.text = item.salary
        binding.stackTextView.text = item.stack
        Picasso.get().load(item.image).into(binding.logo)

        viewModel.fetchVacancy(item.url).observe(viewLifecycleOwner, Observer {
            binding.detailTextView.text = it.detail
            binding.progressLayout.visibility = View.GONE
        })

        return binding.root
    }

}