package com.orion.service;

import com.orion.domain.User;

/**
 * @author Administrator
 * @date 2018/12/27
 */
public interface UserService {

    /**
     * 根据id查询user
     * @param id
     * @return
     */
    User selectUserById(Integer id);

    /**
     * 根据名字查询user
     * @param name
     * @return
     */
    User selectUserByName(String name);


}
