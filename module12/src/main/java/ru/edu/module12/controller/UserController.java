package ru.edu.module12.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.module12.entity.User;
import ru.edu.module12.service.UserService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping
public class UserController {
     
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping(value = "/viewUsers")
    public ModelAndView viewUsers() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.setViewName("view-users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }
   
    @GetMapping(value = "/addUser")
    public ModelAndView addUserView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-user");
        return modelAndView;
    }
    
    @GetMapping(value = "/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    @PostMapping(value = "/addUser")
    public ModelAndView addUser(@RequestParam("name") String name, @RequestParam("age") String age) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("response");
        userService.addUser(modelAndView, name, age);
        return modelAndView;
    }
    
    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity<User> deleteUser(@RequestParam(name = "id") String id) { 
        return userService.deleteUser(id);
    }
    
    @PutMapping(value = "/updateUser")
    public ResponseEntity<User> deleteUser(@RequestBody User user) { 
        return userService.updateUser(user);
    }
       
}
