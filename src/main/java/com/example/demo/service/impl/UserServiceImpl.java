package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.ResponseHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Object> fetchAllUser(int page, int size) {
        try {
            /**
             * Fetches all users from the database and checks to ensure that it does not contain any soft deleted users
             * with pagination.
             *
             * @param page     the page number to fetch
             * @param size     the number of users to fetch per page
             * @return         Response containing the fetched users or an error message
             */

            Pageable pageable = PageRequest.of(page, size);
            Page<User> userPage = userRepository.findAllBySoftDelete(false, pageable);
            List<User> users = userPage.getContent();

            if (users.isEmpty()) {
                return ResponseHandler.generateResponse("No users found!", HttpStatus.NOT_FOUND, null);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("users", users);
            response.put("currentPage", userPage.getNumber());
            response.put("totalItems", userPage.getTotalElements());
            response.put("totalPages", userPage.getTotalPages());

            return ResponseHandler.generateResponse("All users fetched successfully!", HttpStatus.OK, response);

        } catch (Exception e) {
            log.error("An error occurred while fetching all users!", e);
            return ResponseHandler.generateResponse("An error occurred while fetching all users! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}

