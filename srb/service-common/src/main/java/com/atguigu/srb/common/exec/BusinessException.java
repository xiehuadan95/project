package com.atguigu.srb.common.exec;


import com.atguigu.srb.common.result.ResponseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常 必须是一个运行时异常
 * 加上无参构造注解 自定义有参构造方法
 * 1.创建自定义异常类，必须是一个运行时异常  2.抛出这个自定义异常对象 3.统一异常处理类处理
 */
@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    //错误码
    private Integer code;
    //错误消息
    private String message;
    /**
     * @Param message 错误消息
     */
    public BusinessException(String message) {
        this.message = message;
    }
    /**
     * 解决封装异常时候 里面有原有的异常 一样的放进来 不至于系统的异常消失
     * @Param message 错误消息
     * @Param code    错误码
     */
    public BusinessException(String message, Integer code, Throwable cause) {
        super(cause);
        this.message = message;
        this.code = code;
    }
    /**
     * @Param message 错误消息
     * @Param code    错误码
     * @Param cause   原始异常对象
     */
    public BusinessException(String message, Integer code){
        this.message = message;
        this.code = code;
    }
    /**
     * 根据枚举直接 抛异常对象
     * @Param resultCodeEnum
     */
    public BusinessException(ResponseEnum resultCodeEnum){
        this.message=resultCodeEnum.getMsg();
        this.code=resultCodeEnum.getCode();
    }
    /**
     * 根据枚举 及原始异常跟踪对象
     * @Param  resultCodeEnum
     * @Param  cause 原始异常对象
     */
    public BusinessException(ResponseEnum resultCodeEnum, Throwable cause){
        super(cause);
        this.message=resultCodeEnum.getMsg();
        this.code=resultCodeEnum.getCode();
    }
}
