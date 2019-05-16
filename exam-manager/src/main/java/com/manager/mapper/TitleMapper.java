package com.manager.mapper;

import com.manager.model.Title;
import com.manager.model.TitleExample;

import java.util.List;

import com.manager.vo.TitleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleMapper {
    long countByExample(TitleExample example);

    int deleteByExample(TitleExample example);

    int deleteByPrimaryKey(Integer titleId);

    int insert(Title record);

    int insertSelective(Title record);

    List<Title> selectByExample(TitleExample example);

    Title selectByPrimaryKey(Integer titleId);

    int updateByExampleSelective(@Param("record") Title record, @Param("example") TitleExample example);

    int updateByExample(@Param("record") Title record, @Param("example") TitleExample example);

    int updateByPrimaryKeySelective(Title record);

    int updateByPrimaryKey(Title record);

    //根据题型筛选
    List<TitleVo> findTitleSubject(String titleType);

    //    查询所有试题跟科目
    List<TitleVo> findAllTitleVo();

    List<TitleVo> findByLike(String question);
}