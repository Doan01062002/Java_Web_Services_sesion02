package com.example.session02.controller;

import com.example.session02.model.entity.Showtime;
import com.example.session02.service.MovieService;
import com.example.session02.service.ScreenRoomService;
import com.example.session02.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/showtimes")
public class ShowtimeController {

    @Autowired
    private ShowtimeService showtimeService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ScreenRoomService screenRoomService;

    @GetMapping
    public String list(Model model,
                       @RequestParam(value = "movieId", required = false) Long movieId,
                       @RequestParam(value = "screenRoomId", required = false) Long screenRoomId,
                       @RequestParam(value = "showDate", required = false) LocalDate showDate) {
        List<Showtime> showtimes = showtimeService.findFilteredShowtimes(movieId, screenRoomId, showDate);
        model.addAttribute("showtimes", showtimes);
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        model.addAttribute("movieId", movieId);
        model.addAttribute("screenRoomId", screenRoomId);
        model.addAttribute("showDate", showDate);
        return "showtime/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("showtime", new Showtime());
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        return "showtime/add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Showtime showtime) {
        showtimeService.save(showtime);
        return "redirect:/showtimes";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Showtime showtime = showtimeService.findById(id).orElseThrow();
        model.addAttribute("showtime", showtime);
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        return "showtime/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Showtime showtime) {
        showtimeService.update(id, showtime);
        return "redirect:/showtimes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        showtimeService.delete(id);
        return "redirect:/showtimes";
    }
}