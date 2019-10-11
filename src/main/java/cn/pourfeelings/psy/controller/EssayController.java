package cn.pourfeelings.psy.controller;

import cn.pourfeelings.psy.pojo.Comment;
import cn.pourfeelings.psy.pojo.Essay;
import cn.pourfeelings.psy.service.CommentService;
import cn.pourfeelings.psy.service.EssayService;
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
 * @create 2019-03-13 18:12
 */
@Controller
public class EssayController {
    @Autowired
    EssayService essayService;

    @Autowired
    CommentService commentService;

    @GetMapping("/essay/{id}")
    public String getEssayById(@PathVariable("id") Integer id,Model model){
        Essay essayById = essayService.getEssayById(id);
        model.addAttribute("essay",essayById);

        List<Comment> comments = commentService.getCommentWithUserByEid(id);
        model.addAttribute("comments",comments);

        return "essay/essay-single";
    }

    @GetMapping("/essays")
    public String getEssay(Model model, @RequestParam(value = "page",required = false,defaultValue = "1")int page){

        PageHelper.startPage(page,6);
        List<Essay> essayList = essayService.getEssay();

        PageInfo<Essay> pageInfo = new PageInfo<>(essayList,5);//页码目录只有五个
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
        model.addAttribute("essayList",essayList);

        //hot5文章
        PageHelper.startPage(1,5);
        List<Essay> hotEssay = essayService.getHotEssay();
        model.addAttribute("hotEssays",hotEssay);

        return "essay/essay";
    }

}
