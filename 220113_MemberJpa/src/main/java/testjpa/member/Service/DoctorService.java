package testjpa.member.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testjpa.member.domain.Doctor;
import testjpa.member.domain.DoctorDto;
import testjpa.member.repository.DoctorRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {
	
	private final DoctorRepository doctorRepository;
	
	/* 가입 */
	@Transactional
	public String join(Doctor doctor) { 
		doctorRepository.save(doctor);
		return doctor.getId();
	}

	/* 중복 아이디 검증 */
	public void checkDoctorIdDuplication(Doctor doctor) {
		boolean idDuplication = doctorRepository.existsById(doctor.getId());
		if (idDuplication) {
			throw new IllegalStateException("이미 존재하는 아이디입니다.");
		}
	}
	
	/* 전체 의사 조회 : DTO */
	public List<DoctorDto> getAllDoctors() {
		return doctorRepository.findAll().stream().map(DoctorDto::fromEntity).collect(Collectors.toList());
	}
	
	/* 탈퇴 */
	@Transactional
	public void delete(String doctorId) {
		doctorRepository.deleteById(doctorId);
	}

	/* 의사 한명 조회 */
	public Optional<Doctor> findDoctorId(String doctorId) {
		return doctorRepository.findById(doctorId);
	}

	/* 환자 조회 */
	
}
