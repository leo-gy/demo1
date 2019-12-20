package com.example.demo1.service.serviceImpl;

import com.example.demo1.dto.UserDTO;
import com.example.demo1.entity.User;
import com.example.demo1.exception.SpringException;
import com.example.demo1.mapper.UserMapper;
import com.example.demo1.result.ResultVO;
import com.example.demo1.service.IUserService;
import com.example.demo1.util.ResultVOUtil;
import com.example.demo1.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.NESTED)
    public ResultVO<?> createUser(UserDTO dto) {
        if(dto.getName().equals("string345")){
            throw new SpringException("自定义异常");
        }
        userMapper.createUser(dto);
       return ResultVOUtil.returnSuccess();
    }

    @Override
    public  ResultVO<?> updateUser(UserDTO dto) {
        userMapper.updateUser(dto);
        User user = userMapper.getUser(dto);
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        return ResultVOUtil.returnSuccess(vo);
    }

    @Override
    public  ResultVO<?> getUser(UserDTO dto) {
        User user = userMapper.getUser(dto);
        UserVO vo = new UserVO();
        if (user != null) {
            BeanUtils.copyProperties(user, vo);
        }
        return ResultVOUtil.returnSuccess(vo);
    }

    @Override
    public  ResultVO<?> deleteUser(UserDTO dto) {
        userMapper.deleteUser(dto);
        return ResultVOUtil.returnSuccess();
    }

    @Override
    public  ResultVO<?> getUsers() {
        List<User> users = userMapper.getUsers();
        List<UserVO> vos = new ArrayList<>();
        if (!users.isEmpty()) {
            users.forEach(u -> {
                UserVO vo = new UserVO();
                BeanUtils.copyProperties(u, vo);
                vos.add(vo);
            });
        }
        return ResultVOUtil.returnSuccess(vos);
    }

}
