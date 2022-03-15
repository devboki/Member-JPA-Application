package com.doctor.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SequenceGenerator(name = "DIARY_SEQ_GENERATOR",
					sequenceName = "DIARY_SEQ",
					initialValue = 1, allocationSize = 1)
public class Diary extends BaseTimeEntity {

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
						generator = "DIARY_SEQ_GENERATOR")
	@Column(name = "diary_id")
	private Long id;
	
	@Column(name = "date")
	@CreationTimestamp
	private LocalDateTime createdTimeAt; 
	
	private int painScale;
	
	@OneToOne(mappedBy = "diary")
	@BatchSize(size = 100)
	private Member member;
	
	public Diary(int painScale) {
		this.painScale = painScale;
	}
}
