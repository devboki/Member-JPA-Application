package com.doctor.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.domain.Persistable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(of = {"id", "password", "phoneNumber", "buisnessNumber"})
@DynamicUpdate
@DynamicInsert
public class Doctor extends BaseTimeEntity implements Persistable<String> {

	@Id
	@Column(name = "doctor_id")
	private String id;
	
	private String password;
	
	private String phoneNumber;
	
	private String buisnessNumber;
	
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, orphanRemoval = true)
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
	
	public void changeDoctor(String password, String phoneNumber) {
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	
	@Override 
	public String getId() { 
		return id; 
	}
	
	@Override 
	public boolean isNew() { 
		return getCreatedDate() == null;
	}
}
