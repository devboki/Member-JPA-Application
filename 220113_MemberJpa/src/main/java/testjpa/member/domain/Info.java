package testjpa.member.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Info {

	@Id @GeneratedValue
	@Column(name = "info_id")
	private Long id;
	
	@CreationTimestamp
	private LocalDateTime createdTimeAt;
	
	private int diaryPainScale;
	
	private String history;
	
	@OneToOne(mappedBy = "info")
	@BatchSize(size = 100) //FetchType.LAZY 보다 우선으로 처리
	private Member member;
	
	public Info(String history) {
		this.history = history;
	}
}
