package au.usyd.artrader.util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = Controller.class)
public class GlobalControllerAdvice extends RuntimeException {

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Model model, Exception e) {
        String errorMessage = e.getMessage();
        if(CommonUtil.isEmpty(errorMessage)) {
            errorMessage = e.getClass().toString();
        }
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}
