package com.terabits.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.terabits.domain.User;

/** 
* @author 作者Vladimir E-mail: gyang.shines@gmail.com
* @version 创建时间：2017年12月18日 下午5:03:34 
* 类说明 
*/
public class AuthorizedInceterceptor implements HandlerInterceptor {

	/** 定义不需要拦截的请求 */
	private static final String[] IGNORE_URI = {"/loginForm", "/login","/404.html"};
	
	
	 /** 
     * preHandle方法是进行处理器拦截用的，该方法将在Controller处理之前进行调用，
     * 当preHandle的返回值为false的时候整个请求就结束了。 
     * 如果preHandle的返回值为true，则会继续执行postHandle和afterCompletion。
     */ 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		/** 默认用户没有登录 */
		boolean flag = false; 
		/** 获得请求的ServletPath */
		String servletPath = request.getServletPath();
		/**  判断请求是否需要拦截 */
        for (String s : IGNORE_URI) {
            if (servletPath.contains(s)) {
                flag = true;
                break;
            }
        }
        /** 拦截请求 */
        if (!flag){
        	/** 1.获取session中的用户  */
        	User user = (User) request.getSession().getAttribute("user_session");
        	/** 2.判断用户是否已经登录 */
        	if(user == null){
        		 /** 如果用户没有登录，跳转到登录页面 */
        		request.setAttribute("message", "请先登录再访问网站!");
        		request.getRequestDispatcher("loginForm").forward(request, response);
        		return flag;
        	}else{
        		 flag = true;
        	}
        }
        return flag;
	}
	
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
//        String token = request.getParameter("token");
//        UserTokenBO userTokenBO= JWT.unsign(token, UserTokenBO.class);
//
//        String authorization = request.getParameter("Authorization");
//        AdminPO adminPO = JWT.unsign(authorization, AdminPO.class);
//        
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("status", 0);
//        if (userTokenBO == null && adminPO == null) {
//            response.getWriter().print(jsonObject);
//            return false;
//        }
//        return true;
//    }
	

	/** 
     * 这个方法在preHandle方法返回值为true的时候才会执行。
     * 执行时间是在处理器进行处理之 后，也就是在Controller的方法调用之后执行。
     */ 
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	/** 
     * 该方法需要preHandle方法的返回值为true时才会执行。
     * 该方法将在整个请求完成之后执行，主要作用是用于清理资源。
     */  
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
