package com.atguigu.srb.common.exec;



import com.atguigu.srb.common.result.ResponseEnum;
import com.atguigu.srb.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

//相当于异常切面  异常处理器

/**
 * 统一异常处理器
 */
@Slf4j
@RestControllerAdvice  //一旦项目中出现异常 就会跑到这里来 相当于切面
public class UnifiedExceptionHandler {
    //异常处理方法
    @ExceptionHandler(value =Exception.class)
    public Result handleException(Exception e){
      log.error(e.getMessage(),e);
      return Result.error();
    }
    //处理自定义异常的 处理方法
    @ExceptionHandler(value=BusinessException.class)
    public Result handleException(BusinessException e){
        log.error(e.getMessage(),e);
        return Result.error().msg(e.getMessage()).cod(e.getCode());
    }
    //异常处理方法2
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public Result handleException(BadSqlGrammarException e){
        log.error(e.getMessage(),e);
        return Result.setResult(ResponseEnum.BAD_SQL_GRAMMAR_ERROR);
    }

    /**
     * controller层之前发生的异常
     * @param e
     * @return
     */
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public Result handlerServletException(Exception e){
        log.error(e.getMessage(),e);
        return Result.error().msg(ResponseEnum.SERVLET_ERROR.getMsg()).cod(ResponseEnum.SERVLET_ERROR.getCode());
    }

}


