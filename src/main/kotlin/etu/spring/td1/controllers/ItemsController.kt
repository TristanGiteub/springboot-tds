package etu.spring.td1.controllers

import etu.spring.td1.models.Item
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes


@Controller
@SessionAttributes("items")
class ItemsController {

    @get:ModelAttribute("items")
    val items: Set<Item>
        get() = HashSet<Item>()
        items.add(Item(nom:"Foo"))
        return items

    @RequestMapping("/")
    fun indexAction():String{
        return "index"
    }
}