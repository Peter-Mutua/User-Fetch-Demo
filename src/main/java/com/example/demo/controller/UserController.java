package com.example.demo.controller;

import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    /**
     * Retrieves all users from the server.
     *
     * @param  page  the page number to fetch (default is 0)
     * @param  size  the number of users to fetch per page (default is 10)
     * @return       a ResponseEntity containing the fetched users or an error message
     */
    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        log.info("getAllUsers function called.....");
        return userService.fetchAllUser(page, size);
    }
}
