package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface UserService {

    /**
     * Fetches all users, paginated, based on the provided page and size.
     *
     * @param  page  the page number to fetch (0-indexed)
     * @param  size  the number of users to fetch per page
     * @return       a ResponseEntity containing the fetched users, or an error object if the request fails
     */

    ResponseEntity<Object> fetchAllUser(int page, int size);
}
