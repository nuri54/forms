package com.group9.backend.`interface`

import com.group9.backend.models.Account
import org.jetbrains.annotations.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

interface AccountControllerInterface {

    @GetMapping
    fun getAccounts(): List<Account>?

    @GetMapping("/{id}")
    fun getAccountById(@PathVariable @NotNull("ID is required") id: String): ResponseEntity<Optional<Account>>

    @PostMapping
    fun createAccount(@RequestBody account: Account): ResponseEntity<String>
}