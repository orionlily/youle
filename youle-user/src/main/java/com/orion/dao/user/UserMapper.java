package com.orion.dao.user;

import com.orion.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 * @date 2018/12/27
 */
@Repository
public interface UserMapper {

    /**
     * 根据id查询user
     * @param id
     * @return
     */
    User selectUserById(@Param("id") Integer id);

    /**
     * 根据名字查询user
     * @param name
     * @return
     */
    User selectUserByName(@Param("name") String name);
}
