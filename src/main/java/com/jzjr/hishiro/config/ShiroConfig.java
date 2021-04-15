package com.jzjr.hishiro.config;

import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public SimpleAccountRealm realm() {
        //创建SimpleAccountRealm对象
        SimpleAccountRealm realm = new SimpleAccountRealm();
        //添加两个用户，并赋予角色
        realm.addAccount("admin","admin","ADMIN");
        realm.addAccount("normal","normal","NORMAL");
        return realm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        //创建DefaultWebSecurityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置其使用的Realm
        securityManager.setRealm(this.realm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        //创建ShiroFilterFactoryBean对象，用于创建shiroFilter过滤器
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        filterFactoryBean.setSecurityManager(this.securityManager());
        //设置url
        //登录url
        filterFactoryBean.setLoginUrl("/login");
        filterFactoryBean.setSuccessUrl("/login_success");
        //无权限url
        filterFactoryBean.setUnauthorizedUrl("/unauthorized");
        //设置url的权限配置
        filterFactoryBean.setFilterChainDefinitionMap(this.filterChainDefinitionMap());
        return filterFactoryBean;
    }

    private Map<String, String> filterChainDefinitionMap() {
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        //允许匿名访问
        filterMap.put("/test/echo","anon");
        //需要admin角色
        filterMap.put("/test/admin","roles[ADMIN]");
        //需要normal角色
        filterMap.put("/test/normal","roles[NORMAL]");
        //推出
        filterMap.put("/logout","logout");
        //剩余的url，需要经过认证
        filterMap.put("/**","authc");
        return filterMap;
    }


}
