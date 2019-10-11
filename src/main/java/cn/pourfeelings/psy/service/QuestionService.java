package cn.pourfeelings.psy.service;

import cn.pourfeelings.psy.mapper.QuestionMapper;
import cn.pourfeelings.psy.pojo.Question;
import cn.pourfeelings.psy.pojo.QuestionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LicoLeung
 * @create 2019-04-18 9:00
 */
@Service
public class QuestionService {
    @Autowired
    QuestionMapper questionMapper;

    public Question getQuestionByQid(Integer qid){
        QuestionExample questionExample = new QuestionExample();
        QuestionExample.Criteria criteria = questionExample.createCriteria();
        criteria.andQidEqualTo(qid);
        List<Question> questions = questionMapper.selectByExample(questionExample);
        return questions.get(0);
    }

    public List<Question> getQuestions(){
        QuestionExample questionExample = new QuestionExample();
        QuestionExample.Criteria criteria = questionExample.createCriteria();
        List<Question> questions = questionMapper.selectByExample(questionExample);
        return questions;
    }

    public List<Question> getQuestionsWithUser(){
        List<Question> questionWithUser = questionMapper.getQuestionWithUser();
        return questionWithUser;
    }

    public void addQuestion(Question question){
        questionMapper.insert(question);
    }

}
