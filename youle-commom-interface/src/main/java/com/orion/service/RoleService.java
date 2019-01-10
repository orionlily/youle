package com.orion.service;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/12/27
 */
public interface RoleService {
    /**
     * 根据userId获取角色编码
     * @param  userId
     * @return
     */
    List<String>  getRoleCodeByUserId(Integer userId);
}
