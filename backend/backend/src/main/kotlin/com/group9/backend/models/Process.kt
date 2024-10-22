package com.group9.backend.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

data class Process(
    @Id
    val id: String = ObjectId.get().toString(),
    val metaData: MetaData,
    val steps: List<ProcessStep>
)
