package com.springmvctutorial.springboot_springmvc_first_app;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;


public class CustomInterceptor implements HandlerInterceptor {


        // Called before the controller is invoked
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            System.out.println("CustomInterceptor: preHandle called");
            // Return true to proceed to the controller; false to stop processing
            return true;
        }

        // Called after the controller has returned a response but before the view is rendered
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                               org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
            System.out.println("CustomInterceptor: postHandle called");
        }

        // Called after the response has been completely rendered
        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
                throws Exception {
            System.out.println("CustomInterceptor: afterCompletion called");
        }
    }


