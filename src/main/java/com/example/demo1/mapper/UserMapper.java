package com.example.demo1.mapper;

import com.example.demo1.dto.UserDTO;
import com.example.demo1.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    public void createUser(UserDTO dto);

    public void updateUser(UserDTO dto);

    public User getUser(UserDTO dto);

    public void deleteUser(UserDTO dto);

    public List<User> getUsers();
}
