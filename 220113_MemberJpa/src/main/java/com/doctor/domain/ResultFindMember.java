package com.doctor.domain;

import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultFindMember {
	private String name;
	private Gender gender;
	private int age;
}
