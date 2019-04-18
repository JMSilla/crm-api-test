package com.jmsilla.crmapitest.api.config;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmsilla.crmapitest.api.utils.EnvUtils;

public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, 
			AuthenticationException arg2)
			throws IOException, ServletException 
	{
		final Map<String, Object> mapBodyException = new HashMap<>();

		mapBodyException.put("timestamp", LocalDateTime.now().toString());
		mapBodyException.put("status", HttpServletResponse.SC_UNAUTHORIZED);
		mapBodyException.put("error", "unauthorized");
		mapBodyException.put("message", 
				"Full authentication is required to access this resource");
		mapBodyException.put("path", EnvUtils.getContextPath() + request.getServletPath());

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), mapBodyException);
	}
}
