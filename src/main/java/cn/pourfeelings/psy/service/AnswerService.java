package cn.pourfeelings.psy.service;

import cn.pourfeelings.psy.mapper.AnswerMapper;
import cn.pourfeelings.psy.pojo.Answer;
import cn.pourfeelings.psy.pojo.AnswerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LicoLeung
 * @create 2019-04-18 10:14
 */
@Service
public class AnswerService {
    @Autowired
    AnswerMapper answerMapper;

    public List<Answer> getAnswers(){
        List<Answer> answerWithUserAndQuestion = answerMapper.getAnswerWithUserAndQuestion();
        return answerWithUserAndQuestion;
    }

    public List<Answer> getAnswersByQid(Integer qid){
        List<Answer> answers = answerMapper.getAnswerWithUserAndQuestionByQid(qid);
        return answers;
    }

    public void addAnswer(Answer answer){
        answerMapper.insert(answer);
    }
}
