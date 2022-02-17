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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import testjpa.member.domain.Member.MemberBuilder;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "password", "phoneNumber", "buisnessNumber"})
@DynamicUpdate
@DynamicInsert
public class Doctor extends BaseTimeEntity {

	@Id
	@Column(name = "doctor_id")
	private String id;
	
	private String password;
	
	private String phoneNumber;
	
	private String buisnessNumber;
	
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
	@BatchSize(size = 100)
	@JsonIgnore
	private List<Member> members = new ArrayList<>();
	
	@Builder
	public Doctor(String id, String password, String phoneNumber, String buisnessNumber) {
		this.id = id;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.buisnessNumber = buisnessNumber;
	}
}
