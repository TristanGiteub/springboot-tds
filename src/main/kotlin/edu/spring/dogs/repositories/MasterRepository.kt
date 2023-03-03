package edu.spring.dogs.repositories

import edu.spring.dogs.entities.Master
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MasterRepository : JpaRepository<Master, Long> {

    fun findByDogsName(name: String): List<Master>

    fun findByFirstnameAndLastname(firstname: String, lastname: String): List<Master>
    abstract fun deleteById(id: Long?)
}
