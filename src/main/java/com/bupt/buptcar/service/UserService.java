package com.bupt.buptcar.service;

import com.bupt.buptcar.dao.UserMapper;
import com.bupt.buptcar.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User login(User userMsg) {
        return userMapper.getUserByMsg(userMsg);
    }

    public String register(User userMsg) {
        boolean hasUserName = userMapper.hasUserName(userMsg.getUserName());
        if (hasUserName){
            return "用户名已被注册";
        }else {
            userMapper.addUser(userMsg);
            return "注册成功";
        }
    }
}