package testjpa.member.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import testjpa.member.Service.DoctorService;
import testjpa.member.domain.DoctorDto;
import testjpa.member.domain.MemberDto;

@RestController
@RequiredArgsConstructor
public class DoctorController {
	
	private final DoctorService doctorService;

	@GetMapping("/doctors")
	public List<DoctorDto> findAllDoctorDto(){
		return doctorService.findAllDoctorDto();
	}
	
	@GetMapping("/doctor/members/{id}")
	public List<MemberDto> findDoctorMembers(@PathVariable("id") String doctorId){
		return doctorService.findDoctorMember(doctorId);
	}
}