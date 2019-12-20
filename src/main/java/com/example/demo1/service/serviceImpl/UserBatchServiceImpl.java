package com.example.demo1.service.serviceImpl;

import com.example.demo1.dto.UserDTO;
import com.example.demo1.enums.SexEnum;
import com.example.demo1.exception.SpringException;
import com.example.demo1.result.ResultVO;
import com.example.demo1.service.IUserBatchService;
import com.example.demo1.service.IUserService;
import com.example.demo1.util.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserBatchServiceImpl implements IUserBatchService {

    @Autowired
    IUserService iUserService;

        @Override
        @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
        public ResultVO<?> batchCreateUsers(List<UserDTO> dtos) {
            UserDTO dto=new UserDTO();
            dto.setId(Long.valueOf(36));
            dto.setName(dtos.get(0).getName());
            dto.setSex(SexEnum.女);
            iUserService.updateUser(dto);
            int count=0;
//            for(int i=0;i<dtos.size();i++){
//                iUserService.createUser(dtos.get(i));
//                count++;
//            }
            try {
                for(int i=0;i<dtos.size();i++){
                    iUserService.createUser(dtos.get(i));
                    count++;
                }
            }catch (Exception e){
//                throw new SpringException(e.getMessage());
                e.printStackTrace();
            }
//            UserDTO dto1=new UserDTO();
//            dto1.setId(Long.valueOf(36));
//            dto1.setName("oooooooooooooooo");
//            dto1.setSex(SexEnum.女);
//            iUserService.updateUser(dto1);
            return ResultVOUtil.returnSuccess(count);
        }
}
