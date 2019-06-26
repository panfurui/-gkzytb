package com.demo.config.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

public class RestResponse<T> {
    private Integer code;
//    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String description;

    public static RestResponse<Boolean> OK = new RestResponse<>(200, Boolean.TRUE, "");
    public static RestResponse<Boolean> ERROR = new RestResponse<>(200, Boolean.FALSE, "");

    public RestResponse() {
    }

    public RestResponse(Integer code, T result, String description) {
        this.code = code;
        this.result = result;
        this.description = description;
    }

    public RestResponse(Integer code, T result) {
        this.code = code;
        this.result = result;
        this.description = "";
    }

    public RestResponse(T result) {
        this.code = 200;
        this.result = result;
        this.description = "";
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
