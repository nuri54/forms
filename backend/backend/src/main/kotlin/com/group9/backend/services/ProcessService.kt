package com.group9.backend.services

import com.group9.backend.models.Process
import com.group9.backend.repositories.ProcessRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@Service
class ProcessService(private val processRepository: ProcessRepository) {

    // Alle Processes
    fun getAllProcesses(): List<Process> = processRepository.findAll()

    // Alle vorlagen
    fun getAllTemplates(): List<Process> {
        val allProcesses = getAllProcesses()

        val processes = mutableListOf<Process>()
        for (process in allProcesses) {
            if (process.metaData.template)
                processes.add(process)
        }
        return processes
    }

    // Process via process id
    fun getProcessById(id: String): Optional<Process> = processRepository.findById(id)

    // Vorlage via formbuilder erstellen
    fun createTemplate(process: Process) {
        process.metaData.template = true
        process.metaData.done = false
        process.metaData.currentStep = 0

        val currentDateTime = LocalDateTime.now()
        process.metaData.updatedAt = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant())
        processRepository.save(process)
    }

    // Process an die nächste Person schicken
    // currentUser in meta data muss gesetzt werden (nächste person, die ausfüllen muss)
    fun sendProcess(process: Process) {
        if (process.metaData.template)
            process.metaData.template = false

        val currentDateTime = LocalDateTime.now()
        process.metaData.updatedAt = Date.from(currentDateTime.atZone(ZoneId.systemDefault()).toInstant())

        process.metaData.currentStep += 1
        if (process.metaData.currentStep == process.steps.size) {
            process.metaData.currentUser = null
            process.metaData.done = true
        }

        processRepository.save(process)
    }

    // Alle Processes die der User angefangen hat
    fun getUserProcesses(id: String): List<Process> {
        val allProcesses = getAllProcesses()

        val processes = mutableListOf<Process>()
        for (process in allProcesses) {
            if (process.metaData.owner?.id.equals(id))
                processes.add(process)
        }

        return processes
    }

    // Fertige Processes für archiv des users
    fun getUserDone(id: String): List<Process> {
        val allProcesses = getUserProcesses(id)

        val processes = mutableListOf<Process>()
        for (process in allProcesses) {
            if (process.metaData.done)
                processes.add(process)
        }

        return processes
    }

    // Prozesse wo auf den nächsten user gewartet wird
    fun getUserWaiting(id: String): List<Process> {
        val allProcesses = getUserProcesses(id)

        val processes = mutableListOf<Process>()
        for (process in allProcesses) {
            if (!process.metaData.done)
                processes.add(process)
        }

        return processes
    }

    // Processes finden, die der user mit der angegebenen id ausfüllen muss
    // Via currentStep den Step anzeigen, den er ausfüllen muss
    fun getUserTodos(id: String): List<Process> {
        val allProcesses = getAllProcesses()

        val processes = mutableListOf<Process>()
        for (process in allProcesses) {
            if (process.metaData.currentUser?.id.equals(id))
                processes.add(process)
        }

        return processes
    }
}
