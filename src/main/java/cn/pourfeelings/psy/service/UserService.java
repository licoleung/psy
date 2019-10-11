package cn.pourfeelings.psy.service;

import cn.pourfeelings.psy.mapper.UserMapper;
import cn.pourfeelings.psy.pojo.User;
import cn.pourfeelings.psy.pojo.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author LicoLeung
 * @create 2019-04-11 11:07
 */
@Service
@Transactional
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserById(Integer id){
        User user = userMapper.getUserByUid(id);
        return  user;
    }

    public List<User> checkName(String username){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(userExample);
        return list;
    }

    public Boolean login(User user){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        criteria.andPasswordEqualTo(user.getPassword());
        List<User> list = userMapper.selectByExample(userExample);
        if(list.size()==1){
            return true;
        }
        else {
            return false;
        }
    }

    public User getUserByUsername(String username){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(userExample);
        return  list.get(0);
    }

    public List<User> getTeacher(){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andTypeEqualTo("老师");
        List<User> teachers = userMapper.selectByExample(userExample);
        return teachers;
    }

    public void edit(User user,String phone,String username,String email){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUidEqualTo(user.getUid());
        User newUser = new User();
        newUser.setPhone(phone);
        newUser.setEmail(email);
        newUser.setUsername(username);
        userMapper.updateByExampleSelective(newUser,userExample);
    }

    public void editPsw(Integer uid,String psw){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUidEqualTo(uid);
        User newUser = new User();
        newUser.setPassword(psw);
        userMapper.updateByExampleSelective(newUser,userExample);
    }

    public void addUser(User user){
        userMapper.insert(user);
    }
}
