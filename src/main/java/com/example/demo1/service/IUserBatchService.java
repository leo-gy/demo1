package com.example.demo1.service;

import com.example.demo1.dto.UserDTO;
import com.example.demo1.result.ResultVO;

import java.util.List;

public interface IUserBatchService {

    public ResultVO<?> batchCreateUsers(List<UserDTO> dtos);

}
