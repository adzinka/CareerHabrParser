package com.example.parsingdatawithviewmodel.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parsingdatawithviewmodel.models.Vacancy
import com.example.parsingdatawithviewmodel.models.VacancyDetail
import com.example.parsingdatawithviewmodel.repository.Repo
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class VacancyViewModel: ViewModel() {

    var vacancies: MutableLiveData<MutableList<Vacancy>> = MutableLiveData()

    fun init (context: Context) {
        if (vacancies.value != null)
            return
    }

    private val repo = Repo()

    fun fetchData(): MutableLiveData<MutableList<Vacancy>> {
        // load data async
        viewModelScope.launch(IO) {
            // for async load need use "postValue", else "setValue"
            vacancies.postValue(repo.getVacanciesList())
        }
        return vacancies
    }

    fun fetchVacancy(url: String): MutableLiveData<VacancyDetail> {
        val item = MutableLiveData<VacancyDetail>()
        viewModelScope.launch(IO) {
            item.postValue(repo.getVacancy(url))
        }
        return item
    }
}