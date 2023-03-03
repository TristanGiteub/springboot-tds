package edu.spring.dogs.controllers

import edu.spring.dogs.entities.Dog
import edu.spring.dogs.entities.Master
import edu.spring.dogs.repositories.DogRepository
import edu.spring.dogs.repositories.MasterRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class MainController(private val masterRepository: MasterRepository, private val dogRepository: DogRepository) {

    @GetMapping("/")
    fun index(model: Model): String {
        val masters = masterRepository.findAll()
        val dogs = dogRepository.findAll()
        val spaDogs = dogRepository.findByMasterIsNull()

        model.addAttribute("masters", masters)
        model.addAttribute("dogs", dogs)
        model.addAttribute("spaDogs", spaDogs)

        return "index"
    }

    @GetMapping("/master/{id}/delete")
    fun deleteMaster(@PathVariable id: Long): String {
        masterRepository.deleteById(id)
        return "redirect:/"
    }

    @GetMapping("/addMaster")
    fun addMasterForm(model: Model): String {
        model.addAttribute("master", Master())
        return "addMaster"
    }

    @PostMapping("/master/add")
    fun addMaster(@RequestParam name: String, @RequestParam lastname: String): String {
        val master = Master(name, lastname)
        masterRepository.save(master)
        return "redirect:/"
    }

    @GetMapping("/addDog")
    fun addDogForm(model: Model): String {
        model.addAttribute("dog", Dog())
        model.addAttribute("masters", masterRepository.findAll())
        return "addDog"
    }

    @PostMapping("/addDog")
    fun addDogSubmit(@ModelAttribute("dog") dog: Dog): String {
        dogRepository.save(dog)
        return "redirect:/"
    }

    @GetMapping("/adopt/{dogId}")
    fun adoptDog(@PathVariable dogId: Long, @RequestParam masterId: Long): String {
        val dog = dogRepository.findById(dogId).orElseThrow()
        val master = masterRepository.findById(masterId).orElseThrow()

        master.dogs.add(dog)
        dog.master = master

        masterRepository.save(master)
        dogRepository.save(dog)

        return "redirect:/"
    }
}
