package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 순수 자바를 이용한 개발 검증 로직
public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /**
         * 스프링은 모든것이 Application Context 라는 것으로 시작됨. -> 이게 스프링 컨테이너 라고 보면됨.
         * 이게 스프링의 모든 객체들을 관리한다. -> AppConfig.java에 선언한 @Bean 들의 객체를 관리함.
         */

        // AppConfig.class 를 AnnotationConfigApplicationContext에 넣어 줌으로써 AppConfig의 선언된 빈들의 객체를 생성해서 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // ApplicationContext에 등록된 객체는 AppConfig에 정의된 메소드 명으로 등록이 된다. 따라서, 사용할 객체는 메소드 명으로 이름을 주고, 해당 객체의 타입을 정의한다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
