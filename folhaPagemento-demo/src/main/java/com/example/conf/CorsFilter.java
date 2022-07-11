package com.example.conf;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;

	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    response.setHeader("Access-Control-Allow-Methods", "*");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, Authorization,Cache-Control, Content-Type");

	    if (request.getMethod().equals("OPTIONS")) {      	
	    	response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, Authorization");
	    	response.setHeader("Access-Control-Allow-Methods","GET, POST, GET, PUT, DELETE, HEAD, OPTIONS");
	    	response.setHeader("Access-Control-Max-Age","3600");        	
        	
	    	response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
	    chain.doFilter(req, res);
		
	}
	@Override	
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}

	

}
