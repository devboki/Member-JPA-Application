package com.doctor.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.Service.BuisnessService;
import com.doctor.Service.DoctorService;
import com.doctor.domain.Bno;
import com.doctor.domain.Doctor;
import com.doctor.domain.DoctorDto;
import com.doctor.domain.MemberDto;
import com.doctor.domain.RequestDoctorDto;
import com.doctor.domain.ResponseDoctorDto;
import com.doctor.domain.ResultDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DoctorApiController {
	
	private final DoctorService doctorService;
	private final BuisnessService buisnessService;

	/* DoctorDto 전체 조회 */
	@GetMapping("/api/doctors")
	public List<DoctorDto> findAllDoctorDto(){
		return doctorService.findAllDoctorDto();
	}
	
	/* DoctorDto 단건 조회 */
	@GetMapping("/api/doctor/{id}")
	public List<DoctorDto> findDoctorDtoAPI(@PathVariable("id") String doctorId) {
		return doctorService.findDoctorDto(doctorId);
	}
	
	/* MemberDto 조회 */
	@GetMapping("/api/doctor/members/{id}/{password}")
	public List<MemberDto> findMemberDto(@PathVariable("id") String doctorId, 
										@PathVariable("password") String password){
		return doctorService.findMemberDto(doctorId, password);
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
	
	/* 사업자등록 상태조회 */
	@PostMapping("/api/doctor/check")
	public ResultDto getService(@RequestBody Bno bNo) throws IOException {
		ResultDto resultDto = buisnessService.check(bNo);
		return resultDto;
	}
}
