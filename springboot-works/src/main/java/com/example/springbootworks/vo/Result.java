package com.example.springbootworks.vo;

import com.example.springbootworks.common.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 与前端交互的数据结构
 *
 * @param <T> 携带数据的类型
 */
@Data
@AllArgsConstructor
public class Result<T> {
    private Integer code;   //状态码
    private String message; //返回信息
 
    private T data;         //返回数据

    public static <T> Result<T> success(String message) {
        return new Result<>(ResultCode.SUCCESS, message, null);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultCode.SUCCESS, message, data);
    }
 
    public static <T> Result<T> error(int resultCode, String message) {
        return new Result<>(resultCode, message, null);
    }
}