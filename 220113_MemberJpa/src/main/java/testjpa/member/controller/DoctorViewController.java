package testjpa.member.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import testjpa.member.Service.DoctorService;
import testjpa.member.domain.Doctor;
import testjpa.member.domain.DoctorDto;

@Controller
@RequiredArgsConstructor
public class DoctorViewController {

	private final DoctorService doctorService;
	
	@GetMapping("/doctor/search")
	public String searchForm() {
		return "doctors/searchForm";
	}
	
//	@GetMapping("/doctor/join")
//	public String joinForm(Model model) {
//		model.addAttribute("joinForm", new DoctorDto());
//		return "doctors/joinForm";
//	}
//	
//	@PostMapping("/doctor/new")
//	public String join(@Valid DoctorDto dto) {
//		
//		Doctor doctor = Doctor.builder()
//				.id(dto.getId())
//				.password(dto.getPassword())
//				.phoneNumber(dto.getPhoneNumber())
//				.buisnessNumber(dto.getBuisnessNumber())
//				.build();
//		
//		doctorService.join(doctor);
//		return "redirect:/";
//	}
}
