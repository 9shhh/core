package hello.core.member;

// 침고: MemberServiceImpl 에서 뒤에 붙은 Impl 은 구현체가 하나만 있을 때 관례상 많이 사용되는 규칙.
public class MemberServiceImpl implements MemberService {

    // 해당 클래스는 현재 추상화, 구체화 둘다 의존하고 있음. => DIP 위반.

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
