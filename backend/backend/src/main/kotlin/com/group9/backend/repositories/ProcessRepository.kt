package com.group9.backend.repositories

import com.group9.backend.models.Process
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProcessRepository : MongoRepository<Process, String>