package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public ModelAndView allUsers() {
        List<User> userList = userService.allUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    @PatchMapping(value = "/edit/{id}")
    public ModelAndView editPage(@PathVariable("id") int id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        //modelAndView.addObject("user", user);
        modelAndView.addObject("user", userService.getById(id));
        return modelAndView;
    }

    @PostMapping(value = "/edit")
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.editUser(user);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addUser");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        modelAndView.addObject("user", user);
        userService.add(user);
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        User user = userService.getById(id);
        userService.delete(user);
        return modelAndView;
    }
}

//    @GetMapping(value="/edit")
//    public String printCar(HttpServletRequest request, Model model){
//        //String str = request.getParameter("count");
//        return "users";
//    }
