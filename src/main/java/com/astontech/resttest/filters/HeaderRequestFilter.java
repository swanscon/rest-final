package com.astontech.resttest.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(1)
@Slf4j
public class HeaderRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("Starting transaction for resource: " + request.getRequestURI());
        request.getHeaderNames()
                .asIterator()
                .forEachRemaining(header -> {
                    log.info("Header: " + header + ":" + request.getHeader(header));
                });
        filterChain.doFilter(request, response);
    }
}
