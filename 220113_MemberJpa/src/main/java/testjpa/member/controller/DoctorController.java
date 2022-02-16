package testjpa.member.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import testjpa.member.Service.DoctorService;
import testjpa.member.domain.DoctorDto;
import testjpa.member.domain.Member;

@RestController
@RequiredArgsConstructor
public class DoctorController {
	
	private final DoctorService doctorService;

	@GetMapping("/doctors")
	public List<DoctorDto> findAllDoctorDto(){
		return doctorService.findAllDoctorDto();
	}
	
//	/* doctorId로 memberList 조회 */
//	@GetMapping("/doctor/members/{doctorId}")
//	public List<Member> findAllMembers(@PathVariable String doctorId){
//		return doctorService.findAllMember(doctorId);
//	}//@RequestBody 어노테이션 검색해보기

	
}
