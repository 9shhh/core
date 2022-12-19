package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 해당 어노테이션을 통해 동일한 구현체를 가지는 빈 중에 우선권을 갖게되고 해당 객체의 빈이 주입되게 됨.
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {

        return member.getGrade() == Grade.VIP ? price * discountPercent / 100 : 0;
    }
}
