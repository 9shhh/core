package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)

/**
 * proxyMode = ScopedProxyMode.TARGET_CLASS 이라는 속성을 통해 CGLIB 라이브러리로 현재 클래스를 상속 받은 가짜 프록시 객체를 만들어 주입함.
 * ScopedProxyMode.TARGET_CLASS 는 클래스, ScopedProxyMode.INTERFACE 는 인터페이스를 지원함. (맞는 형태로 사용하면 됨.)
 * 실제 요청이 오면 그때 내부에서 실제 빈을 요청하는 위임 로직이 들어있음. (가짜 프록시 객체에.)
 * 가짜 프록시 객체는 실제 request scope 와는 관계 없음. 그냥 가짜이고, 내부의 단순한 위임 로직만 있고 싱글톤 처럼 동작함.
 * 해당 기능은 싱글톤 같지만 내부에서는 요청마다 전용 스코프가 생성되는 것을 주의해야함.
 * 이런 특별한 스코프는 꼭 필요한 곳에서 최소화 해서 사용해야함. -> 무분별하게 사용하면 유지보수가 어려워짐.
 *
 */
public class MyLogger { // 로그 출력을 위한 클래스

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]" + "request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "]" + "request scope bean close:" + this);
    }
}
