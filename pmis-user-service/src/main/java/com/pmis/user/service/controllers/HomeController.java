package com.pmis.user.service.controllers;

import com.pmis.user.service.models.User;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return ("Home Controller");
    }

    //@RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping("/user")
    public User users() {
        User user = new User();
        user.setId("1");
        user.setEmail("mnoman@i2cinc.com");
        user.setUsername("damon");
        return user;
    }

    @GetMapping("/user/{id1}/{id2}")
    public String pathVariables(@PathVariable String id1, @PathVariable("id2") String name) {
        return "id1 = " +id1 +" and id2 = "+ name;
    }

    @GetMapping("/requestParam")
    public String requestParams(@RequestParam(required = false, defaultValue = "") String name, @RequestParam String emailId){
        return "Your name is: "+ name+ " and Email Id is: "+ emailId;
    }

}
