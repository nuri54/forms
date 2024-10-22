package com.group9.backend.models

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ProcessInput(
    val name: String,
    val value: String?,
    val options: List<ProcessOption>?,
    val type: InputType
)