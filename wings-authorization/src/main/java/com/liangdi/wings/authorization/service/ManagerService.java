package com.liangdi.wings.authorization.service;

import com.liangdi.wings.authorization.entity.Manager;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Liang Di
 * @since 1.0.0
 */
public interface ManagerService extends UserDetailsService {

    /**
     * 根据用户名获取这个用户
     *
     * @param username 用户名
     * @return {@link Manager}
     */
    Manager getByUsername(String username);

}
