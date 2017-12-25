package com.base.interceptor;

import com.base.controller.BaseController;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class IsRepeatDataInterceptor extends BaseController
  implements AsyncHandlerInterceptor
{
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception
  {
    handleBasePath(request);

    if ((handler instanceof HandlerMethod)) {
      HandlerMethod handlerMethod = (HandlerMethod)handler;
      Method method = handlerMethod.getMethod();
      IsRepeatData annotation = (IsRepeatData)method.getAnnotation(IsRepeatData.class);
      if (annotation != null) {
        if (repeatDataValidator(request)) {
          String header = request.getHeader("X-Requested-With");
          boolean isAjax = "XMLHttpRequest".equals(header);
          if (isAjax) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("请不要重复提交");
            return false;
          }
          this.request = request;
          this.response = response;
          super.toErrorPage(null, "请不要重复提交！");
          return false;
        }

        return true;
      }

      request.getSession().removeAttribute("repeatData");

      return true;
    }
    return true;
  }

  public boolean repeatDataValidator(HttpServletRequest httpServletRequest)
  {
    String params = JSONObject.fromObject(httpServletRequest.getParameterMap()).toString();
    String url = httpServletRequest.getRequestURI();
    Map map = new HashMap();
    map.put(url, params);
    String nowUrlParams = map.toString();

    Object preUrlParams = httpServletRequest.getSession().getAttribute("repeatData");
    if (preUrlParams == null)
    {
      httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
      return false;
    }

    if (preUrlParams.toString().equals(nowUrlParams))
    {
      return true;
    }

    httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
    return false;
  }

  private void handleBasePath(HttpServletRequest request)
  {
    Object o = request.getServletContext().getAttribute("REQUEST_BASE_PATH");
    if (o == null) {
      String path = request.getContextPath();

      request.getServletContext().setAttribute("REQUEST_BASE_PATH", path + "/");
    }
  }

  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
    throws Exception
  {
  }

  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    throws Exception
  {
  }

  public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception
  {
  }
}