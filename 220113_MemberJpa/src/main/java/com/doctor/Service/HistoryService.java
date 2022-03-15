package com.doctor.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.domain.History;
import com.doctor.repository.HistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HistoryService {

	private final HistoryRepository historyRepository;
	
	@Transactional
	public Long save(History history) {
		historyRepository.save(history);
		return history.getId(); 
	}
}
