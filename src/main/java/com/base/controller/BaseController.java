package com.base.controller;

import com.entity.Member;
import com.utils.CommConstant;
import com.utils.JSONUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController
{
  protected HttpServletRequest request;
  protected HttpServletResponse response;
  protected HttpSession session;

  /**
  * @param request
  * @param response
  * 不推荐使用该方法，会有线程安全问题
  */
  @ModelAttribute
  @Deprecated
  public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
    this.request = request;
    this.response = response;
    this.session = request.getSession();
  }
  protected String success() {
    return JSONUtils.success();
  }
  protected String setErrorMsg(String msg) {
    return JSONUtils.setErrorMsg(msg);
  }

  protected Member getLoginMember()
  {
    return (Member)this.session.getAttribute(CommConstant.LOGIN_MEMBER);
  }

  protected String getLoginMemberId()
  {
    Object tid = this.session.getAttribute(CommConstant.LOGIN_MEMBER_ID_SESSION_KEY);
    return tid == null ? null : tid.toString();
  }

  protected String getLoginUserId()
  {
    Object tid = this.session.getAttribute("");
    return tid == null ? null : tid.toString();
  }

  protected void toErrorPage(String code, String msg)
  {
    try
    {
      if (isMobileBrowser()) {
        if (StringUtils.isNotBlank(code)) {
          if ("404".equals(code))
            this.request.getRequestDispatcher("/error/error404_mobile.jsp").forward(this.request, this.response);
          else if ("500".equals(code))
            this.request.getRequestDispatcher("/error/error500_mobile.jsp").forward(this.request, this.response);
        }
        else {
          this.request.setAttribute("errorMsg", msg);
          this.request.getRequestDispatcher("/error/error_mobile.jsp").forward(this.request, this.response);
        }
      } else {
        if (StringUtils.isNotBlank(code)) {
          if ("404".equals(code))
            this.request.setAttribute("errorMsg", "很抱歉，您访问的页面暂时没有找到");
          else if ("500".equals(code))
            this.request.setAttribute("errorMsg", "很抱歉，网站发生了错误，请返回首页后重试");
          else this.request.setAttribute("errorMsg", msg);
        }
        this.request.getRequestDispatcher("/error/error.jsp").forward(this.request, this.response);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private boolean isMobileBrowser()
  {
    String userAgent = this.request.getHeader("user-agent");
    if (StringUtils.isNotBlank(userAgent)) {
      int isMObrowser = userAgent.indexOf("Mobile");
      return isMObrowser >= 0;
    }
    return false;
  }

  protected void toResultPage(String title, String msg, Boolean state)
  {
    try
    {
      if (StringUtils.isBlank(title))
        title = state.booleanValue() ? "提交成功" : state == null ? "提示信息" : "提交失败";
      this.request.setAttribute("title", title);
      this.request.setAttribute("msg", msg);
      this.request.setAttribute("state", state);
      this.request.getRequestDispatcher("/staticPage/submitResult.jsp").forward(this.request, this.response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}