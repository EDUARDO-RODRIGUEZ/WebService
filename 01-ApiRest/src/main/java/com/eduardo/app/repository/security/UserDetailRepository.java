package com.eduardo.app.repository.security;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailRepository {

    private final static List<UserDetails> USERS = Arrays.asList(
            new User("ed@ed.com", "$2a$10$CwLX8LaqQGEy9WxWwzWx.uGVngSSjM8MDGY7F7CFm9RkYnOErIXEW", Collections.emptyList()),
            new User("jose@jose.com", "$2a$10$CwLX8LaqQGEy9WxWwzWx.uGVngSSjM8MDGY7F7CFm9RkYnOErIXEW", Collections.emptyList())
    );

    public UserDetails findByEmail(String email) {
        return USERS.stream()
                .filter((user) -> user.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Not User Email Found"));
    }

}
