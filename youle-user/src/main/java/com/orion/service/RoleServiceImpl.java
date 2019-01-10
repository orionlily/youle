package com.orion.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.orion.dao.role.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Administrator
 * @date 2018/12/27
 */
@Component
@Service
public class RoleServiceImpl implements  RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<String> getRoleCodeByUserId(Integer userId) {
        return roleMapper.getRoleCodeByUserId(userId);
    }
}
