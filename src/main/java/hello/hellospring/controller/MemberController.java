package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new") //데이터 조회
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new") //입력받아 전달(데이터 전달)
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName()); //getName : 꺼내기

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers(); //member 모두 불러오기
        model.addAttribute("members", members);
        return "members/memberList";
    }
}

//http 기능: gettag, getform 알아보기