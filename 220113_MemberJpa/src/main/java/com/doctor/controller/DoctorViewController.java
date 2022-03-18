package com.doctor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.doctor.domain.CheckBox;
import com.doctor.domain.Doctor;
import com.doctor.domain.DoctorDto;
import com.doctor.domain.DoctorForm;
import com.doctor.domain.MemberDto;
import com.doctor.domain.MemberSearchForm;
import com.doctor.domain.ResultFindMember;
import com.doctor.domain.SearchForm;
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
		doctorService.deleteList(box.getChecked());
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
	public String searchForm(Model model) {
		model.addAttribute("searchForm", new SearchForm());
		return "doctors/searchForm";
	}
	
	/* 의사 조회 */
	@PostMapping("/doctor/search")
	public String searchDoctor(SearchForm form, DoctorDto dto, Model model) {

		List<DoctorDto> doctorInfo = doctorService.findDoctorDto(form.getId());
			
		if(doctorInfo.isEmpty()) {
			model.addAttribute("errorMessage", "입력한 ID의 의사 정보가 없습니다.");
			return "doctors/searchForm";
		}
		model.addAttribute("doctorInfo", doctorInfo);

		return "doctors/doctorInfo";
	}
	
	/* 환자 조회 폼 */
	@GetMapping("/doctor/findMember")
	public String findMemberForm(Model model) {
		model.addAttribute("memberSearchForm", new MemberSearchForm());
		return "doctors/findMemberForm";
	}
	
	/* 환자 조회 */
	@PostMapping("/doctor/findMember")
	public String searchMember(MemberSearchForm form, ResultFindMember result, Model model) {

		List<ResultFindMember> members = doctorService.findMemberResult(form.getId(), form.getPassword());
		
		if(members.isEmpty()) {
			model.addAttribute("errorMessage", "매칭된 환자 정보가 없습니다.");
			return "doctors/findMemberForm";
		}
		model.addAttribute("members", members);

		return "doctors/memberInfo";
	}
	
	/* 사업자등록 상태조회 폼 */
	@GetMapping("/checkForm")
	public String bNoCheckForm() {
		return "doctors/checkForm";
	}
}