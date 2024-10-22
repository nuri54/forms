package com.group9.backend.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

data class Account(
    @Id
    val id: String = ObjectId.get().toString(),
    val name: String,
    val lastName: String,
    val email: String
)