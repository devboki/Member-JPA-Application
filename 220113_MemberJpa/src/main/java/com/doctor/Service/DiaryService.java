package com.doctor.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.domain.Diary;
import com.doctor.repository.DiaryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaryService {
	
	private final DiaryRepository diaryRepository;
	
	@Transactional //데이터 변경 O
	public Long save(Diary diary) { 
		diaryRepository.save(diary);
		return diary.getId();
	}
}
