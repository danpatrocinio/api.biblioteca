package com.bairro.biblioteca.filters;

import java.io.IOException;
import java.util.TimeZone;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "TimeZoneFilter", urlPatterns = { "/rest/*" })
public class TimeZoneFilter implements Filter {

	@Override
	public void destroy() {
	}

	// Filtro para configurar o TimeZone da api.
	// Todas as requisicoes que passarem por "/rest" vao passar por este metodo doFilter()
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {

		// configura o timezone na JVM do Java para todas as URLs a partir de /rest
		TimeZone.setDefault(TimeZone.getTimeZone("UTC-3"));

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}