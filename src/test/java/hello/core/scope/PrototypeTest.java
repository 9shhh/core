package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy () {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
/**
 * 싱글톤 빈은 스프링 컨테이너 생성 시점에 초기화 메소드가 실행 되지만, 프로토타입 스코프의 빈은 스프링 컨테이너에서 빈을 조회(요청)할 때 생성되고, 초기화 메소드도 실행됨.
 * 싱글톤 빈은 스프링 컨테이너가 관리하므로 스프링 컨테이너 종료 시 종료 메소드가 실행되지만, 프로토타입 빈은 스프링 컨테이너가 생성과 의존관계 주입, 초기화 까지만 관여하고,
 * 더는 관리하지 않아 스프링 컨테이너가 종료될 때 @ProDestroy 메소드가 전혀 실행되지 않음.
 * 프로토타입 빈은 해당 빈을 조회한 클라이언트가 관리해야함. -> 종료 메소드에 대한 호출도 클라이언트가 직접 해야함.
 */
