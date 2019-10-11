package cn.pourfeelings.psy.controller;

import cn.pourfeelings.psy.pojo.Problem;
import cn.pourfeelings.psy.pojo.Test;
import cn.pourfeelings.psy.service.ProblemService;
import cn.pourfeelings.psy.service.TestService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author LicoLeung
 * @create 2019-04-22 16:49
 */
@Controller
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    ProblemService problemService;

    @GetMapping("/psyTest")
    public String getTests(Model model, @RequestParam(value = "page",required = false,defaultValue = "1")int page){
        PageHelper.startPage(page,2);
        List<Test> test = testService.getTest();
        model.addAttribute("tests",test);
        PageInfo<Test> pageInfo = new PageInfo<>(test,5);//页码目录只有五个
        int nowPage = pageInfo.getPageNum();//当前页面
        int[] nums = pageInfo.getNavigatepageNums();

        //previous page
        if(pageInfo.isIsFirstPage()){
            model.addAttribute("prePage",nowPage);
        }
        else {
            model.addAttribute("prePage",nowPage-1);
        }

        //next page
        if(pageInfo.isIsLastPage()){
            model.addAttribute("nextPage",nowPage);
        }
        else {
            model.addAttribute("nextPage",nowPage+1);
        }
        model.addAttribute("nums",nums);
        List<Test> hotTest = testService.getTest();
        model.addAttribute("hotTest",hotTest);
        return "test/psyTest";
    }

    @GetMapping("/test/{id}")
    public String getTest(@PathVariable("id") Integer id,Model model){
        List<Problem> problemByTid = problemService.getProblemByTid(id);
        model.addAttribute("problems",problemByTid);
        Test testById = testService.getTestById(id);
        model.addAttribute("test",testById);
        return "test/test-single";
    }
}
