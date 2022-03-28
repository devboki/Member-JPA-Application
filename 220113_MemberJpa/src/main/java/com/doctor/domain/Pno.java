package com.doctor.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Pno {
	
	@NotBlank(message = "핸드폰번호를 입력해주세요.")
	@Size(min = 10, max = 11, message = " - 를 제외한 숫자 11자리를 입력해주세요.")
	private String pNo;
}
