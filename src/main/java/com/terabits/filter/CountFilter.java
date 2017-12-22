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
 * @author 作者Vladimir E-mail: gyang.shines@gmail.com
 * @version 创建时间：2017年12月19日 下午9:08:42 
 * 类说明 
 */
public class CountFilter implements Filter {
	static int count=0;
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();  
		chain.doFilter(request,response);  

		out.print("<br/>Total visitors "+(++count));  
		out.close();

	}

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("CountFilter.destroy()");

	}

}
