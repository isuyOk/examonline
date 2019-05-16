package com.manager.service.ServiceImpl;

import com.manager.mapper.TitleMapper;
import com.manager.model.Title;
import com.manager.model.TitleExample;
import com.manager.service.TitleService;
import com.manager.vo.TitleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleServiceImpl implements TitleService {
    @Autowired
    private TitleMapper titleMapper;

    //根据题型筛选
    @Override
    public List<TitleVo> findTitleByType(String titleType) {
        return titleMapper.findTitleSubject(titleType);
    }

    //查询所有题目
    @Override
    public List<TitleVo> findAllTitleVo() {
       return titleMapper.findAllTitleVo();
    }

    @Override
    public List<TitleVo> findByLike(String question) {
        return titleMapper.findByLike(question);
    }

    //根据问题模糊查询
    @Override
    public List<Title> findByQuestion(String question) {
        TitleExample titleExample = new TitleExample();
        titleExample.createCriteria().andQuestionLike("question");
        return titleMapper.selectByExample(titleExample);
    }



    //    根据科目跟题目类型查询
    @Override
    public List<Title> findBySubjectAndType(int subjectId, String titleType) {
        TitleExample titleExample = new TitleExample();
        titleExample.createCriteria()
                .andSubjectIdEqualTo(subjectId)
                .andTitleTypeEqualTo(titleType);
        return titleMapper.selectByExample(titleExample);
    }

    @Override
    public Title findById(int titleId) {
        return titleMapper.selectByPrimaryKey(titleId);
    }

    //插入题
    @Override
    public boolean insertTitle(Title title) {
        int result = titleMapper.insertSelective(title);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    //根据id删除题
    @Override
    public boolean deleteTitle(int titleId) {
        int result = titleMapper.deleteByPrimaryKey(titleId);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    //更新单选题
    @Override
    public boolean updateTitle(Title title) {
        int result = titleMapper.updateByPrimaryKeySelective(title);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
}