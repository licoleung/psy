package cn.pourfeelings.psy.controller;

import cn.pourfeelings.psy.pojo.Answer;
import cn.pourfeelings.psy.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

/**
 * @author LicoLeung
 * @create 2019-04-18 10:13
 */
@Controller
public class AnswerController {

    @Autowired
    AnswerService answerService;

//    @GetMapping("/answers")
//    public String getAnswer(){
//
//    }

    @PostMapping("/answer")
    public String addAnswer(Answer answer){
        Date date = new Date();
        answer.setAnTime(date);
        answerService.addAnswer(answer);
        Integer qid = answer.getQid();
        return "redirect:/question/"+qid;
    }
}
