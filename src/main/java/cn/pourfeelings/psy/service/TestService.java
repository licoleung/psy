package cn.pourfeelings.psy.service;

import cn.pourfeelings.psy.mapper.TestMapper;
import cn.pourfeelings.psy.pojo.Test;
import cn.pourfeelings.psy.pojo.TestExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LicoLeung
 * @create 2019-04-22 19:33
 */
@Service
public class TestService {
    @Autowired
    TestMapper testMapper;

    public List<Test> getTest(){
        TestExample testExample = new TestExample();
        List<Test> tests = testMapper.selectByExample(testExample);
        return tests;
    }

    public Test getTestById(Integer id){
        TestExample testExample = new TestExample();
        TestExample.Criteria criteria = testExample.createCriteria();
        criteria.andTidEqualTo(id);
        List<Test> tests = testMapper.selectByExample(testExample);
        return tests.get(0);
    }
}
