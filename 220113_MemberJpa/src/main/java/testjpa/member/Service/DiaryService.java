package testjpa.member.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testjpa.member.domain.Diary;
import testjpa.member.repository.DiaryRepository;

@Service
@Transactional(readOnly = true) //데이터 변경 X
@RequiredArgsConstructor 	//final 생성자 생성
public class DiaryService {
	
	private final DiaryRepository diaryRepository;
	
	@Transactional //데이터 변경 O
	public Long save(Diary diary) { 
		diaryRepository.save(diary);
		return diary.getId();
	}
}
