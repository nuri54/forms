package com.group9.backend.`interface`

import com.group9.backend.models.Process
import org.jetbrains.annotations.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

interface ProcessControllerInterface {

    @GetMapping
    fun getProcesses(): List<Process>?

    @GetMapping("/{id}")
    fun getProcessById(@PathVariable @NotNull("ID is required") id: String): ResponseEntity<Optional<Process>>

    @GetMapping("/templates")
    fun getAllTemplates(): List<Process>?

    @PostMapping("/send")
    fun sendProcess(@RequestBody process: Process): ResponseEntity<String>

    @PostMapping
    fun createTemplate(@RequestBody process: Process): ResponseEntity<String>

    @GetMapping("/user/{id}")
    fun getUserProcesses(@PathVariable @NotNull("ID is required") id: String): List<Process>?

    @GetMapping("/user/done/{id}")
    fun getUserDone(@PathVariable @NotNull("ID is required") id: String): List<Process>?

    @GetMapping("/user/todo/{id}")
    fun getUserTodos(@PathVariable @NotNull("ID is required") id: String): List<Process>?

    @GetMapping("/user/waiting/{id}")
    fun getUserWaiting(@PathVariable @NotNull("ID is required") id: String): List<Process>?
}