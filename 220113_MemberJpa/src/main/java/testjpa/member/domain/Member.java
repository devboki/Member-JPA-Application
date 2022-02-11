package testjpa.member.domain;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
@ToString(of = {"id", "name", "gender", "age", "email", "roleType"})
public class Member extends BaseTimeEntity {

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
	@JoinColumn(name = "history_id")
	private History history;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "diary_id") 
	private Diary diary;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "doctor_id") 
	private Doctor doctor;

	public Member(String name, int age, String email, Gender gender, RoleType roleType, Diary diary, History history, Doctor doctor) {
		this.name = name;
		this.age = age;
		this.email = email;
		this.gender = gender;
		this.roleType = roleType;
		this.diary = diary;
		this.history = history;
		this.doctor = doctor;
	}

	public void changeMember(Long id, String name, Gender gender, int age, String email) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.email = email;
	} 
	
	public void changeDoctor(Doctor doctor) {
		if(this.doctor != null) {
			this.doctor.getMembers().remove(this);
	    }
		
		this.doctor = doctor;
		doctor.getMembers().add(this);
	}
}
