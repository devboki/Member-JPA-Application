package testjpa.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
	MALE("남자"), FEMALE("여자");
	
	private final String kor;
}
