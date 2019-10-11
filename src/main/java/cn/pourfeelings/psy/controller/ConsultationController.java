package cn.pourfeelings.psy.controller;

import cn.pourfeelings.psy.pojo.User;
import cn.pourfeelings.psy.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author LicoLeung
 * @create 2019-04-24 21:26
 */
@Controller
public class ConsultationController {

    @Autowired
    UserService userService;

    @GetMapping("/consultation")
    public String consult(Model model, @RequestParam(value = "page",required = false,defaultValue = "1")int page, HttpSession session){
        User loginUser = (User)session.getAttribute("loginUser");
        if(loginUser.getType().trim().equals("学生")){
            PageHelper.startPage(page,4);
            List<User> teachers = userService.getTeacher();
            PageInfo<User> pageInfo = new PageInfo<>(teachers,5);//页码目录只有五个
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

            model.addAttribute("teachers",teachers);
            model.addAttribute("nums",nums);
            return "consultation/consultation";
        }
        else{
            return "consultation/consultationForTeacher";
        }
    }
}
