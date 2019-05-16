package com.manager.service;

import com.manager.model.Title;
import com.manager.vo.TitleVo;

import java.util.List;

public interface TitleService {

    //    根据题型筛选
    public List<TitleVo> findTitleByType(String titleType);

    public List<TitleVo> findAllTitleVo();

    //    根据question模糊查询
    public List<TitleVo> findByLike(String text);



    public List<Title> findByQuestion(String question);

    public List<Title> findBySubjectAndType(int subjectId, String titleType);

    public Title findById(int titleId);

    public boolean insertTitle(Title title);

    public boolean deleteTitle(int titleId);

    public boolean updateTitle(Title title);
}