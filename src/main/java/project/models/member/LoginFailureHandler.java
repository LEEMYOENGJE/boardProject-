package project.models.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import project.commons.Utils;

import java.io.IOException;

public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        HttpSession session = request.getSession();

        Utils.loginInit(session);

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean isRequiredFieldCheck = false;

        session.setAttribute("email", email);

        /* 필수 항목 검증 - email, password */
        if (email == null || email.isBlank()) {
            session.setAttribute("NotBlank_email", Utils.getMessage("NotBlank.email", "validation"));
        }

        if (password == null || password.isBlank()) {
            session.setAttribute("NotBlank_password", Utils.getMessage("NotBlank.password", "validation"));
        }
        /* 필수 항목 검증 - email, password E */
        
        if (!isRequiredFieldCheck) { // 아이디가 없거나 비밀번호가 잘못된 경우
            session.setAttribute("globalError", Utils.getMessage("Login.fail", "validation"));
        }

        response.sendRedirect(request.getContextPath() + "/member/login");
    }
}
