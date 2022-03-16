package com.doctor.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.domain.Bno;
import com.doctor.domain.Doctor;
import com.doctor.domain.DoctorDto;
import com.doctor.domain.MemberDto;
import com.doctor.domain.RequestDoctorDto;
import com.doctor.domain.ResponseDoctorDto;
import com.doctor.domain.ResultDto;
import com.doctor.service.BuisnessService;
import com.doctor.service.DoctorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DoctorController {
	
	private final DoctorService doctorService;
	private final BuisnessService buisnessService;
	
	/* DoctorDto 단건 조회 */
	@GetMapping("/findDoctor")
	public List<DoctorDto> findDoctorDto(@RequestParam("id") String doctorId) {
		return doctorService.findDoctorDto(doctorId);
	}
	
	/* MemberDto 조회 */
	@GetMapping("/findMember")
	public List<MemberDto> findMemberDto(@RequestParam("id") String doctorId,
										@RequestParam("password") String password){
		return doctorService.findMemberDto(doctorId, password);
	}
	
	/* RequestDoctorDto 요청 받아 Doctor update 후 ResponseDoctorDto 리턴 */
//	@PatchMapping("/api/doctor/{id}")
//	public ResponseDoctorDto updateDoctor(@PathVariable("id") String doctorId, @RequestBody @Valid RequestDoctorDto dto){
//		doctorService.update(doctorId, dto.getPassword(), dto.getPhoneNumber());
//		Doctor updateDoctor = doctorService.findDoctorOneId(doctorId);
//		return new ResponseDoctorDto(updateDoctor.getId(), updateDoctor.getPassword(), 
//										updateDoctor.getPhoneNumber(), updateDoctor.getBuisnessNumber());
//	}
	
	/* Doctor 삭제 */
//	@DeleteMapping("/api/doctor/{id}")
//	public String deleteDoctor(@PathVariable("id") String doctorId) {
//		doctorService.delete(doctorId);
//		return "delete success";
//	}
	
	/* 사업자등록 상태조회 */
	@PostMapping("/check")
	public ResultDto bNoCheck(Bno bNo) throws IOException {
		ResultDto resultDto = buisnessService.check(bNo);
		return resultDto;
	}
}