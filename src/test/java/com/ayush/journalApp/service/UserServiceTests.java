package com.ayush.journalApp.service;

import com.ayush.journalApp.entity.User;
import com.ayush.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @BeforeAll
    static void setUp() {

    }

//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentsProvider.class)
//    public void testSaveNewUser(User user) {
//        assertTrue(userService.saveNewUser(user));
//    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a+b);
    }
}
