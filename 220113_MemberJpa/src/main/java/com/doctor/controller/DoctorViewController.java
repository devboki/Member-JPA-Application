package com.doctor.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.doctor.Service.DoctorService;
import com.doctor.domain.DoctorDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DoctorViewController {

	private final DoctorService doctorService;
	
	/* 의사 전체 조회 */
	@GetMapping("/doctor/list")
	public String list(Model model) {
		List<DoctorDto> doctors = doctorService.findAllDoctorDto();
		model.addAttribute("doctors", doctors);
		return "doctors/doctorList";
	}
	
	/* 회원 가입 폼 */
	@GetMapping("/doctor/join")
	public String joinForm(Model model) {
		new DoctorDto();
		model.addAttribute("joinForm", DoctorDto.DoctorDtoBuilder().build());
		return "doctors/joinForm";
	}
	
	/* 의사 조회 폼 */
	@GetMapping("/doctor/search")
	public String searchForm() {
		return "doctors/searchForm";
	}
}
