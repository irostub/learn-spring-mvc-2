package irostub.login.web.filter;

import irostub.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private final String[] whitelist = {"/", "/member/add", "/login", "/logout", "/css/*"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init login check filter");
    }

    @Override
    public void destroy() {
        log.info("login check filter destroy");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestUri = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작 {}", requestUri);

            if (isLoginCheckPath(requestUri)) {
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestUri);
                    httpResponse.sendRedirect("/login?redirectUrl=" + requestUri);
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }finally {
            log.info("인증 체크 필터 종료 {}", requestUri);
        }
    }

    private boolean isLoginCheckPath(String requestUri) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestUri);
    }
}
