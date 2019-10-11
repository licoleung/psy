package cn.pourfeelings.psy.service;

import cn.pourfeelings.psy.mapper.EssayMapper;
import cn.pourfeelings.psy.pojo.Essay;
import cn.pourfeelings.psy.pojo.EssayExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EssayService {

    @Autowired
    EssayMapper essayMapper;

    public void addEssay(Essay essay){
        essayMapper.insert(essay);
    }

    public List<Essay> getEssay(){
        EssayExample essayExample = new EssayExample();
        return essayMapper.selectByExample(essayExample);
    }

    public Essay getEssayById(Integer id){
        EssayExample essayExample = new EssayExample();
        EssayExample.Criteria criteria = essayExample.createCriteria();
        criteria.andEidEqualTo(id);
        List<Essay> essayList = essayMapper.selectByExampleWithBLOBs(essayExample);
        Essay essay = essayList.get(0);

        EssayExample updateEssayExample = new EssayExample();
        EssayExample.Criteria criteria1 = updateEssayExample.createCriteria();
        criteria1.andEidEqualTo(essay.getEid());
        essay.setReadnum(essay.getReadnum()+1);
        essayMapper.updateByExample(essay,updateEssayExample);

        return essay;
    }

    public List<Essay> getHotEssay(){
        EssayExample essayExample = new EssayExample();
        essayExample.setOrderByClause("readNum desc,likeNum desc");
        return essayMapper.selectByExample(essayExample);
    }
}

