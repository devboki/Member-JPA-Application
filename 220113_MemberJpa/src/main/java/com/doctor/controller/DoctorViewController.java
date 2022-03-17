package com.doctor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.doctor.domain.CheckBox;
import com.doctor.domain.Doctor;
import com.doctor.domain.DoctorDto;
import com.doctor.domain.DoctorForm;
import com.doctor.service.DoctorService;

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
	
	/* 의사 삭제 체크 */
	@GetMapping("/doctor/delete")
	public String checking(Model model) {
		model.addAttribute("checkBox", new CheckBox());
		return "doctors/doctorList";
	}
	
	/* 의사 삭제 */
	@PostMapping("/doctor/delete")
	public String delete(CheckBox box) {
		if(box.equals(null)) {
			return "redirect:/";
		}
		doctorService.delete(box.getCheckId());
		return "redirect:/";
	}
	
	/* 회원 가입 폼 */
	@GetMapping("/doctor/new")
	public String joinForm(Model model) {
		model.addAttribute("doctorForm", new DoctorForm());
		return "doctors/joinForm";
	}
	
	/* 회원 가입 */
	@PostMapping("/doctor/new")
	public String joinDoctor(@Valid DoctorForm form, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "doctors/joinForm";
		}
		
		try {
			Doctor doctor = Doctor.builder()
					.id(form.getId())
					.password(form.getPassword())
					.phoneNumber(form.getPhoneNumber())
					.buisnessNumber(form.getBuisnessNumber())
					.build();
			doctorService.checkDoctorIdDuplication(doctor);
			doctorService.join(doctor);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "doctors/joinForm";
		}
		
		return "redirect:/";
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