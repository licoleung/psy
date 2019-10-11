package cn.pourfeelings.psy.mapper;

import cn.pourfeelings.psy.pojo.Essay;
import cn.pourfeelings.psy.pojo.EssayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EssayMapper {
    long countByExample(EssayExample example);

    int deleteByExample(EssayExample example);

    int insert(Essay record);

    int insertSelective(Essay record);

    List<Essay> selectByExampleWithBLOBs(EssayExample example);

    List<Essay> selectByExample(EssayExample example);

    int updateByExampleSelective(@Param("record") Essay record, @Param("example") EssayExample example);

    int updateByExampleWithBLOBs(@Param("record") Essay record, @Param("example") EssayExample example);

    int updateByExample(@Param("record") Essay record, @Param("example") EssayExample example);
}