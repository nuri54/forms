package com.group9.backend.controllers

import com.group9.backend.`interface`.ProcessControllerInterface
import com.group9.backend.services.ProcessService
import com.group9.backend.models.Process
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/processes")
class ProcessController(@Autowired val processService: ProcessService) : ProcessControllerInterface {

    @Override
    override fun getProcesses(): List<Process>? {
        return processService.getAllProcesses()
    }

    @Override
    override fun getProcessById(@PathVariable id: String): ResponseEntity<Optional<Process>> {
        val response =  processService.getProcessById(id)
        if(!response.isPresent){
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        else{
            return ResponseEntity<Optional<Process>>(response, HttpStatus.OK)
        }
    }

    @Override
    override fun getAllTemplates(): List<Process>? {
        return processService.getAllTemplates()
    }

    @Override
    override fun sendProcess(@RequestBody process: Process): ResponseEntity<String> {
        processService.sendProcess(process)

        return ResponseEntity<String>("Process updated and sent", HttpStatus.ACCEPTED)
    }

    @Override
    override fun createTemplate(@RequestBody process: Process): ResponseEntity<String> {
        processService.createTemplate(process)

        return ResponseEntity<String>("Template created", HttpStatus.CREATED)
    }

    @Override
    override fun getUserProcesses(@PathVariable id: String): List<Process>? {
        return processService.getUserProcesses(id)
    }

    @Override
    override fun getUserDone(@PathVariable id: String): List<Process>? {
        return processService.getUserDone(id)
    }

    @Override
    override fun getUserTodos(@PathVariable id: String): List<Process>? {
        return processService.getUserTodos(id)
    }

    @Override
    override fun getUserWaiting(@PathVariable id: String): List<Process>? {
        return processService.getUserWaiting(id)
    }
}