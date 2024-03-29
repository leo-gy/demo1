package com.example.demo1.controller;

import com.example.demo1.dto.UserDTO;
import com.example.demo1.result.ResultVO;
import com.example.demo1.service.IUserBatchService;
import com.example.demo1.service.IUserService;
import com.example.demo1.util.ResultVOUtil;
import com.example.demo1.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = {"用户表API"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IUserBatchService iUserBatchService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//    @Autowired
//    private MongoTemplate mongoTemplate;

    @ApiOperation(value = "创建用户", notes = "用户表API")
    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<?> createUser(@RequestBody UserDTO dto) {
        return iUserService.createUser(dto);
    }

    @ApiOperation(value = "更新用户信息", notes = "用户表API")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<UserVO> updateUser(@RequestBody UserDTO dto) {
        return (ResultVO<UserVO>) iUserService.updateUser(dto);
    }

    @ApiOperation(value = "查找用户", notes = "用户表API")
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<UserVO> getUser(@RequestBody UserDTO dto) {
//        UserValidator userValidator= (UserValidator) iUserService;
//        if(userValidator.validate(dto)){
//            System.out.println("************pass*************");
//        }else{
//            System.out.println("************reject*************");
//        }
        return (ResultVO<UserVO>) iUserService.getUser(dto);
    }

    @ApiOperation(value = "删除用户", notes = "用户表API")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<?> deleteUser(@RequestBody UserDTO dto) {
        return iUserService.deleteUser(dto);
    }

    @ApiOperation(value = "获取用户列表", notes = "用户表API")
    @RequestMapping(value = "/getUsers", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<List<UserVO>> getUsers() {
        return (ResultVO<List<UserVO>>) iUserService.getUsers();
    }


    @ApiOperation(value = "异常处理测试", notes = "用户表API")
    @RequestMapping(value = "/exceptionHandler", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<List<UserVO>> exceptionHandler() throws Exception {
        throw new Exception("aaaaa");
//        throw new RuntimeException("有个异常");
//        throw new SpringException("asdasdas");
    }


    @ApiOperation(value = "数据绑定", notes = "用户表API")
    @RequestMapping(value = "/dataBind", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<?> dataBind(Model model) {
        Map<String, Object> map = (Map<String, Object>) model.asMap().get("mp");
        System.out.println(map.get("name"));
        return ResultVOUtil.returnSuccess();
    }

//    @ApiOperation(value = "数据预处理", notes = "用户表API")
//    @RequestMapping(value = "/dataPreDeal", method = RequestMethod.GET)
//    @ResponseBody
//    public ResultVO<?> dataPreDeal(@ModelAttribute("a") User user, @ModelAttribute("b") UserDTO userDTO) {
//        System.out.println(user.getId());
//        System.out.println(userDTO.getId());
//        return ResultVOUtil.returnSuccess();
//    }


//    @ApiOperation(value = "mongo测试", notes = "用户表API")
//    @RequestMapping(value = "/mongoDemo", method = RequestMethod.POST)
//    @ResponseBody
//    public ResultVO<?> mongoDemo(@RequestBody UserDTO userDTO) {
//        UserDTO user = mongoTemplate.save(userDTO, "user");
//        System.out.println( mongoTemplate.getCollection("user"));
//        List<UserDTO> dtos=mongoTemplate.findAll(UserDTO.class,"user");
//        redisTemplate.opsForValue().set("test","test1",10, TimeUnit.SECONDS);
//        return ResultVOUtil.returnSuccess();
//    }

    @ApiOperation(value = "批量添加用户", notes = "用户表API")
    @RequestMapping(value = "/batchCreateUsers", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<?> batchCreateUsers(@RequestBody List<UserDTO> dtos) {
        return  iUserBatchService.batchCreateUsers(dtos);
    }
}
