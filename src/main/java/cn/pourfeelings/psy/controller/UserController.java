package cn.pourfeelings.psy.controller;

import cn.pourfeelings.psy.pojo.User;
import cn.pourfeelings.psy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author LicoLeung
 * @create 2019-04-11 10:28
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @ResponseBody
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        System.out.println(user);
        return user.toString();
    }

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Map<String, Object> map, HttpSession session) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        Boolean login = userService.login(user);
        if (login) {
            User userByUsername = userService.getUserByUsername(username);
            session.setAttribute("loginUser",userByUsername);
            return "redirect:/index";
        } else {
            map.put("msg","用户名或密码错误");
            return "user/login";
        }
    }

    @GetMapping("/user/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/index";
    }

    @PostMapping("/editUser")
    public String edit(HttpSession session,String phone,String username,String email){
        User loginUser = (User)session.getAttribute("loginUser");
        userService.edit(loginUser,phone,username,email);
        loginUser.setEmail(email);
        loginUser.setPhone(phone);
        loginUser.setUsername(username);
        return "user/info";
    }

    @PostMapping("/editPsw")
    public String editPassword(HttpSession session,String password,String confirm,Map<String, Object> map){
        if(!confirm.equals(password)){
            map.put("msg","两次密码不一致");
            return "user/editPsw";
        }
        User loginUser = (User)session.getAttribute("loginUser");
        userService.editPsw(loginUser.getUid(),password);
        session.removeAttribute("loginUser");
        return "user/login";
    }

    @RequestMapping("/addUser")
    public String addUser(String password,String username,HttpSession session,String confirm,Map<String, Object> map){
        if(!confirm.equals(password)){
            map.put("msg","两次密码不一致");
            return "user/register";
        }
        List<User> list = userService.checkName(username);
        if(list.size()>0){
            map.put("msg","名字已经有人取了");
            return "user/register";
        }
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setType("学生");
        userService.addUser(user);
        return "user/login";
    }

    @GetMapping("/toChat/{id}")
    public String getTeacherToChat(@PathVariable("id") Integer id, Model model){
        User userById = userService.getUserById(id);
        model.addAttribute("teacher",userById);
        return "consultation/chat";
    }

    @GetMapping("/TeachertoChat")
    public String TTC(Model model, HttpSession session){
        User loginUser = (User)session.getAttribute("loginUser");
        model.addAttribute("teacher",loginUser);
        return "consultation/chat";
    }

}
