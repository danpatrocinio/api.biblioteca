package com.bairro.biblioteca.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = { "/rest/*" })
public class CharsetFilter implements Filter {

	@Override
	public void destroy() {
	}

	// Filtro para configurar o charset da api.
	// Todas as requisicoes que passarem por "/rest" vao passar por este metodo doFilter()
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {

		// configura o charset para todas as URLs a partir de /rest
		String charset = "UTF-8";
		request.setCharacterEncoding(charset);
		response.setCharacterEncoding(charset);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}

}