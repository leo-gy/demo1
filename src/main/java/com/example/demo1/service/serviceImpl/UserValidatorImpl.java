package com.example.demo1.service.serviceImpl;

import com.example.demo1.dto.UserDTO;
import com.example.demo1.service.UserValidator;

public class UserValidatorImpl implements UserValidator {

    @Override
    public boolean validate(UserDTO dto) {
        System.out.println("引入新的接口：" + UserValidator.class.getSimpleName());
        return dto.getId() != null;
    }
}
