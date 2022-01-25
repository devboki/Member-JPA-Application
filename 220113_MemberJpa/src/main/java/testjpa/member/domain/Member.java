package testjpa.member.domain;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter //InitDb를 위해 @Setter
@DynamicInsert
public class Member {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;
	
	@Column(length = 20)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Gender gender;
	
	@Column(length = 2)
	private int age;
	
	@Column(length = 100)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	@ColumnDefault("'USER'")
	private RoleType roleType;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "info_id") //Foreign key
	private Info info;
	
	public Member() { } //JPA는 기본 생성자를 protected까지 열어놔야 함. 프록시 동작할 때 방해받으므로 private X

	public Member(String name, int age, String email, Gender gender) {
		this.name = name;
		this.age = age;
		this.email = email;
		this.gender = gender;
	}

	public void changeMember(Long id, String name, Gender gender, int age, String email) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.email = email;
	} 
}
