package com.xl.www.app.repo;

import com.xl.www.app.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserRepository {

    public User getById(Long id) {
        return new User();
    }
}
