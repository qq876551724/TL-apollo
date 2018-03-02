package com.apollo.commons.shiro;

import java.util.HashSet;
import java.util.Set;

import com.apollo.commons.redis.RedisUtils;
import com.apollo.entity.user.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * MyShiroRealm <br>
 *
 * @Description :
 * @Author : tianlei
 * @Create : 2017/11/10.
 * @E-mail : 876551724@qq.com
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private RedisUtils redisUtils;
    /*
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Set<String> roleNames = new HashSet<String>();
        Set<String> permissions = new HashSet<String>();
        //TODO:数据库或redis获取权限
        roleNames.add("administrator");//添加角色
        permissions.add("newPage.jhtml");  //添加权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);
        return info;
    }

    /*
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = (String) token.getPrincipal();
        User user = (User)redisUtils.get(username);
        if(user != null){
            return new SimpleAuthenticationInfo(user.getName(), user.getPwd(), getName());
        }else{
            throw new AuthenticationException();
        }
    }

}
