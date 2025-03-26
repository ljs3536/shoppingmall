package com.hertz.shoppingMall.utils.exception.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        //오류 상태 코드 가져오기
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            int statusCode = Integer.parseInt(status.toString());

            //오류 메시지 설정
            if(statusCode == HttpStatus.NOT_FOUND.value()){
                model.addAttribute("code", "404");
                model.addAttribute("message", "페이지를 찾을 수 없습니다.");
            }else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                model.addAttribute("code", "500");
                model.addAttribute("message", "서버 내부 오류가 발생했습니다.");
            } else{
                model.addAttribute("code", statusCode);
                model.addAttribute("message", "예상치 못한 오류가 발생했습니다.");
            }

            model.addAttribute("errorTrace", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
        }

        return "error";
    }
}
