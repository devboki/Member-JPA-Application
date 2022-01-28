package testjpa.member.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testjpa.member.domain.History;
import testjpa.member.repository.HistoryRepository;

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
