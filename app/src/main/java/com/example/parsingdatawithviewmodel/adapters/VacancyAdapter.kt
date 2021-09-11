package com.example.parsingdatawithviewmodel.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.parsingdatawithviewmodel.BR
import com.example.parsingdatawithviewmodel.databinding.VacancyBinding
import com.example.parsingdatawithviewmodel.fragments.HomeFragmentDirections
import com.example.parsingdatawithviewmodel.models.Vacancy
import com.squareup.picasso.Picasso

class VacancyAdapter(private val context: Context): RecyclerView.Adapter<VacancyAdapter.ViewHolder>() {

    private var vacancies = mutableListOf<Vacancy>()
    fun setListData(data: MutableList<Vacancy>) {
        vacancies = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacancyAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = VacancyBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VacancyAdapter.ViewHolder, position: Int) {
        holder.bind(vacancies[position])
    }

    override fun getItemCount(): Int {
        return vacancies.size
    }

    inner class ViewHolder(private val binding: VacancyBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(vacancy: Vacancy) = with(itemView) {

            if (vacancy.salary == "") {
                binding.salaryTextView.visibility = View.GONE
            } else
                binding.salaryTextView.visibility = View.VISIBLE

            binding.setVariable(BR.vacancy, vacancy)
            binding.executePendingBindings()

            Picasso.get().load(vacancy.image).into(binding.logo)

            itemView.setOnClickListener{
                val action = HomeFragmentDirections.actionNavigationHomeToVacancyDetailsFragment(vacancy)
                findNavController().navigate(action)
            }
        }
    }
}