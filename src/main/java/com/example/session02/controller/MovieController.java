package com.example.session02.controller;

import com.example.session02.model.entity.Movie;
import com.example.session02.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("movies", movieService.findAll());
        return "movie/list";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("movie", new Movie());
        return "movie/add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Movie movie){
        movieService.save(movie);
        return "redirect:/movies";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model){
        Movie movie = movieService.findById(id).orElseThrow();
        model.addAttribute("movie", movie);
        return "movie/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute Movie movie){
        movieService.update(id,movie);
        return "redirect:/movies";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        movieService.delete(id);
        return "redirect:/movies";
    }
}
