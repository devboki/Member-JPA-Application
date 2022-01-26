package testjpa.member.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testjpa.member.domain.Info;
import testjpa.member.repository.InfoRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InfoService {
	
	private final InfoRepository infoRepository;
	
	/* 정보 저장 */
	@Transactional //데이터 변경
	public Long saveInfo(Info info) { 
		infoRepository.save(info);
		return info.getId();
	}

}
