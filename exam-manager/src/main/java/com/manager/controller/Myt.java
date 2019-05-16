//package com.manager.controller;
//
//import org.springframework.stereotype.Controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class Myt {
//    public void findTitleByExamId(Integer examId){
//        //查询出试卷信息以及试题
//        List<PaperVo> paperVos=examMapper.findMsgByExamId(examId);
//
//        List<PaperVo> list1=new ArrayList<>();
//        String[] answers=null;
//        //获取试卷答案列表
//        for(PaperVo paperVo:paperVos){
//            if ((paperVo.getTitleType).equals("单选题")){
//                list1.add(paperVo);
//                answers.add(paperVo.getAnst);
//            }
//        }
//    }
//}
