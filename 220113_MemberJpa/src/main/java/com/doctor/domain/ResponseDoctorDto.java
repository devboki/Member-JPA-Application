package com.doctor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseDoctorDto {
	private String id;
	private String password;
	private String phoneNumber;
	private String buisnessNumber;
}
