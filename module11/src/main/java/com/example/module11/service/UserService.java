package com.example.module11.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;
import com.example.module11.entity.User;
import com.example.module11.repository.UserRepository;

public class UserService {
    @Autowired
    UserRepository userRepository;
    
    public List<User> findAll() {
        return userRepository.findAll();
    }
    
    public void addUser(ModelAndView modelAndView, String name, String age) {
        User user = new User();
        String respHeader, respStrBody;
        if (!isNumber(age) || Integer.valueOf(age) < 0) {
            respHeader = "Результат";
            respStrBody = "Неверный формат возраста. Скорректируйте значения";
        } else {
            user.setName(name);
            user.setAge(Integer.valueOf(age));
            User newUser = userRepository.save(user);
            respHeader = "Результат";
            respStrBody = "Пользователь успешно добавлен";
            modelAndView.addObject("user", newUser);
        }
        setModel(modelAndView, respHeader, respStrBody);
    }
    
    public ResponseEntity<User> deleteUser(String id) {
        ResponseEntity<User> responseEntity;
        User user = new User();
        if (!isNumber(id) || Long.valueOf(id) < 0) {
            responseEntity = new ResponseEntity<User>(user, HttpStatus.BAD_REQUEST);
        } else {
            Optional<User> userOptional = userRepository.findById(Long.parseLong(id));
            if (userOptional.isEmpty()) {
                responseEntity = new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
            } else {
                userRepository.deleteById(Long.parseLong(id));
                responseEntity = new ResponseEntity<User>(userOptional.get(), HttpStatus.OK);
            }
        }
        return responseEntity;
    }
    
    public ResponseEntity<User> updateUser(User user) {
        ResponseEntity<User> responseEntity;
        Optional<User> userOptional = userRepository.findById(user.getId());
        if (userOptional.isEmpty()) {
            responseEntity = new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        } else {
            userRepository.save(user);
            responseEntity = new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return responseEntity;
    }
    
    private void setModel(ModelAndView modelAndView, String headerText, String bodyText) {
        modelAndView.addObject("headerText", headerText);
        modelAndView.addObject("bodyText", bodyText);
    }
    
    private boolean isNumber(String number) {
        try {
            Long.parseLong(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
