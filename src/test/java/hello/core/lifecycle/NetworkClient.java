package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 가짜 네트워크 클라이언트
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        System.out.println("수정자 호출, url = " + url);
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + ", message = " + message);
    }

    //서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close = " + url);
    }

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
/**
 * @PostConstruct, @PreDestroy 어노테이션을 사용한 초기화, 소멸(종료)를 사용.
 * 최신 스프링에서 가장 권장하는 방법임.
 * 어노테이션만 초기화, 소멸(종료) 메소드에 정의하면 되서 매우 편리함.
 * import 한 패키지를 보면 javax.annotation.PostConstruct, javax.annotation.PreDestroy 로
 * 스프링의 종속적인 기술이 아니라 JSR-250 이라는 자바 표준 기술이므로, 스프링이 아닌 다른 컨테이너에서도 동작함.
 * 컴포넌트 스캔과 잘 어울림.
 * 단점은, 외부 라이브러리에는 적용이 불가능함.
 * 따라서 외부 라이브러리에 초기화, 소멸(종료) 가 필요하다면 @Bean 의 initMethod, destroyMethod 속성을 사용하자.
 */
