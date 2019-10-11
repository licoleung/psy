package cn.pourfeelings.psy.mapper;

import cn.pourfeelings.psy.pojo.Problem;
import cn.pourfeelings.psy.pojo.ProblemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProblemMapper {
    long countByExample(ProblemExample example);

    int deleteByExample(ProblemExample example);

    int insert(Problem record);

    int insertSelective(Problem record);

    List<Problem> selectByExample(ProblemExample example);

    int updateByExampleSelective(@Param("record") Problem record, @Param("example") ProblemExample example);

    int updateByExample(@Param("record") Problem record, @Param("example") ProblemExample example);
}