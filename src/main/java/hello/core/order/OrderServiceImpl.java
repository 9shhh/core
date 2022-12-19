package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy;

    @Autowired private DiscountPolicy rateDiscountPolicy; // 필드 주입 시 필드명을 특정 빈의 이름으로 지정하면 됨.

     @Autowired
    // 해당 어노테이션을 통해 여러 의존관계도 자동으로 주입 받을 수 있음.
    // 생성자에 파라미터가 많아도 다 알아서 찾아 주입해줌.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy; // 파라미터명을 주입 받을 특정 빈의 이름으로 지정하면 됨.
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
