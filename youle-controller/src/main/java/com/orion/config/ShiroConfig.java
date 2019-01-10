package com.orion.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 *
 * @author Administrator
 * @date 2018/12/25
 *
 * 过滤器默认权限表 {anon=anon, authc=authc, authcBasic=authcBasic, logout=logout,
 * noSessionCreation=noSessionCreation, perms=perms, port=port,
 * rest=rest, roles=roles, ssl=ssl, user=user}
 * <p>
 * anon, authc, authcBasic, user 是第一组认证过滤器
 * perms, port, rest, roles, ssl 是第二组授权过滤器
 * <p>
 * user 和 authc 的不同：当应用开启了rememberMe时, 用户下次访问时可以是一个user, 但绝不会是authc,
 * 因为authc是需要重新认证的, user表示用户不一定已通过认证, 只要曾被Shiro记住过登录状态的用户就可以正常发起请求,比如rememberMe
 * 以前的一个用户登录时开启了rememberMe, 然后他关闭浏览器, 下次再访问时他就是一个user, 而不会authc
 *
 *
 * 初始化 ShiroFilterFactoryBean 的时候需要注入 SecurityManager
 */
@Configuration
@PropertySource("classpath:shiroRealm/myRealm.properties")
@ConfigurationProperties(prefix = "shiro-filter")
public class ShiroConfig {

    private String notLogin;

    private String unauthorized;

    private Map<String,String> filtermap;

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //需要设置securityManager
        shiroFilter.setSecurityManager(securityManager);
        // setLoginUrl 如果不设置值，默认会自动寻找Web工程根目录下的"/login.jsp"页面 或 "/login" 映射
        shiroFilter.setLoginUrl(notLogin);
        // 无权限跳转
        shiroFilter.setUnauthorizedUrl(unauthorized);


        //3.LinkedHashMap是有序的，进行顺序拦截器配置
//        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
//        filterChainDefinitionMap.put("/login","anon");
//        filterChainDefinitionMap.put("/sys/**" ,"roles[systemAdmin]");
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
//        filterChainDefinitionMap.put("/**" ,"authc");
        filtermap.remove("/**");
        //properties不保证顺序，先移除，再添加/**=authc
        filtermap.put("/**","authc");
        shiroFilter.setFilterChainDefinitionMap(filtermap);

        System.out.println("---shiroConfig拦截器工程配置!----");

        return shiroFilter;
    }

    @Bean
    public SecurityManager securityManager(MyRealm myRealm){
        System.out.println("初始化securityManager , 加载自定义realm");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    public void setNotLogin(String notLogin) {
        this.notLogin = notLogin;
    }

    public void setUnauthorized(String unauthorized) {
        this.unauthorized = unauthorized;
    }

    public void setFiltermap(Map<String, String> filtermap) {
        this.filtermap = filtermap;
    }
}
