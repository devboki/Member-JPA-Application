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
	private Diary diary;
	private History history;
	
	public ResultFindMember(String name, Gender gender, int age) {
		this.name = name;
		this.gender = gender;
		this.age = age;
	}
}
