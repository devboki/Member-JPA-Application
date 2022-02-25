package testjpa.member.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import testjpa.member.Service.DoctorService;
import testjpa.member.domain.Doctor;
import testjpa.member.domain.DoctorDto;
import testjpa.member.domain.MemberDto;

@RestController
@RequiredArgsConstructor
public class DoctorController {
	
	private final DoctorService doctorService;

	@GetMapping("/api/doctors")
	public List<DoctorDto> findAllDoctorDto(){
		return doctorService.findAllDoctorDto();
	}
	
	@GetMapping("/api/doctor/{id}")
	public List<DoctorDto> findDoctorDto(@PathVariable("id") String doctorId) {
		return doctorService.findDoctorDto(doctorId);
	}
	
	@GetMapping("/api/doctor/members/{id}")
	public List<MemberDto> findMemberDto(@PathVariable("id") String doctorId){
		return doctorService.findMemberDto(doctorId);
	}
	
	@PostMapping("/api/doctor/new")
	public List<DoctorDto> createDoctor(@RequestBody @Valid DoctorDto dto) {
		
		Doctor doctor = Doctor.builder()
				.id(dto.getId())
				.password(dto.getPassword())
				.phoneNumber(dto.getPhoneNumber())
				.buisnessNumber(dto.getBuisnessNumber())
				.build();
		
		String doctorId = doctorService.join(doctor);
		
		return doctorService.findDoctorDto(doctorId);
	}
	
	/* 개발중 */
	@PutMapping("/api/doctor/{id}")
	public List<DoctorDto> updateDoctor(@PathVariable("id") String doctorId,
										@RequestBody @Valid UpdateDoctorDto updateDto){
		
		Doctor findDoctor = doctorService.findDoctorOneId(doctorId);
		findDoctor.changeDoctor(findDoctor.getId(), updateDto.getPassword(), updateDto.getPhoneNumber());

		return doctorService.findDoctorDto(findDoctor.getId());
	}
	
	@Data
	static class UpdateDoctorDto {
		private String password;
		private String phoneNumber;
	}
}