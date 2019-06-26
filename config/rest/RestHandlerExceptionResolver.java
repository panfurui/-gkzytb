package com.demo.config.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@Component
@ConditionalOnWebApplication
public class RestHandlerExceptionResolver implements HandlerExceptionResolver {
    private final static Logger log = LoggerFactory.getLogger(RestHandlerExceptionResolver.class);

    public static String printStackTraceToString(Throwable t) {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw, true));
        return sw.getBuffer().toString();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o,
                                         Exception e) {
        RestException re;

        if (e instanceof RestException) {
            re = (RestException) e;
        } else {
            re = new RestException(RestErrorEnum.UNKNOWN, printStackTraceToString(e));
        }
        log.error("Exception catches:", re);

        httpServletResponse.setCharacterEncoding("UTF-8");

        try {
            httpServletResponse.setContentType("application/json");

            ObjectMapper mapper = new ObjectMapper();

            PrintWriter pw = httpServletResponse.getWriter();

            pw.print(mapper.writeValueAsString(new RestResponse<>(re.getCode(), re.getResult(), re.getDescription())));

            pw.close();

        } catch (IOException e1) {
            log.error("Exception catches:", e1);
        }

        return new ModelAndView();
    }
}
