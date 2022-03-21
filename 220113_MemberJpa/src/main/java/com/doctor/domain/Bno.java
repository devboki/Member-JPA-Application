package com.doctor.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Bno {
	
	@NotBlank(message = "사업자번호를 입력해주세요.")
	@Size(min = 8, max = 11, message = "사업자번호 10자리를 입력해주세요.")
	@JsonProperty("b_no")
	private String bNo;
}
