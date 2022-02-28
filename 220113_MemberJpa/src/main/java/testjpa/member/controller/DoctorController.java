package testjpa.member.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import testjpa.member.Service.DoctorService;
import testjpa.member.domain.Doctor;
import testjpa.member.domain.DoctorDto;
import testjpa.member.domain.MemberDto;
import testjpa.member.domain.RequestDoctorDto;
import testjpa.member.domain.ResponseDoctorDto;

@RestController
@RequiredArgsConstructor
public class DoctorController {
	
	private final DoctorService doctorService;

	/* DoctorDto 전체 조회 */
	@GetMapping("/api/doctors")
	public List<DoctorDto> findAllDoctorDto(){
		return doctorService.findAllDoctorDto();
	}
	
	/* DoctorDto 단건 조회 */
	@GetMapping("/api/doctor/{id}")
	public List<DoctorDto> findDoctorDto(@PathVariable("id") String doctorId) {
		return doctorService.findDoctorDto(doctorId);
	}
	
	/* MemberDto 조회 */
	@GetMapping("/api/doctor/members/{id}")
	public List<MemberDto> findMemberDto(@PathVariable("id") String doctorId){
		return doctorService.findMemberDto(doctorId);
	}
	
	/* Doctor 등록 후 DoctorDto 조회 */
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
	
	/* RequestDoctorDto 요청 받아 Doctor update 후 ResponseDoctorDto 리턴 */
	@PatchMapping("/api/doctor/{id}")
	public ResponseDoctorDto updateDoctor(@PathVariable("id") String doctorId, @RequestBody @Valid RequestDoctorDto dto){
		doctorService.update(doctorId, dto.getPassword(), dto.getPhoneNumber());
		Doctor updateDoctor = doctorService.findDoctorOneId(doctorId);
		return new ResponseDoctorDto(updateDoctor.getId(), updateDoctor.getPassword(), 
										updateDoctor.getPhoneNumber(), updateDoctor.getBuisnessNumber());
	}
	
	/* Doctor 삭제 */
	@DeleteMapping("/api/doctor/{id}")
	public String deleteDoctor(@PathVariable("id") String doctorId) {
		doctorService.delete(doctorId);
		return "delete success";
	}
}