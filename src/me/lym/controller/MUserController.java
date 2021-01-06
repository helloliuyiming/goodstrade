package me.lym.controller;

import me.lym.entity.User;
import me.lym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MUserController {

    @Autowired
    private UserService userService;

    @RequestMapping("testUser")
    public String test() {
        User user = new User();
        user.setEmail("11111@qq.com");
        user.setGender("��");
        user.setImage("a.jpg");
        user.setPassword("aaa");
        user.setPhoneNumber("138xxxx2569");
        user.setUsername("test");
        userService.newUser(user);
        user.setUserId(12);
        user.setEmail("after@qq.com");
        userService.updateInfo(user);
        System.out.println(userService.queryById(12));
        return "success";
    }
}
