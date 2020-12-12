package com.liangdi.wings.authorization.service.impl;

import com.liangdi.wings.authorization.entity.Role;
import com.liangdi.wings.authorization.mapper.ManagerMapper;
import com.liangdi.wings.authorization.service.ManagerService;
import com.liangdi.wings.authorization.service.PermissionService;
import com.liangdi.wings.authorization.service.RoleService;
import com.liangdi.wings.authorization.entity.Manager;
import com.liangdi.wings.authorization.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Liang Di
 * @since 1.0.0
 *//
@Service
public class ManagerServiceImpl implements ManagerService {

    private final Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);
    private final ManagerMapper managerMapper;
    private final RoleService roleService;
    private final PermissionService permissionService;

    /**
     * 构造器方式注入依赖
     *
     * @param managerMapper {@link ManagerMapper}
     */
    public ManagerServiceImpl(ManagerMapper managerMapper, RoleService roleService, PermissionService permissionService) {
        this.managerMapper = managerMapper;
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    /**
     * 根据用户名获取这个用户
     *
     * @param username 用户名
     * @return {@link Manager}
     */
    @Override
    public Manager getByUsername(String username) {
        if (username.isBlank()) {
            return null;
        }
        return managerMapper.selectByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名查询
        Manager manager = getByUsername(username);
        if (manager == null) {
            return null;
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        // 获取角色
        List<Role> roleList = roleService.getByUsername(username);
        for (Role role : roleList) {
            // 获取权限
            List<Permission> permissionList = permissionService.getByRoleName(role.getName());
            for (Permission permission : permissionList) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getEnglishName()));
            }
        }
        return new User(username, manager.getCredentials(), grantedAuthorities);
    }

}
