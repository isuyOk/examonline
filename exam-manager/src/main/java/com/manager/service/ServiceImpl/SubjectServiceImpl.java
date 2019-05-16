package com.manager.service.ServiceImpl;

import com.manager.mapper.SubjectMapper;
import com.manager.model.Subject;
import com.manager.model.SubjectExample;
import com.manager.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectMapper subjectMapper;
    @Override
    public List<Subject> findAllSubject() {
        SubjectExample subjectExample=new SubjectExample();
        return subjectMapper.selectByExample(subjectExample);
    }

}
