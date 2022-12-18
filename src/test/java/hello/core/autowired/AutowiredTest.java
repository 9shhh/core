package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        @Autowired(required = false) // required = false 설정으로 의존관계가 없으면 수정자 메소드 자체가 호출되지 않음.
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1 );
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) { // @Nullable 속성으로 호출은 되나 등록된 빈이 없을 경우 => null.
            System.out.println("noBean2 = " + noBean2 );
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) { // Optional<T> 로 등록된 빈이 없을 경우 => Optional.empty.
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
