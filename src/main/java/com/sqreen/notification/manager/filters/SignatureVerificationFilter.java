package com.sqreen.notification.manager.filters;

import com.sqreen.notification.manager.validators.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@Order(1)
public class SignatureVerificationFilter implements Filter {

    private static final String SIGNATURE_HEADER = "X-Sqreen-Integrity";

    @Autowired
    private RequestValidator requestValidator;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Signature Verification");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (Objects.nonNull(request.getHeader(SIGNATURE_HEADER))
                && requestValidator.checkSignature(request.getHeader(SIGNATURE_HEADER))) {

            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.info("Invalid Signature");
            HttpServletResponse response = (HttpServletResponse) servletResponse;

            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

    }

    @Override
    public void destroy() {}

}
