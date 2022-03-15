package com.doctor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SequenceGenerator(name = "HISTORY_SEQ_GENERATOR",
					sequenceName = "HISTORY_SEQ",
					initialValue = 1, allocationSize = 1)
public class History extends BaseTimeEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
						generator = "MEMBER_SEQ_GENERATOR")
	@Column(name = "history_id")
	private Long id;
	
	private String history;
	
	@OneToOne(mappedBy = "history")
	@BatchSize(size = 100)
	private Member member;
	
	public History(String history) {
		this.history = history;
	}
}
