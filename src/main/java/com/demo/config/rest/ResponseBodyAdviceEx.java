package com.demo.config.rest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@ConditionalOnWebApplication
public class ResponseBodyAdviceEx implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return "org.springframework.http.converter.json.MappingJackson2HttpMessageConverter".equals(converterType.getName());
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof RestResponse) {
            // 不加多次头部
            return body;
        }

        // 满足一个条件即可
        if (isMingDuRestController(returnType)){
            return new RestResponse<>(RestErrorEnum.SUCCESS.getCode(), body, null);
        }
        return body;
    }


    private boolean isMingDuRestController(MethodParameter returnType) {
        // 类含有PackageResponseBody并且value为true
        PackageResponseBody type = returnType.getContainingClass().getAnnotation(PackageResponseBody.class);
        if (type != null && type.value()) {
            return true;
        }
        // 方法含有PackageResponseBody并且value为true
        PackageResponseBody method = returnType.getMethodAnnotation(PackageResponseBody.class);
        return method != null && method.value();
    }
}