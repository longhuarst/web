package com.clxs.web.error;

//统一业务异常处理类

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"com.clxs.web", })
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    //如果返回的为json数据或者其他对象，就添加该注解
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception{
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(request.getRequestURI());
        errorInfo.setCode(ErrorInfo.SUCCESS);
        return errorInfo;
    }

}
