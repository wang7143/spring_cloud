package com.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommoResult<T> {   //通用返回模板
    private Integer code;
    private String message;
    private T data;

    public CommoResult(Integer code,String message){
        this(code,message,null);
    }
}
