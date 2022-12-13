package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    // 확정되지 않은 데이터베이스 때문에 개발 진행을 위한 임시 메모리 저장소. (store)
    // 현재 실습 예제는 HashMap을 사용했는데, 동시성 이슈 때문에 실무에서는 ConcurrentHashMap<K,V> 을 사용하자
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
