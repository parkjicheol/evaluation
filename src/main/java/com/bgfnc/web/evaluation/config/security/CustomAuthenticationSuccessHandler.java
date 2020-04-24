package com.bgfnc.web.evaluation.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        response.sendRedirect("/");

//        // 현재 사용자의 도메인 확인
//        UserVO userVO = (UserVO) SecurityContextHolder.getContext().getAuthentication().getDetails();
//
//        log.info("Welcome loginSuccess! {}, {}", request.getSession().getId(), userVO.getUsername() + "/" + userVO.getPassword());
//
//        String domain = request.getServerName();
//        String protocol = request.getScheme();
//        int port = request.getServerPort();
//
//        // 지정된 Url이 있다면 해당 Url로 이동
//        String targetUrl = (request.getParameter("targetUrl") == null) ? "" : request.getParameter("targetUrl");
//        String referer = request.getHeader("referer");
//        boolean isMobile = Boolean.valueOf(request.getParameter("isMobile"));
//        String loginId = userVO.getLoginId();
//
//        if (isMobile) {
//            targetUrl = (targetUrl.equals("")) ? "/" : targetUrl;
//        } else {
//
//            targetUrl = (targetUrl.equals("")) ? "/?login=1" : targetUrl;
//
//            //호출 referer 가 메가HRD이면 megahrd 로그인 불필요
//            if (referer != null && !referer.contains("www.mega")) {
//                String returnUrl = protocol + "://" + domain + ( (port == 80 ) ? "" : ":" + port) + targetUrl; // 테스트환경에서 포트 있는 경우 처리
//                //megahrd 로그인
//                targetUrl = protocol + "://" + serviceSite + "/sso/sso/void.sso.type8.user?sso.login_id=" + URLEncoder.encode(loginId.substring(4)) +"&sso.redirect_url=" + URLEncoder.encode(returnUrl);
//            }
//        }
//
//        String isAjax = request.getParameter("ajax");
//
//        // Session에 로그인 정보 저장
//        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(30 * 60);
//        session.setAttribute("SESSION_INFO", userVO);
//
//        response.setStatus(HttpServletResponse.SC_OK);
//
//        if ("Y".equals(isAjax)) {
//            OutputStream out = response.getOutputStream();
//            String resultStr = "{\"result\":\"success\", \"name\":\""+ userVO.getMemberName() +"\"}";
//            out.write(resultStr.getBytes());
//        } else {
//            response.sendRedirect(targetUrl);
//        }
    }
}
