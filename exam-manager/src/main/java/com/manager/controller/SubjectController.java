package com.manager.controller;

import com.manager.model.Subject;
import com.manager.result.Result;
import com.manager.service.ServiceImpl.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubjectController {

    @Autowired
    SubjectServiceImpl subjectService;

    //获取所有科目信息
    @RequestMapping("/subject/get_all")
    public Result getAllSubject() {
        List<Subject> subjects = subjectService.findAllSubject();
        return Result.success(subjects);
    }
}