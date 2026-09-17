package com.atguigu.spzx.common.exception;

import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: GlobalExceptionHandler
 * Package:
 * Description:
 *
 * @Author SeaUrchin
 * @Create 2026/9/12 13:50
 * @Version 1.0
 */
@ControllerAdvice //controller增强类,对所有controller进行增强,进行统一处理
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class) //捕获所有异常
    @ResponseBody //返回json数据格式
    public Result error(Exception e) {
        return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
    }

    //自定义异常处理
    @ExceptionHandler(value = GuiguException.class)
    @ResponseBody
    public Result guiguError(GuiguException e){
        return Result.build(null,e.getResultCodeEnum());
    }
}
