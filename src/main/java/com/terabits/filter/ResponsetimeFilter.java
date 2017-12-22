package com.terabits.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet Filter implementation class ResponsetimeFilter
 */
public class ResponsetimeFilter implements Filter {
	static int count=0;
	/**
	 * Default constructor. 
	 */
	public ResponsetimeFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("ResponsetimeFilter.destroy()");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		PrintWriter out=response.getWriter();  
		long before=System.currentTimeMillis();  

		chain.doFilter(request,response);  

		long after=System.currentTimeMillis();  
		out.print("<br/>Total response time "+(after-before)+" miliseconds");  
		out.close();  
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
