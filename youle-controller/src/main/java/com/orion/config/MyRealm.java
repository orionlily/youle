package com.orion.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.orion.domain.User;
import com.orion.service.RoleService;
import com.orion.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * @author Administrator
 * @date 2018/12/25
 */
@Component
public class MyRealm extends AuthorizingRealm{

    @Reference
    private UserService userService;

    @Reference
    private RoleService roleService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("--shiro权限验证！--");
        User user = (User)principalCollection.getPrimaryPrincipal();
        List<String> roles = roleService.getRoleCodeByUserId(user.getId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("--shiro身份验证！--");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user =userService.selectUserByName(username);
        if (null!=user){
             String password = user.getPassword();
             if (password.equals(new String((char[]) token.getCredentials()))){
                 return new SimpleAuthenticationInfo(user,password,getName());
            }else {
                 throw  new AccountException("密码不正确!");
             }
        }else {
            throw  new AccountException("用户名不正确!");
        }
    }
}
