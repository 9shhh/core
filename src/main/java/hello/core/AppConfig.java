package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 해당 어노테이션으로 해당 클래스는 설정(구성) 정보로 스프링 컨테이너에서 사용된다.
public class AppConfig { // 애플리케이션의 실제 동작에 필요한 *구현 객체를 생성* 한다. (의존적인 객체의 생성과 연결을 담당함.)

    @Bean // 해당 어노테이션으로 해당 메소드는 현재 정의된 메소드 명으로 스프링 빈에 등록된다. @Bean(name="mmm") 형식으로 지정할 수 있지만 관례상 기본 설정을 따르는걸 권장함.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
         // 클라이언트 코드는 변경하지 않고 config(구성) 만 변경함으로써, 할인 정책을 변경함. => 변경 최소화.
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
