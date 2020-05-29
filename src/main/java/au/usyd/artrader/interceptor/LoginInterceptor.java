package au.usyd.artrader.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    public static final String LOGIN = "login";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession httpSession = request.getSession();

        Object user = httpSession.getAttribute(LOGIN);

        if(!ObjectUtils.isEmpty(user)) {
            logger.info("clear login data: " + user.toString());
            httpSession.removeAttribute(LOGIN);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession httpSession = request.getSession();
        ModelMap modelMap = modelAndView.getModelMap();
        Object user = modelMap.get("user");

        if(!ObjectUtils.isEmpty(user)) {
            logger.info("user login: " + user.toString());
            httpSession.setAttribute(LOGIN, user);
            response.sendRedirect("/");
        }
    }
}
