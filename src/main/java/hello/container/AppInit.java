package hello.container;

import jakarta.servlet.ServletContext;

/**
 * 애플리케이션 초기화를 위해선 인터페이스 필요!
 * 서블릿 컨테이너 그 자체 (필터 등록, 서블릿 등록 등등)
 */
public interface AppInit {
    void onStartup(ServletContext servletContext);
}
