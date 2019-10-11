package cn.pourfeelings.psy.service;

import cn.pourfeelings.psy.mapper.ProblemMapper;
import cn.pourfeelings.psy.pojo.Problem;
import cn.pourfeelings.psy.pojo.ProblemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LicoLeung
 * @create 2019-04-23 20:38
 */
@Service
public class ProblemService {

    @Autowired
    ProblemMapper problemMapper;

    public List<Problem> getProblemByTid(Integer tid){
        ProblemExample problemExample = new ProblemExample();
        ProblemExample.Criteria criteria = problemExample.createCriteria();
        criteria.andTidEqualTo(tid);
        List<Problem> problems = problemMapper.selectByExample(problemExample);
        return problems;
    }
}
