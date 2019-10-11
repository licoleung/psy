package cn.pourfeelings.psy.service;

import cn.pourfeelings.psy.mapper.CommentMapper;
import cn.pourfeelings.psy.pojo.Comment;
import cn.pourfeelings.psy.pojo.CommentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LicoLeung
 * @create 2019-04-14 16:50
 */
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    public List<Comment> getCommentByEid(Integer eid){
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        criteria.andEidEqualTo(eid);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        return  comments;
    }

    public List<Comment> getCommentWithUserByEid(Integer eid){
        List<Comment> comments = commentMapper.selectCommentWithByEid(eid);
        return  comments;
    }

    public void addComment(Comment comment){
        commentMapper.insert(comment);
    }
}
