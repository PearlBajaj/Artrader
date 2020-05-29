package au.usyd.artrader.controller;

import au.usyd.artrader.domain.User;
import au.usyd.artrader.interceptor.LoginInterceptor;
import au.usyd.artrader.service.UserService;
import au.usyd.artrader.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/user/**")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login")
    public String getLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "loginPost", method = RequestMethod.POST)
    public String login(@RequestParam String email,
                              @RequestParam String password,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        User user = userService.getUser(email);

        if(ObjectUtils.isEmpty(user) || !password.equals(user.getPassword())) {
            String errorMessage = "Please, check your email or password.";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:login";
        }

        model.addAttribute("user", user);

        return "home";
    }

    @RequestMapping(value = "signup")
    public String getSignUpPage(Model model) {
        if(!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "signup";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String signUp(User user, RedirectAttributes redirectAttributes) {

        if(!ObjectUtils.isEmpty(userService.getUser(user.getEmail()))) {
            String errorMessage = "The email address(" + user.getEmail() + ") already exists. Please try to use another email.";
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:signup";
        }

        userService.signUp(user);

        return "redirect:login";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute(LoginInterceptor.LOGIN);
        httpSession.invalidate();
        return "redirect:login";
    }

    @RequestMapping(value = "edit")
    public String getUserEditPage(Model model, HttpSession httpSession) {
        long userId = CommonUtil.getLoginUserId(httpSession);
        model.addAttribute("user", userService.getUser(userId));

        return "user/userEdit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editUserProfile(User user) {
        userService.editUser(user);

        return "redirect:edit";
    }
}
