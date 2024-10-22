package com.group9.backend.models

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.Date

@JsonInclude(JsonInclude.Include.NON_NULL)
data class MetaData(
    val title: String,
    val owner: Account?,
    var currentUser: Account?,
    var done: Boolean,
    var currentStep: Int,
    var updatedAt: Date?,
    var template: Boolean
)