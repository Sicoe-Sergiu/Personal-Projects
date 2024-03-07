package com.example.bend.ui_state

import android.net.Uri
import java.time.LocalDate
import java.time.LocalTime

data class CreateEventUiState(

    var posterUri: Uri? = null,
    var entranceFee: Int = -1,
    var location: String = "",
    var startDate: LocalDate? = null,
    var endDate: LocalDate? = null,
    var startTime: LocalTime? = null,
    var endTime: LocalTime? = null,
    var artistsStageNames : List<String> = emptyList(),

//    errors\
    var posterError: Boolean = true,
    var entranceFeeError: Boolean = true,
    var locationError: Boolean = true,
    var startDateError: Boolean = true,
    var endDateError: Boolean = true,
    var startTimeError: Boolean = true,
    var endTimeError: Boolean = true,
    var artistsUsernamesError: Boolean = true,
)