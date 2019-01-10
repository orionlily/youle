package com.orion.dao.role;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/12/27
 */
@Repository
public interface RoleMapper {
    /**
     * 根据userid获取角色code
     * @param userId
     * @return
     */
    List<String> getRoleCodeByUserId(@Param("userId") Integer userId);
}
