//회원 서비스 개발
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional // JPA를 쓸 때 항상 있어야함(데이터 저장, 변경_회원가입시에만 필요)
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 가입 불가가
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId(); //아이디 반환

        //Optional<Member> result = memberRepository.findByName(member.getName());
        // result.ifPresent(m ->{
        //    throw new IllegalStateException("이미 존재하는 회원입니다.");
        //});
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}