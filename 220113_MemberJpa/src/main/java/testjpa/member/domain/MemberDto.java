package testjpa.member.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {

	private Long id;
	
	@NotBlank(message = "이름을 입력해주세요.")
	private String name;
	
	@NotNull(message = "성별을 선택해주세요.")
	private Gender gender;
	
	@NotNull(message = "나이를 입력해주세요.")
	private int age;
	
	@NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요. (sample@email.com)")
	private String email;

	public MemberDto(Long id, String name, Gender gender, int age, String email) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.email = email;
	}

	public MemberDto() { }
}
