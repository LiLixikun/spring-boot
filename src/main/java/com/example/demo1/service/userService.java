package com.example.demo1.service;

import com.example.demo1.enums.ResultEnum;
import com.example.demo1.exception.MyException;
import com.example.demo1.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {
    @Autowired
    public userRepository userRepository;

    //@Transactional
    public void getAge(Integer id) throws Exception {

        System.out.println(id);
        Integer age = userRepository.getOne(id).getAge();

        System.out.println(age + "查询到的年级");
        if (age < 10) {
            throw new MyException(ResultEnum.UNLOGIN);
        } else if (age > 10 && age < 20) {
            throw new MyException(ResultEnum.FAIL);
        }
        // return 0;
    }
}
