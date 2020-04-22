package com.bgfnc.web.evaluation.controller;

import com.bgfnc.web.evaluation.common.abs.AbstractBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class IndexController extends AbstractBaseController<IndexController> {

    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, HttpSession session, Locale locale, Model model) {

        return "index";
    }

}
