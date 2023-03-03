package edu.spring.dogs.repositories

import edu.spring.dogs.entities.Toy
import org.springframework.data.jpa.repository.JpaRepository

interface ToyRepository : JpaRepository<Toy, Long> {
    fun findByType(type: String): List<Toy>
}
