package edu.spring.dogs.entities

import jakarta.persistence.*

@Entity
class Toy(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var type: String? = null,
    var label: String? = null,
    @ManyToMany(mappedBy = "toys")
    var dogs: MutableSet<Dog> = mutableSetOf()
) {
    constructor() : this(null)
    constructor(type: String, label: String) : this(null, type, label)
}
