package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemberRepositoryTests {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    } // Test가 끝나면 저장소를 다 지워줌

    @Test
    public void save(){
        Member member = new Member();

        repository.save((member));

        Member result = repository.findById(member.getId()).get();
        assertEquals(member, result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName(("spring1"));
        repository.save(member1);

        Member member2 = new Member();
        member2.setName(("spring2"));
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertEquals(member1, result);

    }

    @Test
    public void findAll(){

        Member member1 = new Member();
        member1.setName(("spring1"));
        repository.save(member1);

        Member member2 = new Member();
        member2.setName(("spring2"));
        repository.save(member2);

        List<Member> result = repository.findAll();

       // assertThat(result.size()).isEqualTo(2);
        assertEquals(2, result.size());

    }
}
