package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Stream;

@Slf4j
@Repository
public class MemberRepository {
    private static final Map<Long, Member> repository = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member){
        member.setId(++sequence);
        repository.put(member.getId(), member);
        return findById(member.getId());
    }

    public Member findById(Long id){
        return repository.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream().filter(member -> member.getLoginId().equals(loginId)).findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(repository.values());
    }

    public void clearStore() {
        repository.clear();
    }
}
