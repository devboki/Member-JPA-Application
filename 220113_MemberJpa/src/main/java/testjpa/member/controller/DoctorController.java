package testjpa.member.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import testjpa.member.Service.DoctorService;
import testjpa.member.domain.DoctorDto;

@RestController
@RequiredArgsConstructor
public class DoctorController {
	
	private final DoctorService doctorService;

	@GetMapping("/doctors")
	public List<DoctorDto> getDoctors(){
		return doctorService.getAllDoctors();
	}
}
