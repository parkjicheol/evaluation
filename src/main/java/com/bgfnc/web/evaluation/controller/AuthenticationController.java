package com.bgfnc.web.evaluation.controller;

import com.bgfnc.web.evaluation.common.abs.AbstractBaseController;
import com.bgfnc.web.evaluation.dto.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class AuthenticationController extends AbstractBaseController<AuthenticationController> {

    @GetMapping("/login")
    public String login(HttpSession session, Locale locale, Model model, @RequestParam(required = false) String error) {
        model.addAttribute("error", error);
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) {
        UserVo userVo = (UserVo) session.getAttribute("SESSION_INFO");
        log.info("Welcome logout! {}, {}", userVo.getId(), userVo.getName());
        session.invalidate();

        return "redirect:/login";
    }

}
