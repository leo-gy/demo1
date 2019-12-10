package com.example.demo1.exception;

import com.example.demo1.result.eunms.ResultEnum;
import lombok.Getter;

@Getter
public class SpringException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    public SpringException(Integer code,String msg){
        super(msg);
        this.code=code;
    }

    public SpringException(String msg){
        super(msg);
        this.code= 400;
    }

    public SpringException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }
}
