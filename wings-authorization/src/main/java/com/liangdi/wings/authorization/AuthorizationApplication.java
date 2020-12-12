package com.liangdi.wings.authorization;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Alone
 * @since 1.0.0
 */
@SpringBootApplication
@MapperScan("com.liangdi.wings.authorization.mapper")
public class AuthorizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }
}
