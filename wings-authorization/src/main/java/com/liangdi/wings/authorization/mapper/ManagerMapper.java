package com.liangdi.wings.authorization.mapper;

import com.liangdi.wings.authorization.entity.Manager;
import org.springframework.stereotype.Repository;

/**
 * @author Liang Di
 * @since 1.0.0
 */
@Repository
public interface ManagerMapper {

    /**
     * 根据username查询
     * @param username 用户名
     * @return {@link Manager}
     */
    Manager selectByUsername(String username);
}
