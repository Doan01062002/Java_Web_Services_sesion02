package com.example.session02.controller;

import com.example.session02.model.entity.ScreenRoom;
import com.example.session02.service.ScreenRoomService;
import com.example.session02.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/screen-rooms")
public class ScreenRoomController {

    @Autowired
    private ScreenRoomService screenRoomService;

    @Autowired
    private TheaterService theaterService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("screenRooms", screenRoomService.findAll());
        return "screenroom/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("screenRoom", new ScreenRoom());
        model.addAttribute("theaters", theaterService.findAll());
        return "screenroom/add";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute ScreenRoom screenRoom) {
        screenRoomService.save(screenRoom);
        return "redirect:/screen-rooms";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        ScreenRoom screenRoom = screenRoomService.findById(id).orElseThrow();
        model.addAttribute("screenRoom", screenRoom);
        model.addAttribute("theaters", theaterService.findAll());
        return "screenroom/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute ScreenRoom screenRoom) {
        screenRoomService.update(id, screenRoom);
        return "redirect:/screen-rooms";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        screenRoomService.delete(id);
        return "redirect:/screen-rooms";
    }
}
