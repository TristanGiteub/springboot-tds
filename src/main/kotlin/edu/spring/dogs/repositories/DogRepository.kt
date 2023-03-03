package edu.spring.dogs.repositories

import edu.spring.dogs.entities.Dog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DogRepository : JpaRepository<Dog, Long> {
    fun findByMasterIsNull(): List<Dog>
    fun findByNameAndMasterId(name: String, masterId: Long?): Dog?
}

