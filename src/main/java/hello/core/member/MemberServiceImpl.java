package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 침고: MemberServiceImpl 에서 뒤에 붙은 Impl 은 구현체가 하나만 있을 때 관례상 많이 사용되는 규칙.
@Component
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    // 해당 어노테이션을 생성자에 붙여주면 스프링이 MemberRepository 타입에 맞는 것을 찾아 의존관계를 자동으로 연결해서 주입해줌. -> 현재는 MemoryMemberRepository 가 주입됨.
    // 자동으로 ac.getBean(MemberRepository.class) 가 들어간다고 생각하면됨.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
