package com.doctor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.Service.MemberService;
import com.doctor.domain.Member;
import com.doctor.domain.MemberDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/members/new")
	public String joinForm() {
		return "members/joinForm";
	}
	
	@PostMapping("/members/new")
	public String create(@Valid MemberDto dto) {
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList.html";
	}
}
