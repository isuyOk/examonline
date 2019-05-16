package com.manager.controller;

import com.manager.model.Subject;
import com.manager.model.Title;
import com.manager.result.CodeMsg;
import com.manager.result.Result;
import com.manager.service.ServiceImpl.SubjectServiceImpl;
import com.manager.service.ServiceImpl.TitleServiceImpl;
import com.manager.service.SubjectService;
import com.manager.util.ImportExcelUtil;
import com.manager.vo.TitleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TitleController {

    @Autowired
    SubjectServiceImpl subjectService;
    @Autowired
    TitleServiceImpl titleService;

    //    显示所有试题
    @RequestMapping("/title/findall")
    @ResponseBody
    public Result findAllTitle() {
        System.out.println("查询所有试题");
        List<TitleVo> titles = titleService.findAllTitleVo();
        for (Title title:titles){
            System.out.println(title.getTitleId());
        }
        return Result.success(titles);
    }

    //题库查询
    @RequestMapping("/title/search/{question}")
    @ResponseBody
    public Result doSearch(@PathVariable("question") String question) {
        System.out.println(question);
        List<TitleVo> titles = titleService.findByLike(question);
        return Result.success(titles);
    }

    //执行判断题题添加
    @PostMapping("/title/doadd")
    @ResponseBody
    public Result addSingle(Title title) {
        boolean result = titleService.insertTitle(title);
        if (result) {
            return Result.success(CodeMsg.ADD_SUCCESS);
        } else {
            return Result.error(CodeMsg.ADD_FAILE);
        }
    }


    @GetMapping("/title/findall_judge")
    @ResponseBody
    public Result singleManager() {
        List<TitleVo> titles = titleService.findTitleByType("judge");
        return Result.success(titles);
    }

    @GetMapping("/title/findall_single")
    @ResponseBody
    public Result findAllSingle() {
        List<TitleVo> titles = titleService.findTitleByType("single");
        return Result.success(titles);
    }

    @GetMapping("/title/findall_multi")
    @ResponseBody
    public Result findAllMulti() {
        List<TitleVo> titles = titleService.findTitleByType("multiple");
        return Result.success(titles);
    }

    //初始化判断题修改页面
    @GetMapping("/title/init_update/{titleId}")
    @ResponseBody
    public Result<Map> toUpdate(@PathVariable("titleId") int titleId) {
        Title title = titleService.findById(titleId);
        List<Subject> subjects = subjectService.findAllSubject();
        Map map = new HashMap();
        map.put("title", title);
        map.put("subjects", subjects);
        return Result.success(map);
    }

    //执行修改
    @PostMapping("/title/doupdate")
    @ResponseBody
    public Result doUpdate(Title title) {
        System.out.println("执行修改");
        boolean result = titleService.updateTitle(title);
        if (result) {
            return Result.success(CodeMsg.UPDATE_SUCCESS);
        } else {
            return Result.success(CodeMsg.UPDATE_FAILE);
        }
    }

    //删除题
    @RequestMapping("/title/dodelete/{titleId}")
    @ResponseBody
    public Result doDelete(@PathVariable("titleId") int titleId) {
        boolean result = titleService.deleteTitle(titleId);
        if (result) {
            return Result.success(CodeMsg.DELETE_SUCCESS);
        } else {
            return Result.success(CodeMsg.DELETE_FAILE);
        }
    }

    @RequestMapping(value="/do/upload",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public  Title  uploadExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        InputStream in =null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        if(file.isEmpty()){
            throw new Exception("文件不存在！");
        }
        in = file.getInputStream();
        listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());
        in.close();

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i <listob.size(); i++) {
            List<Object> lo = listob.get(i);
            Title title=new Title();
            title.setQuestion(String.valueOf(lo.get(1)));
            title.setAnsa(String.valueOf(lo.get(2)));
            title.setAnsb(String.valueOf(lo.get(3)));
            title.setAnsc(String.valueOf(lo.get(4)));
            title.setAnsd(String.valueOf(lo.get(5)));
            title.setAnse(String.valueOf(lo.get(6)));
            title.setAnsf(String.valueOf(lo.get(7)));
            title.setAnst((String) lo.get(8));
//            String subjectName= (String) lo.get(9);
//            int subjectId= subjectService.getSubjectId(subjectName);
            title.setSubjectId(1);
            title.setTitleType(String.valueOf(lo.get(10)));
            titleService.insertTitle(title);
        }
        return null;
    }

}
