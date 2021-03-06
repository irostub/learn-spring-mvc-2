package irostub.login;

import irostub.login.domain.item.Item;
import irostub.login.domain.item.ItemRepository;
import irostub.login.domain.member.Member;
import irostub.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));

        memberRepository.save(new Member("iro", "stub", "0000!"));
        memberRepository.save(new Member("ho", "diph", "0000!"));
    }

}