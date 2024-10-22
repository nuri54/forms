package com.group9.backend.controllers

import com.group9.backend.`interface`.AccountControllerInterface
import com.group9.backend.models.Account
import com.group9.backend.services.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/v1/accounts")
class AccountController(@Autowired val accountService: AccountService) : AccountControllerInterface {
    @Override
    override fun getAccounts(): List<Account>? {
        return accountService.getAllAccounts()
    }

    @Override
    override fun getAccountById(@PathVariable id: String): ResponseEntity<Optional<Account>> {
        val response =  accountService.getAccountById(id)
        if(!response.isPresent){
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        else{
            return ResponseEntity<Optional<Account>>(response, HttpStatus.OK)
        }
    }

    @Override
    override fun createAccount(@RequestBody account: Account): ResponseEntity<String> {
        accountService.createAccount(account)

        return ResponseEntity<String>("Account created", HttpStatus.CREATED)
    }
}