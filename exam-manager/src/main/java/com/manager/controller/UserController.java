package com.manager.controller;

import com.manager.model.User;
import com.manager.result.CodeMsg;
import com.manager.result.Result;
import com.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;


    //    获取所有普通用户
    @PostMapping("/user/getAll")
    @ResponseBody
    public Result showAllUser() {
        List<User> users = userService.findAllUser();
        return Result.success(users);
    }

    //   根据id获取用户信息
    @GetMapping("/user/get_user/{userId}")
    @ResponseBody
    public Result toUpdate(@PathVariable("userId") int userId) {
        User user = userService.findById(userId);
        return Result.success(user);
    }

    //    执行用户信息修改
    @RequestMapping("/user/doupdate")
    @ResponseBody
    public Result doUpdate(User user) {
        boolean a = userService.updateUser(user);
        if (a) {
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        } else {
            return Result.error(CodeMsg.UPDATE_FAILE);
        }
    }

    //    删除用户
    @RequestMapping("/user/dodelete/{userid}")
    @ResponseBody
    public Result deleteUser(@PathVariable("userid") int userId) {
        boolean result = userService.deleteUser(userId);
        if (result) {
            return Result.success(CodeMsg.DELETE_SUCCESS);
        } else {
            return Result.error(CodeMsg.DELETE_FAILE);
        }
    }


    //    执行添加用户
    @RequestMapping("/user/doadd")
    @ResponseBody
    public Result insertUser(User user) {
        boolean result = userService.insertUser(user);
        if (result) {
            return Result.success(CodeMsg.ADD_SUCCESS);
        } else {
            return Result.error(CodeMsg.ADD_FAILE);
        }
    }
}

