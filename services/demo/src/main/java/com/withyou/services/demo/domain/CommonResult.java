package com.withyou.services.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author jeremy.zhao
 */
@Getter
@Setter
@ToString
public class CommonResult<T> {

    private String message;
    private boolean success;
    private T result;

    public CommonResult() {}

    public CommonResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public CommonResult(String message, boolean success, T result) {
        this.message = message;
        this.success = success;
        this.result = result;
    }

    public static <T> CommonResult ok(T result) {
        return new CommonResult<T>(null, true, result);
    }

    public static CommonResult failure(String message) {
        return new CommonResult(message, false);
    }
}
