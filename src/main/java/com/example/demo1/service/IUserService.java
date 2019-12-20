package com.example.demo1.service;

import com.example.demo1.dto.UserDTO;
import com.example.demo1.result.ResultVO;

import java.util.List;

public interface IUserService {

    public ResultVO<?> createUser(UserDTO dto);

    public ResultVO<?> updateUser(UserDTO dto);

    public ResultVO<?> getUser(UserDTO dto);

    public ResultVO<?> deleteUser(UserDTO dto);

    public ResultVO<?> getUsers();
}
