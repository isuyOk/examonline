package com.manager.service.ServiceImpl;

import com.manager.mapper.UserMapper;
import com.manager.model.User;
import com.manager.model.UserExample;
import com.manager.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User checkUser(String email, String password) {
        UserExample userExample=new UserExample();
        userExample.createCriteria().andEmailEqualTo(email).andPasswordEqualTo(password);
        List<User> list = userMapper.selectByExample(userExample);
        if (list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }
}
