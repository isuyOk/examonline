package com.manager.service;

import com.manager.model.User;

import java.util.List;

public interface UserService {
        public List<User> findAllUser();

        public List<User> findByName(String userName);

        public List<User> findByEmail(String email);

        public boolean insertUser(User user);

        public User findById(int userid);

        public boolean deleteUser(int userId);

        public boolean updateUser(User user);
    }


