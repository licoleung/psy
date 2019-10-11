package cn.pourfeelings.psy.mapper;

import cn.pourfeelings.psy.pojo.Question;
import cn.pourfeelings.psy.pojo.QuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionMapper {
    long countByExample(QuestionExample example);

    int deleteByExample(QuestionExample example);

    int insert(Question record);

    int insertSelective(Question record);

    List<Question> selectByExample(QuestionExample example);

    int updateByExampleSelective(@Param("record") Question record, @Param("example") QuestionExample example);

    int updateByExample(@Param("record") Question record, @Param("example") QuestionExample example);

    Question getQuestionByQid(Integer qid);

    List<Question> getQuestionWithUser();
}