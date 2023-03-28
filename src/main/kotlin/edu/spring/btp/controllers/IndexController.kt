package edu.spring.btp.controllers

import edu.spring.btp.entities.Complaint
import edu.spring.btp.entities.Domain
import edu.spring.btp.repositories.ComplaintRepository
import edu.spring.btp.repositories.DomainRepository
import edu.spring.btp.repositories.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import java.security.Principal

@Controller
class IndexController(
        private val domainRepository: DomainRepository,
        private val complaintRepository: ComplaintRepository,
        private val userRepository: UserRepository
) {

    @GetMapping(value = ["/", "", "/index"])
    fun index(model: ModelMap): String {
        //val domain = domainRepository.findByParentIsNull()
        val domain = domainRepository.findByName("Root")
        val domains = domainRepository.findByParentName("Root")
        model["domain"] = domain
        model["domains"] = domains
        return "index"
    }

    @GetMapping("/domain/{name}")
    fun listDomains(@PathVariable name: String, model: Model): String {
        val domain = domainRepository.findByName(name)
        val domains = domainRepository.findByParentName(name)
        model.addAttribute("domains", domains)
        model.addAttribute("domain", domain)
        return "index"
    }

    @GetMapping("/complaints/{domain}")
    fun listComplaints(@PathVariable domain: String, model: Model): String {
        val domain = domainRepository.findByName(domain)
        val complaints = domain?.complaints
        model.addAttribute("complaints", complaints)
        model.addAttribute("domain", domain)
        return "complaints"
    }

    @GetMapping("/complaints/{domain}/sub")
    fun listSubComplaints(@PathVariable domain: String, model: Model): String {
        val domain = domainRepository.findByName(domain)
        val complaints = domain?.complaints
        model.addAttribute("complaints", complaints)
        model.addAttribute("domain", domain)
        return "complaints"
    }

    @GetMapping("/complaints/{domain}/new")
    fun newComplaintForm(@PathVariable domain: String, model: Model, principal: Principal?): String {
        val complaint = Complaint()
        val domains = domainRepository.findByParentIsNull()
        model.addAttribute("complaint", complaint)
        model.addAttribute("domains", domains)
        model.addAttribute("domainName", domain)
        return "forms/complaint"
    }

    @PostMapping("/complaints/{domain}/new")
    fun newComplaintSubmit(
            @PathVariable domain: String,
            @ModelAttribute complaint: Complaint,
            principal: Principal?
    ): String {
        complaint.user = userRepository.findByUsernameOrEmail(principal?.name ?: "") ?: return "redirect:/login"
        complaint.domain = domainRepository.findByName(domain) ?: return "redirect:/complaints/{domain}/new"
        complaintRepository.save(complaint)
        return "redirect:/complaints/$domain"
    }

    @GetMapping("/login")
    fun loginForm(): String {
        return "forms/login"
    }

    @PostMapping("/login")
    fun loginSubmit(): String {
        return "redirect:/"
    }

    @GetMapping("/signup")
    fun signupForm(): String {
        return "forms/signup"
    }

    @PostMapping("/signup")
    fun signupSubmit(): String {
        return "redirect:/"
    }

    @GetMapping("/logout")
    fun logout(): String {
        return "redirect:/"
    }
}
