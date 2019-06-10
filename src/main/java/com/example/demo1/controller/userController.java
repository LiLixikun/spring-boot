package com.example.demo1.controller;

import com.example.demo1.domain.Result;
import com.example.demo1.domain.user;
import com.example.demo1.repository.userRepository;
import com.example.demo1.service.userService;
import com.example.demo1.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class userController {
    @Autowired
    private userRepository repository;
    @Autowired
    private userService myUserService;

    @GetMapping("/user")
    /**
     * 获取用户列表
     */
    public Result<user> list() {
        return ResultUtil.success(repository.findAll());
    }

    /**
     * 创建用户
     */
    @PostMapping("/user")
    public user creat(@Valid user user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;
        }
        user.setAddress(user.getAddress());
        user.setAge(user.getAge());
        user.setUserNmae(user.getUserNmae());
        return repository.save(user);
    }

    /**
     * 根据id查询用户
     */
    @GetMapping("/user/{id}")
    public user findByid(@PathVariable("id") Integer id) {
        return repository.findById(id).orElse(null);
    }


    @PutMapping("/user/{id}")
    public Result<user> updata(@PathVariable("id") Integer id,
                               @RequestParam("age") Integer age,
                               @RequestParam("name") String name) {
        //首先查询该数据
        Optional<user> optional = repository.findById(id);
        //判断是否为空
        if (optional.isPresent()) {
            user user = optional.get();
            user.setAge(age);
            user.setUserNmae(name);
            Result result = new Result();
            result.setCode(200);
            result.setData(user);
            return result;
        }
        return null;
    }

    /**
     * 根据id删除用户
     */
    @DeleteMapping("/user/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        return "删除成功";
    }

    @GetMapping(value = "/user/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception {
        myUserService.getAge(id);
    }
}
