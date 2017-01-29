package com.intive.samples.spring.mvc.samples;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleData {


    public static List<String> getSampleSpringClasses() {
        return new ArrayList<>(Arrays.asList("DispatcherServlet", "ViewResolver", "ExceptionHandler",
                "HandlerAdapter", "HandlerMapping", "RequestMappingHandlerMapping", "DefaultAnnotationHandlerMapping",
                "HandlerInterceptor", "HandlerInterceptorAdapter"));
    }
}
