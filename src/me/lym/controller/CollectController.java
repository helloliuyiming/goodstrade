package me.lym.controller;

import me.lym.service.FavoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectController {

    @Autowired
    private FavoritiesService favoritiesService;

    @RequestMapping("testC")
    public String test() {
        System.out.println(favoritiesService.getCollects(1));
        return "success";
    }

}
