package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
class Master(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstname: String = "",
    var lastname: String = "",
    @OneToMany(mappedBy = "master", cascade = [CascadeType.ALL])
    var dogs: MutableSet<Dog> = mutableSetOf()
) {
    constructor() : this(null, "", "")
    constructor(firstname: String, lastname: String) : this(null, firstname, lastname)

    fun addDog(dog: Dog) {
        dogs.add(dog)
        dog.master = this
    }

    fun giveUpDog(dog: Dog) {
        dogs.remove(dog)
        dog.master = null
    }

    @PreRemove
    fun removeDogs() {
        dogs.forEach { it.master = null }
    }
}

