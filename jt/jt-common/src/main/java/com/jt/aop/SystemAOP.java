package com.jt.aop;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice//拦截controller层
//@ResponseBody
@RestControllerAdvice   //@ControllerAdvice+@ResponseBody 定义全局异常的处理类
public class SystemAOP {

    /**
     * 定义全局异常处理方法    当遇到了什么异常时，程序开始执行    参数一般为class类型
     *  如果一旦发生异常，则应该输出异常的信息，之后返回错误数据即可
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    public Object systemAop(Exception e){
        e.printStackTrace();
        return SysResult.fail();
    }
}
