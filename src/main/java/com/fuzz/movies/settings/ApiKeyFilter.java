package com.fuzz.movies.settings;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//@Component
@Order(1)
@WebFilter("/*")
public class ApiKeyFilter implements Filter {

    //TODO: Disable
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Collections.singletonList("/apikey")));

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String apiKey = request.getParameter("apiKey");
        String path = req.getRequestURI().substring(req.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        System.out.println(path);
        if(apiKey.equals("true") || allowedPath)
            chain.doFilter(request, response);
        else
            res.sendError(401);
    }

}
