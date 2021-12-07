package com.example.ssm.controller;

import com.example.ssm.domain.User;
import com.example.ssm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {




    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/")
    public String user(Model model){
        model.addAttribute("users",userMapper.findAll());
        return "users";
    }

    @RequestMapping("/toAddUser")
    public String toAddUser(){
        return "addUser";
    }

    @RequestMapping("/addUser")
    public String addUser(User user){
        userMapper.save(user);
        return "redirect:/";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(defaultValue = "0") Integer id){
        userMapper.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) {
        System.out.println(id);
        model.addAttribute("user", userMapper.findOne(id));
        return "update";
    }
    @RequestMapping("/update")
    public String update(User user) {
        userMapper.updateById(user);
        return "redirect:/";
    }


}
