package com.example.quanlychitieuv2.enums;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class RegisterBeanForPermisson {

    @Bean("addTransaction")
    public String AddTransaction() {
        return Permission.ADD_TRANSACTION.getValue();
    }
}
