package com.demo.config.rest;

public class RestException extends RuntimeException {
    private RestResponse<String> restResponse;

    public RestException(RestErrorEnum errorEnum) {
        super(errorEnum.getDescription());
        this.restResponse = new RestResponse<>();
        this.restResponse.setCode(errorEnum.getCode());
        this.restResponse.setDescription(errorEnum.getDescription());
    }

    public RestException(RestErrorEnum errorEnum, String result) {
        super(result);
        this.restResponse = new RestResponse<>();
        this.restResponse.setCode(errorEnum.getCode());
        this.restResponse.setResult(result);
        this.restResponse.setDescription(errorEnum.getDescription());
    }

    public Integer getCode() {
        return this.restResponse.getCode();
    }

    public String getResult() {
        return this.restResponse.getResult();
    }

    public String getDescription() {
        return this.restResponse.getDescription();
    }
}
