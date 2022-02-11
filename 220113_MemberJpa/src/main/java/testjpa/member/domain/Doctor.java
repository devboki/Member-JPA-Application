package testjpa.member.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;
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
@ToString(of = {"id", "name", "email", "hospital", "roleType"})
public class Doctor extends BaseTimeEntity {

	@Id @GeneratedValue
	@Column(name = "doctor_id")
	private Long id;
	
	@Column(length = 20)
	private String name;
	
	@Column(length = 100)
	private String email;
	
	@Column(length = 100)
	private String hospital;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	@ColumnDefault("'DOCTOR'")
	private RoleType roleType;
	
	@OneToMany(mappedBy = "doctor")
	@BatchSize(size = 100)
	private List<Member> members = new ArrayList<Member>();
	
	public Doctor(String name, String email, String hospital) {
		this.name = name;
		this.email = email;
		this.hospital = hospital;
	}
}
