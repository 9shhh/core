package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 해당 어노테이션을 통해 동일한 구현체를 가지는 빈 중에 우선권을 갖게되고 해당 객체의 빈이 주입되게 됨.
// 추가 정보 => @Primary 는 기본값 처럼 동작, @Qualifier 는 매우 상세하게 동작함. 이런 경우 스프링은 자동 보다는 수동이 더 높은 우선순위를 가지게 되어
// @Qualifier 가 우선권이 높음.
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {

        return member.getGrade() == Grade.VIP ? price * discountPercent / 100 : 0;
    }
}
