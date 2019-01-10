package com.orion.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.orion.dao.user.UserMapper;
import com.orion.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2018/12/27
 */
@Component
@Service
public class UserServiceImpl implements   UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }
}
