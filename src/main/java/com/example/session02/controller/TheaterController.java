package com.example.session02.controller;

import com.example.session02.model.entity.Theater;
import com.example.session02.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("theaters", theaterService.findAll());
        return "theater/list";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("theater", new Theater());
        return "theater/add";
    }

    @PostMapping("/add")
    public String save(Theater theater){
        theaterService.save(theater);
        return "redirect:/theaters";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model){
        Theater theater = theaterService.findById(id).orElseThrow();
        model.addAttribute("theater", theater);
        return "theater/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, Theater theater){
        theaterService.update(id, theater);
        return "redirect:/theaters";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        theaterService.delete(id);
        return "redirect:/theaters";
    }
}
