package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 아래 DiscountPolicy 에 대한 부분은 DIP는 잘 지킴 => 인터페이스를 의존하게 함.
     * 하지만, 구현체(실제 기능을 하는 동작 객체)가 없음.
     * TODO: 구현체 클래스를 생성하고 주입하라.
     */
    private DiscountPolicy discountPolicy;


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
