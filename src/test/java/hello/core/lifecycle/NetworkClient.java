package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

// 가짜 네트워크 클라이언트
public class NetworkClient implements InitializingBean, DisposableBean {

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

    @Override
    // 스프링 의존관계 주입이 끝나면 호출돰.
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}

/**
 * InitializingBean, DisposableBean 인터페이스를 통한 초기화, 소멸 작업의 단점.
 * 1. 이 인터페이스는 스프림 전용 인터페이스 임. 해당 코드가 스프링 전용 인터페이스에 의존함.
 * 2. 초기화, 소멸 메소드의 이름을 변경할 수 없음.
 * 3. 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없음.
 *
 * 참고로 인터페이스를 사용하는 초기화, 소멸(종료) 방법은 스프링 초창기에 나온 방법들이고,
 * 지금은 더 나은 방법들이 존재하여 거의 사용되지 않음.
 */
