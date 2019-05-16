package com.manager.service;

import com.manager.model.User;

public interface LoginService {
    User checkUser(String email,String password);
}
