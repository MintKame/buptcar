package com.bupt.buptcar.dao;

import com.bupt.buptcar.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User getUserByMsg(User user);

    boolean hasUserName(String userName);

    void addUser(User userMsg);
}
