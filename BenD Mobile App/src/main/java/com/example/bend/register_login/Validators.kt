package com.example.bend.register_login

import android.net.Uri
import java.time.LocalDate
import java.time.LocalTime

object RegisterLoginValidator {
    fun validateFirstName(first_name:String):ValidationResult{
        return ValidationResult(
            (!first_name.isNullOrEmpty() && first_name.length >= 6)
        )
    }
    fun validateLastName(last_name:String):ValidationResult{
        return ValidationResult(
            (!last_name.isNullOrEmpty() && last_name.length >= 6)
        )
    }
    fun validateUsername(username:String):ValidationResult{
        return ValidationResult(
            (!username.isNullOrEmpty() && username.length >= 6)
        )
    }
    fun validatePassword(password:String):ValidationResult{
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 6)
        )
    }
    fun validateEmail(email:String):ValidationResult{
        return ValidationResult(
            (!email.isNullOrEmpty() && email.length >= 6)
        )
    }
    fun validateStageName(stage_name:String):ValidationResult{
        return ValidationResult(
            (!stage_name.isNullOrEmpty() && stage_name.length >= 6)
        )
    }

    fun validatePhone(phone:String):ValidationResult{
        return ValidationResult(
            (!phone.isNullOrEmpty() && phone.length >= 6)
        )
    }

}

object CreateEventValidator {
    fun validatePoster(uri: Uri): ValidationResult {
        return ValidationResult(status = true)
    }

    fun validateLocation(location: String): ValidationResult {
        return if (location.isNotEmpty()) {
            ValidationResult(status = true)
        } else {
            ValidationResult()
        }
    }

    fun validateEntranceFee(entranceFee: Int): ValidationResult {
        return if (entranceFee >= 0) {
            ValidationResult(status = true)
        } else {
            ValidationResult()
        }
    }

    fun validateStartDate(startDate: LocalDate): ValidationResult {
        return ValidationResult(status = true)
    }

    fun validateEndDate(endDate: LocalDate): ValidationResult {
        return ValidationResult(status = true)
    }

    fun validateStartTime(startTime: LocalTime): ValidationResult {
        return ValidationResult(status = true)
    }

    fun validateEndTime(endTime: LocalTime): ValidationResult {
        return ValidationResult(status = true)
    }

    fun validateArtists(artistsUsernames: List<String>): ValidationResult {
        return if (artistsUsernames.isNotEmpty()) {
            ValidationResult(status = true)
        } else {
            ValidationResult()
        }
    }
}

data class ValidationResult(
    val status: Boolean = false
)