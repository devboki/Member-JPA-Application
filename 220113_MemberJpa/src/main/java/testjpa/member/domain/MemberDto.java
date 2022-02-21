package testjpa.member.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
	
	private RoleType roleType;
	
	private Diary diary;
	
	private History history;
	
	public MemberDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public MemberDto(History history) {
		this.history = history;
	}
	
	public MemberDto(Diary diary) {
		this.diary = diary;
	}
}
