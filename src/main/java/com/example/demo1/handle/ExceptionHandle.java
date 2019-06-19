package com.example.demo1.handle;

import com.example.demo1.VO.ResultVo;
import com.example.demo1.exception.SellException;
import com.example.demo1.utils.ResultVoUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//不指定包默认加了@Controller和@RestController都能控制
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVo handle(Exception e) {
        if (e instanceof SellException) {
            SellException myException = (SellException) e;
            return ResultVoUtils.err(myException.getCode(), myException.getMessage());
        }
        return ResultVoUtils.err(-1, "未知错误");
    }
}
