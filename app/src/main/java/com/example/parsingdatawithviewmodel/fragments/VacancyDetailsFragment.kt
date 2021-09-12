package com.example.parsingdatawithviewmodel.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.parsingdatawithviewmodel.databinding.FragmentVacancyDetailsBinding
import com.example.parsingdatawithviewmodel.viewmodels.VacancyViewModel
import com.squareup.picasso.Picasso

class VacancyDetailsFragment : Fragment() {

    private var _binding: FragmentVacancyDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: VacancyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVacancyDetailsBinding.inflate(inflater, container, false)

        binding.progressLayout.visibility = View.VISIBLE

        val args = VacancyDetailsFragmentArgs.fromBundle(requireArguments())
        val item = args.vacancy

        binding.companyTextView.text = item.company
        binding.dateTextView.text = item.date
        binding.positionTextView.text = item.position
        binding.employmentTypeTextView.text = item.employmentType
        binding.salaryTextView.text = item.salary
        binding.stackTextView.text = item.stack
        binding.linkTextView.text = item.url
        Picasso.get().load(item.image).into(binding.logo)

        if (binding.salaryTextView.text == "") binding.salaryTextView.visibility = View.GONE

        binding.linkTextView.setOnClickListener {
            openLink(item.url)
        }

        viewModel.fetchVacancy(item.url).observe(viewLifecycleOwner, Observer {
            binding.detailTextView.text = it.detail
            binding.progressLayout.visibility = View.GONE
        })

        return binding.root
    }

    private fun openLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)
        startActivity(intent)
    }


}