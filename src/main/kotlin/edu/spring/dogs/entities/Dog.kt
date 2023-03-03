package edu.spring.dogs.entities

import jakarta.persistence.*
@Entity
class Dog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String = "",
    @ManyToOne
    @JoinColumn(name = "master_id")
    var master: Master? = null,
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "dog_toy",
        joinColumns = [JoinColumn(name = "dog_id")],
        inverseJoinColumns = [JoinColumn(name = "toy_id")]
    )
    var toys: MutableSet<Toy> = mutableSetOf()
) {
    constructor() : this(null, "")
    constructor(name: String) : this(null, name)
}


