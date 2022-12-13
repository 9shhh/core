package hello.core.singletonTest;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberRepository1).isSameAs(memberRepository);
        assertThat(memberRepository2).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class); // 인자로 넘긴 AppConfig.class 도 빈으로 등록됨.
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean); // bean = hello.core.AppConfig$$EnhancerBySpringCGLIB$$ad8aea39@7f6f61c8
        // AppConfig.class 에 정의된 @Configuration 로 인해 바이트코드를 조작하는 CGLIB 기술을 통해 싱글톤을 보장 받게 됨.
        // 만약, @Configuration 을 제거하고 실행하면, @bean 이 붙은 메소드에 값들이 빈으로 등록이 됨. 하지만, 순수 자바 코드로 생성되고 등록되어 싱글톤을 보장 받지 못함.
    }
}
