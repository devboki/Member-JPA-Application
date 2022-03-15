package com.doctor.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.doctor.Service.DoctorService;
import com.doctor.domain.Doctor;
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
	@GetMapping("/doctor/new")
	public String joinForm() {
		return "doctors/joinForm";
	}
	
	/* 의사 조회 폼 */
	@GetMapping("/doctor/search")
	public String searchForm() {
		return "doctors/searchForm";
	}
	
	/* 환자 조회 폼 */
	@GetMapping("/doctor/findMember")
	public String findMemberForm() {
		return "doctors/findMemberForm";
	}
	
	/* 사업자등록 상태조회 폼 */
	@GetMapping("/checkForm")
	public String bNoCheckForm() {
		return "doctors/checkForm";
	}
}
