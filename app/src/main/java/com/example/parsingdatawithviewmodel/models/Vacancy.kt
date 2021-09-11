package com.example.parsingdatawithviewmodel.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vacancy(
    var id: Int = 0,
    var company: String = "",
    var position: String = "",
    var employmentType: String = "",
    var salary: String = "",
    var stack: String = "",
    var image: String = "",
    var url: String = ""
) : Parcelable

