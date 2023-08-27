package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitV2Spring implements AppInit {
    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("AppInitV2Spring.onStartup");

        //스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class); //HelloController() 등록

        //스프링 MVC 디스패처 서블릿 생성, 스프링 컨테이너 연결 (이름 주의! "dispatcherV2")
        DispatcherServlet dispatcher = new DispatcherServlet(appContext);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherV2", dispatcher);

        // "/spring/*" 요청이 디스패처 서블릿을 통하도록 설정
        servlet.addMapping("/spring/*");

        /**
         * 주소 찾기
         * dispatcherV2 서블릿 실행 : '/spring'
         * dispatcherV2 서블릿이 컨트롤러 찾아서 실행 : '/hello-spring'
         */
    }
}
