package testjpa.member.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import testjpa.member.domain.Doctor;
import testjpa.member.domain.DoctorDto;
import testjpa.member.domain.Member;
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
	public List<DoctorDto> findAllDoctorDto() {
		return doctorRepository.findAll().stream().map(DoctorDto::fromEntity).collect(Collectors.toList());
	}
	
	/* 전체 의사 조회 : Entity */
	public List<Doctor> findAllDoctor(){
		return doctorRepository.findAll();
	}
	
	/* 의사 한명 조회 : Optional */
	public Optional<Doctor> findDoctorIdOptional(String doctorId) {
		return doctorRepository.findById(doctorId);
	}

	/* 의사 한명 조회 : 단건 */
	public Doctor findDoctorById(String doctorId) {
		return doctorRepository.findDoctorById(doctorId);
	}
	
	/* 환자 조회 : Entity 개발중 */
	public Page<Doctor> findAllMember(Pageable pageable){
		return doctorRepository.findAllMember(pageable);
	}
	
	/* 탈퇴 */
	@Transactional
	public void delete(String doctorId) {
		doctorRepository.deleteById(doctorId);
	}

	
	
}
