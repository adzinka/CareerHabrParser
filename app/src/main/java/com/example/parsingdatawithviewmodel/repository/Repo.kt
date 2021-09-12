package com.example.parsingdatawithviewmodel.repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.parsingdatawithviewmodel.models.Vacancy
import com.example.parsingdatawithviewmodel.models.VacancyDetail
import org.jsoup.Jsoup
import java.io.IOException

class Repo {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: Repo? = null
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
    }

    fun getInstance(context: Context): Repo {
        mContext = context
        if (instance == null)
            instance = Repo()

        return instance!!
    }

    fun getVacanciesList() : MutableList<Vacancy> {
        val listData = mutableListOf<Vacancy>()
        try {
            val url = "https://career.habr.com/vacancies?type=all"
            val doc = Jsoup.connect(url).get()
            val vacancies = doc.select("div.vacancy-card")

            for ((i, vacancy) in vacancies.withIndex()) {

                val company = vacancy.select("div.vacancy-card__company-title")
                    .text()

                val position = vacancy.select("a.vacancy-card__title-link")
                    .text()

                val employmentType = vacancy.select("div.vacancy-card__meta")
                    .text()

                val salary = vacancy.select("div.basic-salary")
                    .text()

                val stack = vacancy.select("div.vacancy-card__skills")
                    .text()

                val vacancyUrl = "https://career.habr.com" + vacancy.select("div.vacancy-card__title")
                    .select("a")
                    .attr("href")

                val date = vacancy.select("div.vacancy-card__date")
                    .text()

                val image = vacancy.select("img.vacancy-card__icon")
                    .attr("src")

                listData.add(Vacancy(i, company, position, employmentType, salary, stack, image, date, vacancyUrl))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return listData
    }

    fun getVacancy(url: String): VacancyDetail {
        val item = VacancyDetail()
        try {
            val document = Jsoup.connect(url).get()
            val detail = document.select("div.style-ugc")
                .select("p")
                .eq(1)
                .text()
           item.detail = detail
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return item
    }
}