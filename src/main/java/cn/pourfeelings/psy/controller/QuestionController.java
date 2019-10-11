package cn.pourfeelings.psy.controller;

import cn.pourfeelings.psy.pojo.Answer;
import cn.pourfeelings.psy.pojo.AnswerExample;
import cn.pourfeelings.psy.pojo.Question;
import cn.pourfeelings.psy.service.AnswerService;
import cn.pourfeelings.psy.service.QuestionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @author LicoLeung
 * @create 2019-04-18 8:41
 */
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @GetMapping("/questions")
    public String que(Model model,@RequestParam(value = "page",required = false,defaultValue = "1")int page){
        PageHelper.startPage(page,3);
        List<Answer> answers = answerService.getAnswers();

        PageInfo<Answer> pageInfo = new PageInfo<>(answers,5);
        int nowPage = page;
        int[] nums = pageInfo.getNavigatepageNums();

        if(pageInfo.isIsFirstPage()){
            model.addAttribute("prePage",nowPage);
        }
        else{
            model.addAttribute("prePage",nowPage-1);
        }
        if(pageInfo.isIsLastPage()){
            model.addAttribute("nextPage",nowPage);
        }
        else {
            model.addAttribute("nextPage",nowPage+1);
        }
        model.addAttribute("nums",nums);

        PageHelper.startPage(1,3);
        List<Question> questions = questionService.getQuestionsWithUser();

        model.addAttribute("answers",answers);
        model.addAttribute("newQuestion",questions);

        return "question/question";
    }

    @GetMapping("/question/{id}")
    public String getQuestion(@PathVariable("id") Integer id, Model model,@RequestParam(value = "page",required = false,defaultValue = "1")int page){
        Question question = questionService.getQuestionByQid(id);
        model.addAttribute("question",question);

        PageHelper.startPage(page,2);
        List<Answer> answersByQid = answerService.getAnswersByQid(id);
        model.addAttribute("answersByQid",answersByQid);

        PageInfo<Answer> pageInfo = new PageInfo<>(answersByQid,5);
        int nowPage = page;
        int[] nums = pageInfo.getNavigatepageNums();

        if(pageInfo.isIsFirstPage()){
            model.addAttribute("prePage",nowPage);
        }
        else{
            model.addAttribute("prePage",nowPage-1);
        }
        if(pageInfo.isIsLastPage()){
            model.addAttribute("nextPage",nowPage);
        }
        else {
            model.addAttribute("nextPage",nowPage+1);
        }
        model.addAttribute("nums",nums);

        PageHelper.startPage(1,3);
        List<Question> questions = questionService.getQuestionsWithUser();
        model.addAttribute("newQuestion",questions);

        return "question/question-single";
    }

    @GetMapping("/ask")
    public String ask(){
        return "question/ask";
    }

    @PostMapping("/question")
    public String addQuestion(Question question){
        question.setTime(new Date());
        questionService.addQuestion(question);
        return "redirect:/questions";
    }
}
