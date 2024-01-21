package ru.edu.module13.controller;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import ru.edu.module13.controller.UserController;
import ru.edu.module13.entity.User;
import ru.edu.module13.service.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({UserController.class})
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UserService userService;
    
    @InjectMocks
    ObjectMapper mapper;
    
    @BeforeEach
    void setUp() {
        
    }
    
    @AfterEach
    void tearDown() {
        
    }
    
    
    @Test
    void getAllUsers() throws Exception {
        User user1 = new User(1, "Petr", 63);
        User user2 = new User(188, "Boris", 46);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        
        when(userService.findAll()).thenReturn(users);
        
        mockMvc.perform(get("/getUsers"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }
    
    @Test
    void updateUser() throws Exception {
        User user = new User(1, "Petr", 63);
        ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        
        when(userService.updateUser(any(User.class))).thenReturn(responseEntity);
        
        mockMvc.perform(put("/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(user))
                        )
                        .andDo(print())
                        .andExpect(content().json(mapper.writeValueAsString(user)))
                        .andExpect(status().isOk());
    }
    
    @Test 
    void deleteUser() throws Exception {
        User user = new User(1, "Petr", 63);
        ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        
        when(userService.deleteUser("1")).thenReturn(responseEntity);
        
        mockMvc.perform(delete("/deleteUser?id=1"))
                        .andDo(print())
                        .andExpect(content().json(mapper.writeValueAsString(user)))
                        .andExpect(status().isOk());
    }

}
