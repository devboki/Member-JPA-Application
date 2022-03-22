package com.doctor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
	USER("환자"), ADMIN("관리자"), DOCTOR("의사");
	
	private String value;
	}
