package com.example.demo1.handle;

import com.example.demo1.domain.Result;
import com.example.demo1.exception.MyException;
import com.example.demo1.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//不指定包默认加了@Controller和@RestController都能控制
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof MyException) {
            MyException myException = (MyException) e;
            return ResultUtil.err(myException.getCode(), myException.getMessage());
        }
        return ResultUtil.err(-1, "未知错误");
    }
}
