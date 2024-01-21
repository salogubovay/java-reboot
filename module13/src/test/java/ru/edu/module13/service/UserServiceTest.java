package ru.edu.module13.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import ru.edu.module13.entity.User;
import ru.edu.module13.repository.UserRepository;
import ru.edu.module13.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;
    
    private UserService service;
    
    @BeforeEach
    void setUp() {
        service = new UserService(repository);
    }
    
    @AfterEach
    void tearDown() {
        
    }
    
    @Test
    void findAll() {
        User user1 = new User(1, "Petr", 63);
        User user2 = new User(188, "Boris", 46);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        when(repository.findAll()).thenReturn(users);
        List<User> allUsers = service.findAll();
        assertThat(allUsers).hasSameSizeAs(users);
    }
    
    @Test
    void addUser() {
        String name = "Petr";
        String age = "63";
        User user1 = new User(1, "Petr", 63);
        when(repository.save(any(User.class))).thenReturn(user1);
        ModelAndView modelAndView = new ModelAndView();
        service.addUser(modelAndView, name, age);
        assertThat(modelAndView.getModel().get("headerText")).isNotNull();
        assertThat(modelAndView.getModel().get("bodyText")).isNotNull();
    }
    
    @Test
    void deleteUser() {
        User user = new User(1, "Petr", 63);
        Optional<User> optionalUser = Optional.of(user);
        String id = "1";
        when(repository.findById(any(Long.class))).thenReturn(optionalUser);
        doNothing().when(repository).deleteById(any(Long.class));
        ResponseEntity<User> responseEntity = service.deleteUser(id);
        assertThat(responseEntity.getBody()).isEqualTo(user);
    }
    
    @Test
    void updateUser() {
        User userOld = new User(1, "Petr", 54);
        User userNew = new User(1, "Ivan", 63);
        Optional<User> optionalUser = Optional.of(userOld);
        when(repository.findById(any(Long.class))).thenReturn(optionalUser);
        when(repository.save(userNew)).thenReturn(userNew);
        ResponseEntity<User> responseEntity = service.updateUser(userNew);
        assertThat(responseEntity.getBody()).isEqualTo(userNew);
    }

}
