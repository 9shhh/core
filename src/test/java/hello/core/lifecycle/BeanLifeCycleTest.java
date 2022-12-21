package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        // 빈으로 등록될 객체 내부에서 개발자가 원하는 이름으로 초기화, 소멸(종료) 메소드를 정의하고 @Bean 의 initMethod, destroyMethod 로 정의한 메소드를 지정함.
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
    /**
     * 기존 초기화, 소멸(종료) 메소드를 인터페이스를 구현하는 방식에서 설정 정보를 사용해 지정하는 방법으로 변경함.
     * 해당 방법의 특징으로는
     * 1. 초기화, 소멸(종료) 메소드의 이름을 자유롭게 줄 수 있음.
     * 2. 스프링 빈이 스프링 코드에 의존하지 않음.
     * 3. 코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 소멸(종료) 메소드를 적용할 수 있음.
     *
     * 추가로, @Bean 의 destroyMethod 속성은 특별한 기능이 있음.
     * 라이브러리는 대부분 close, shutdown 과 같은 이름의 종료 메소드를 사용함.
     * Bean 의 destroyMethod 속성은 메소드 명을 지정하지 않으면 기본값이 (inferred) (추론)으로 등록되어 있음.
     * 이 추론 기능은 close, shutdown 이라는 이름의 메소드를 자동으로 호출해줌. 이름 그대로의 종료 메소드를 추론해서 호출해줌.
     * 따라서, 직접 스프링 빈으로 등록하면 종료 메소드는 따로 적어주지 않아도 잘 동작함.
     * 만약 해당 기능을 사용하기 싫으면, destroyMethod="" 처럼 공백을 지정하면 됨.
     */
}
