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

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "info_id")
	private Long id;
	
	private int painScale;
	
	@Column(name = "date")
	@CreationTimestamp
	private LocalDateTime createdTimeAt;
	
	private String history;
	
	public Info(LocalDateTime createdTimeAt, int painScale) {
		this.createdTimeAt = createdTimeAt;
		this.painScale = painScale;
	}
	
	public Info(String history) {
		this.history = history;
	}
}
