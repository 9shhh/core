package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        // AnnotationConfigApplicationContext 인자로 컴포넌트 클래스를 넘겨주면 자동으로 빈이 등록됨.
        // 현재 정의한 SingletonBean 클래스의 @Component 어노테이션이 없어도 해당 클래스를 인자로 지정해주면 스프링 빈으로 등록함.(컴포넌트 스캔 처럼 동작함.)
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);

        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    @Scope("singleton") // 디폴트가 singleton 이라 지정하지 않아도 되지만 학습을 위해 정의함.
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy () {
            System.out.println("SingletonBean.destroy");
        }
    }
}
