package com.atguigu.srb.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {
    private Integer cod;
    private String message;
    private Map<String,Object> data=new HashMap<>();
    /**
     * 构造函数私有化
     */
    //将结果对象构造私有 避免被随意new
    private Result(){};
    /**
     * 返回成功结果
     * @return
     */
    //通过静态方法调用，生成对象
    public static Result ok(){
        Result result = new Result();
        result.setCod(ResponseEnum.SUCCESS.getCode());
        result.setMessage(ResponseEnum.SUCCESS.getMsg());
        return result;
    }
    /**
     * 返回失败结果
     * @return
     */
     public static Result error(){
         Result result = new Result();
         result.setCod(ResponseEnum.ERROR.getCode());
         result.setMessage(ResponseEnum.ERROR.getMsg());
         return result;
     }

    /**
     * 设置特定的结果
     * @param responseEnum
     * @return
     */
     public static Result setResult(ResponseEnum responseEnum){
         Result result = new Result();
         result.setCod(responseEnum.getCode());
         result.setMessage(responseEnum.getMsg());
         return result;
     }
     public Result data(String key,Object value){
         this.data.put(key,value);
         return this;
     }

    /**
     * 参数就是map的情况，重载data方法
     * @param map
     * @return
     */
    public Result data(Map<String,Object> map){
       this.setData(map);
       return this;
    }

    /**
     * 让消息更个性化，设置消息内容的入口
     * @param msg
     * @return
     */
    public  Result msg(String msg){
         this.setMessage(msg);
         return this;
    }

    /**
     * 设置特定的响应码 便于以后扩展
     * @param cod
     * @return
     */
    public Result cod(Integer cod){
        this.setCod(cod);
        return this;
    }






}
