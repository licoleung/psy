package cn.pourfeelings.psy.controller;

import cn.pourfeelings.psy.pojo.Comment;
import cn.pourfeelings.psy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author LicoLeung
 * @create 2019-04-14 22:56
 */
@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/comment/{eid}")
    public String getCommentWithUserByEid(@PathVariable("eid") Integer eid){
        List<Comment> commentWithUserByEid = commentService.getCommentWithUserByEid(eid);
        Comment comment = commentWithUserByEid.get(0);
        return comment.toString();
    }

    @PostMapping("/comment")
    public String addComment(Comment comment){
        Date date = new Date();
        comment.setCmTime(date);
        commentService.addComment(comment);
        Integer eid = comment.getEid();
        return "redirect:/essay/"+eid;
    }
}
