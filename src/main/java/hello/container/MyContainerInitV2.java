package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

/**
 * 서블릿 컨테이너 초기화(#2)
 */
@HandlesTypes(AppInit.class)
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("MyContainerInitV2 c = " + c); //@HandlesTypes(인터페이스)의 인터페이스 정보의 구현체 정보를 c에 저장!
        System.out.println("MyContainerInitV2 ctx = " + ctx);

        //c = class hello.container.AppInitV1Servlet
        for (Class<?> appInitClass : c) {
            try {
                //new AppInitV1Servlet()과 같은 코드
                AppInit appInit = (AppInit) appInitClass.getDeclaredConstructor().newInstance(); // 객체 생성하고
                appInit.onStartup(ctx); // AppInit 구현체 실행
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
