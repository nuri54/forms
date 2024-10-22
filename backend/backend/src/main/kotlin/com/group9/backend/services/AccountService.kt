package com.group9.backend.services

import com.group9.backend.models.Account
import com.group9.backend.repositories.AccountRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountService(private val accountRepository: AccountRepository) {

    // Alle Accounts
    fun getAllAccounts(): List<Account> = accountRepository.findAll()

    // Account via id
    fun getAccountById(id: String): Optional<Account> = accountRepository.findById(id)

    // Account erstellen
    fun createAccount(account: Account) {
        accountRepository.save(account)
    }
}