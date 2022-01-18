package testjpa.member.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter //InitDb를 위해 @Setter
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 20)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10) //byte로 계산시 한글1=3byte 여자*3=6, mysql 4.1 이후 문자 수로 계산 여자=2
	private Gender gender;
	
	@Column(length = 2)
	private int age;
	
	@Column(length = 100)
	private String email;
}
