package com.orion.controller.user;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/12/27
 */

@RestController
@RequestMapping("/")
public class UserController {

    @RequestMapping("/login")
    public String login(String userName,String password){
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,String> result = new HashMap<>();
        try {
            subject.login(token);

            result.put("result","success");
            result.put("msg","登陆成功");
            return JSON.toJSONString(result);
        }catch (AccountException e){
            result.put("result","fail");
            result.put("msg","登陆失败:"+e.getMessage());
            return JSON.toJSONString(result);
        }

    }

    @RequestMapping("/notLogin")
    public String notLogin(String userName,String password){
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        Subject subject = SecurityUtils.getSubject();
        Map<String,String> result = new HashMap<>();
        result.put("result","fail");
        result.put("msg","尚未登录");
        return JSON.toJSONString(result);
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        Map<String,String> result = new HashMap<>();
        result.put("result","success");
        result.put("msg","成功注销");
        return JSON.toJSONString(result);
    }


    @RequestMapping("/sys")
    public String sys(){

        Map<String,String> result = new HashMap<>();
        result.put("result","success");
        result.put("msg",SecurityUtils.getSubject().getPrincipal()+"成功登录有权限");
        return JSON.toJSONString(result);
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){
        Map<String,String> result = new HashMap<>();
        result.put("result","fail");
        result.put("msg","没有权限");
        return  JSON.toJSONString(result);
    }

    @RequestMapping(value = "/common")
    public String common() {
        Map<String,String> result = new HashMap<>();
        result.put("result","success");
        result.put("msg","公共资源");
        return JSON.toJSONString(result);
    }


}
