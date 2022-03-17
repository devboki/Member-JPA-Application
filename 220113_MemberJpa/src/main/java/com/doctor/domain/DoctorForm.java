package com.doctor.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DoctorForm {
	
	@NotBlank(message = "아이디를 입력해주세요.")
	private String id;
	
	@NotBlank(message = "비밀번호를 입력해주세요.")
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 영문 대/소문자와 숫자, 특수문자가 포함된 8자 이상이어야 합니다.")
	private String password;
	
	@NotBlank(message = "핸드폰번호를 입력해주세요.")
	private String phoneNumber;
	
	@NotBlank(message = "사업자번호를 입력해주세요.")
	private String buisnessNumber;
}
