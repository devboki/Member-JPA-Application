package testjpa.member.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import testjpa.member.Service.MemberService;
import testjpa.member.domain.Member;
import testjpa.member.domain.MemberDto;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

	@GetMapping("/")
	public String Home() {
		return "home";
	}
	
	@GetMapping("/members/new")
	public String joinForm(Model model) {
		model.addAttribute("joinForm", new MemberDto());
		return "members/joinForm";
	}
	
	@PostMapping("/members/new")
	public String create(@Valid MemberDto dto) {

		Member member = new Member();
		member.setName(dto.getName());
		member.setAge(dto.getAge());
		member.setGender(dto.getGender());
		member.setEmail(dto.getEmail());
		
		memberService.join(member);
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
}
